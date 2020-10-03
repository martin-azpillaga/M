package m.main;

import static m.m.MPackage.Literals.FUNCTION__NAME;

import java.io.File;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.CompletableFuture;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.util.EcoreUtil;
import org.eclipse.lsp4j.CompletionItem;
import org.eclipse.lsp4j.CompletionItemKind;
import org.eclipse.lsp4j.CompletionList;
import org.eclipse.lsp4j.CompletionOptions;
import org.eclipse.lsp4j.CompletionParams;
import org.eclipse.lsp4j.Diagnostic;
import org.eclipse.lsp4j.DiagnosticSeverity;
import org.eclipse.lsp4j.DidChangeConfigurationParams;
import org.eclipse.lsp4j.DidChangeTextDocumentParams;
import org.eclipse.lsp4j.DidChangeWatchedFilesParams;
import org.eclipse.lsp4j.DidChangeWorkspaceFoldersParams;
import org.eclipse.lsp4j.DidCloseTextDocumentParams;
import org.eclipse.lsp4j.DidOpenTextDocumentParams;
import org.eclipse.lsp4j.DidSaveTextDocumentParams;
import org.eclipse.lsp4j.FileChangeType;
import org.eclipse.lsp4j.Hover;
import org.eclipse.lsp4j.HoverParams;
import org.eclipse.lsp4j.InitializeParams;
import org.eclipse.lsp4j.InitializeResult;
import org.eclipse.lsp4j.MarkupContent;
import org.eclipse.lsp4j.MessageParams;
import org.eclipse.lsp4j.MessageType;
import org.eclipse.lsp4j.ParameterInformation;
import org.eclipse.lsp4j.PublishDiagnosticsParams;
import org.eclipse.lsp4j.ServerCapabilities;
import org.eclipse.lsp4j.SignatureHelp;
import org.eclipse.lsp4j.SignatureHelpOptions;
import org.eclipse.lsp4j.SignatureHelpParams;
import org.eclipse.lsp4j.SignatureInformation;
import org.eclipse.lsp4j.TextDocumentSyncKind;
import org.eclipse.lsp4j.WorkspaceFoldersOptions;
import org.eclipse.lsp4j.WorkspaceServerCapabilities;
import org.eclipse.lsp4j.jsonrpc.messages.Either;
import org.eclipse.lsp4j.services.LanguageClient;
import org.eclipse.lsp4j.services.LanguageClientAware;
import org.eclipse.lsp4j.services.TextDocumentService;
import org.eclipse.lsp4j.services.WorkspaceService;
import org.eclipse.xtext.EcoreUtil2;
import org.eclipse.xtext.TerminalRule;
import org.eclipse.xtext.nodemodel.impl.CompositeNode;
import org.eclipse.xtext.nodemodel.impl.CompositeNodeWithSemanticElement;
import org.eclipse.xtext.nodemodel.impl.HiddenLeafNode;
import org.eclipse.xtext.nodemodel.util.NodeModelUtils;
import org.eclipse.xtext.parser.IParser;

import m.MStandaloneSetup;
import m.generator.Engine;
import m.generator.Generator;
import m.library.Library;
import m.library.symbols.Component;
import m.library.types.AtomicType;
import m.library.types.FunctionType;
import m.m.Application;
import m.m.Assignment;
import m.m.Binary;
import m.m.BindingBlock;
import m.m.Block;
import m.m.Cell;
import m.m.Function;
import m.m.Statement;
import m.m.Unary;
import m.m.Value;
import m.validation.Inference;
import m.validation.Validator;
import m.validation.problems.errors.RedefinedSymbol;
import m.validation.rules.Binding;
import m.validation.rules.Binding.BindingReason;
import m.validation.rules.ExpressionNode;

public class Server implements org.eclipse.lsp4j.services.LanguageServer, LanguageClientAware, TextDocumentService, WorkspaceService
{
	LanguageClient client;
	List<Workspace> workspaces;

	Validator validator;
	Generator generator;
	
    
	// 1 Connection
	
	@Override
	public void connect(LanguageClient client)
	{
		this.client = client;
	}
	
	// Set capabilities and initialize workspaces with file -> inferenceData
	@Override
	public CompletableFuture<InitializeResult> initialize(InitializeParams params)
	{
		var injector = new MStandaloneSetup().createInjectorAndDoEMFRegistration();
		validator = injector.getInstance(Validator.class);
		generator = injector.getInstance(Generator.class);

        var workspaceFolders = new WorkspaceFoldersOptions();
		workspaceFolders.setSupported(true);
        workspaceFolders.setChangeNotifications(true);
		
		var capabilities = new ServerCapabilities();
        capabilities.setTextDocumentSync(TextDocumentSyncKind.Full);
		capabilities.setWorkspace(new WorkspaceServerCapabilities(workspaceFolders));
		capabilities.setHoverProvider(true);
		capabilities.setCompletionProvider(new CompletionOptions(false, Arrays.asList(".","(",")","{","}"," ")));
		capabilities.setSignatureHelpProvider(new SignatureHelpOptions(Arrays.asList("(", ",")));
		
		workspaces = new ArrayList<>();
		
		for (var folder : params.getWorkspaceFolders())
		{
			initializeWorkspace(folder.getUri());
		}
		
		return CompletableFuture.supplyAsync(()->new InitializeResult(capabilities));
	}
	
	@Override
	public TextDocumentService getTextDocumentService()
	{
		return this;
	}

	@Override
	public WorkspaceService getWorkspaceService()
	{
		return this;
	}
	
	@Override
	public CompletableFuture<Object> shutdown()
	{
		return CompletableFuture.supplyAsync(() -> Boolean.TRUE);
	}

	@Override
	public void exit()
	{
		System.exit(0);
	}






	// 2 Workspace folders

	
	private void initializeWorkspace(String path)
	{
		var root = decode(path);
		
		var workspace = new Workspace(root);
		workspaces.add(workspace);

		var diagnosticsOfFile = new HashMap<String,List<Diagnostic>>();
		
		try (var folder = Files.walk(Paths.get(root)))
		{
			folder.forEach(f -> 
			{
				if (f.toString().endsWith(".Ⲙ"))
				{
					try
					{
						var diagnostics = localInference(f.toString(), workspace, new String(Files.readAllBytes(Paths.get(f.toString()))));
						diagnosticsOfFile.put(f.toString(), diagnostics);
					}
					catch (IOException e)
					{
						write(e.getMessage());
					}
				}
			});
		}
		catch (IOException e)
		{
			write(e.getMessage());
		}

		for (var entry : diagnosticsOfFile.entrySet())
		{
			client.publishDiagnostics(new PublishDiagnosticsParams("file://"+entry.getKey(), entry.getValue()));
		}
	}

	private Workspace findWorkspace(String filePath)
	{
		for (var workspace : workspaces)
		{
			if (filePath.startsWith(workspace.root))
			{
				return workspace;
			}
		}
		return null;
	}

	
	
	
	
	@Override
	public void didChangeWorkspaceFolders(DidChangeWorkspaceFoldersParams params)
	{
		for (var added : params.getEvent().getAdded())
		{
			initializeWorkspace(added.getUri());
		}
		for (var removed : params.getEvent().getRemoved())
		{
			for (var i = 0; i < workspaces.size(); i++)
			{
				var workspace = workspaces.get(i);
				
				if (workspace.root.equals(decode(removed.getUri())))
				{
					workspaces.remove(workspace);
				}
			}
		}
	}
	
	
	@Override
	public void didChangeConfiguration(DidChangeConfigurationParams params)
	{
		// No configuration is sync'ed between server and client
		// All configuration is stored in configuration files like m.project
		// so that the projects are more portable
	}

	
	// Local inference on added file, remove from workspace when deleted
	@Override
	public void didChangeWatchedFiles(DidChangeWatchedFilesParams params)
	{
		for (var change : params.getChanges())
		{
			var path = decode(change.getUri());
			var workspace = findWorkspace(path);

			if (workspace == null) { continue; }
			
			if (change.getType() == FileChangeType.Created)
			{
				
				try
				{
					localInference(path, workspace, new String(Files.readAllBytes(Paths.get(path))));
				}
				catch (IOException e)
				{
					write(e.getMessage());
				}
			}
			else if (change.getType() == FileChangeType.Deleted)
			{
				workspace.files.remove(path);
			}
		}
	}
	
	
	
	
	
	
	
	
	// 3 files
	
	@Override
	public void didOpen(DidOpenTextDocumentParams params)
	{
		onChange(params.getTextDocument().getUri(), params.getTextDocument().getText());
	}
	
	

	@Override
	public void didChange(DidChangeTextDocumentParams params)
	{
		onChange(params.getTextDocument().getUri(), params.getContentChanges().get(0).getText());
	}

	@Override
	public void didClose(DidCloseTextDocumentParams params)
	{
		// No action
	}

	@Override
	public void didSave(DidSaveTextDocumentParams params)
	{
		// No action
	}

	private void onChange(String uri, String text)
	{
		var filePath = decode(uri);
		
		var workspace = findWorkspace(filePath);
		
		var diagnostics = localInference(filePath, workspace, text);
		
		var globalDiagnostics = globalInference(filePath, workspace, text, true);

		if (globalDiagnostics.containsKey(filePath))
		{
			globalDiagnostics.get(filePath).addAll(diagnostics);
		}
		else
		{
			globalDiagnostics.put(filePath, diagnostics);
		}

		for (var entry : workspace.files.entrySet())
		{
			if (globalDiagnostics.containsKey(entry.getKey()))
			{
				client.publishDiagnostics(new PublishDiagnosticsParams("file://"+entry.getKey(), globalDiagnostics.get(entry.getKey())));
			}
			else
			{
				client.publishDiagnostics(new PublishDiagnosticsParams("file://"+entry.getKey(), new ArrayList<>()));
			}
		}
	}








	
	
	
	
	
	
	
	// 4 Quality of development features
	
	@Override
	public CompletableFuture<Hover> hover(HoverParams params)
	{
		var result = "";
		
		var file = decode(params.getTextDocument().getUri());
		var workspace = findWorkspace(file);
		var library = workspace.game.library;
		var localData = workspace.files.get(file);

		var text = localData.text;
		var position = params.getPosition();
		var offset = offset(text, position.getLine(), position.getCharacter());
		
		var node = NodeModelUtils.findLeafNodeAtOffset(localData.rootNode, offset);
		
		if (node instanceof HiddenLeafNode)
		{
			var hover = new Hover();
			var contents = new MarkupContent("markdown", result);
			hover.setContents(contents);
			return CompletableFuture.supplyAsync(() -> hover);
		}
		var semantic = node.getSemanticElement();
		
		if (semantic instanceof Function)
		{
			var function = (Function) semantic;
			if (node.getText().equals(function.getName()))
			{
				result = "User system";
			}
		}
		else if (semantic instanceof Value)
		{
			var value = (Value) semantic;
			
			var container = value.eContainer();
			
			if (container instanceof Cell)
			{
				var cell = (Cell) container;
				if (cell.getComponent() == value)
				{
					var standard = library.getComponent(value.getName());

					if (standard != null)
					{
						var type = library.getName(standard.getType());
						result = "Standard component : " + type;
					}
					else
					{
						var type = workspace.game.components.get(value.getName());
						if (type != null)
						{
							result = "User component : " + library.getName(type);
						}
						else
						{
							result = "User component : ?";
						}
					}
				}
				else
				{
					var function = EcoreUtil2.getContainerOfType(cell, Function.class);
					UserFunction userFunction = null;
					for (var f : workspace.game.functions)
					{
						if (f.getName().equals(function.getName()))
						{
							userFunction = f;
							break;
						}
					}
					var query = userFunction.queries.get(value.getName());
					
					if (query == null)
					{
						var standard = library.getValue(value.getName());

						if (standard != null)
						{
							var type = library.getName(standard.getType());
							result = "Standard value : " + type;
						}
						else
						{
							var type = workspace.game.values.get(value);
							if (type != null)
							{
								result = "User value : " + library.getName(type);
							}
							else
							{
								result = "user value : ?";
							}
						}
					}
					else
					{
						result = "Entity query\n\n";
						var builder = new StringBuilder(result);
						for (var component : query.entrySet())
						{
							builder.append("* ");
							builder.append(component.getKey());
							builder.append("\n\n");
						}
						result = builder.toString();
					}
				}
			}
			else
			{
				var function = EcoreUtil2.getContainerOfType(value, Function.class);
				UserFunction userFunction = null;
				for (var f : workspace.game.functions)
				{
					if (f.getName().equals(function.getName()))
					{
						userFunction = f;
						break;
					}
				}
				var query = userFunction.queries.get(value.getName());
				
				if (query == null)
				{
					var standard = library.getValue(value.getName());

					if (standard != null)
					{
						var type = library.getName(standard.getType());
						result = "Standard value : " + type;
					}
					else
					{
						var type = workspace.game.values.get(value);
						if (type != null)
						{
							result = "User value : " + library.getName(type);
						}
						else
						{
							result = "user value : ?";
						}
					}
				}
				else
				{
					result = "Entity query\n\n";
					var builder = new StringBuilder(result);
					for (var component : query.entrySet())
					{
						builder.append("* ");
						builder.append(component.getKey());
						builder.append("\n\n");
					}
					result = builder.toString();
				}
			}
		}
		else if (semantic instanceof Binary)
		{
			var binary = (Binary) semantic;
			var standard = library.getFunction(binary.getOperator());
			if (standard != null)
			{
				result = "Standard operator\n\nType: " + library.getName(standard.getType());
			}
		}
		else if (semantic instanceof Unary)
		{
			var unary = (Unary) semantic;
			var standard = library.getFunction(unary.getOperator());
			if (standard != null)
			{
				result = "Standard operator\n\nType: " + library.getName(standard.getType());
			}
		}
		else if (semantic instanceof Application)
		{
			var application = (Application) semantic;
			var standard = library.getFunction(application.getName());
			if (standard != null)
			{
				result = "Standard function\n\nType: " + library.getName(standard.getType());
			}
		}

		var hover = new Hover();
		var contents = new MarkupContent("markdown", result);
		hover.setContents(contents);
		return CompletableFuture.supplyAsync(() -> hover);

	}
	
	
	@Override
	public CompletableFuture<Either<List<CompletionItem>, CompletionList>> completion(CompletionParams params)
	{
		var result = new ArrayList<CompletionItem>();

		var file = decode(params.getTextDocument().getUri());
		var workspace = findWorkspace(file);
		var library = workspace.game.library;
		var localData = workspace.files.get(file);

		var text = localData.text;
		var position = params.getPosition();
		var offset = offset(text, position.getLine(), position.getCharacter());
		
		var node = NodeModelUtils.findLeafNodeAtOffset(localData.rootNode, offset-1);
		
		var semantic = node.getSemanticElement();
		var grammatic = node.getGrammarElement();
		
		if (semantic instanceof Cell || semantic instanceof Value && semantic.eContainer() instanceof Cell)
		{
			Cell cell;
			if (semantic instanceof Cell)
			{
				cell = (Cell) semantic;
			}
			else
			{
				cell = (Cell) semantic.eContainer();
			}

			for (var component : workspace.game.components.keySet())
            {
				if (component == null || cell.getComponent() != null && cell.getComponent().getName() != null && cell.getComponent().getName().equals(component))
				{
					continue;
				}
                var item = new CompletionItem(component);
				item.setDocumentation(library.getDescription(library.getComponent(component)));
				item.setDetail(library.getName(workspace.game.components.get(component)));
                item.setKind(CompletionItemKind.Class);
                result.add(item);
            }
			for (var component : Component.values())
			{
                var item = new CompletionItem(library.getName(component));
				item.setDocumentation(library.getDescription(component));
				item.setDetail(library.getName(component.getType()));
                item.setKind(CompletionItemKind.Enum);
                result.add(item);
			}
		}
		else if (grammatic instanceof TerminalRule && ((TerminalRule)grammatic).getName().equals("IDENTIFIER"))
		{			
			for (var function : m.library.symbols.Function.values())
            {
                if (function != m.library.symbols.Function.ASSIGNMENT)
                {
                    var item = new CompletionItem(library.getName(function));
                    item.setKind(CompletionItemKind.Function);
                    result.add(item);
                }
            }

            for (var value : m.library.symbols.Value.values())
            {
                var item = new CompletionItem(library.getName(value));
                item.setKind(CompletionItemKind.Value);
                result.add(item);
			}
			
			EObject statement = EcoreUtil2.getContainerOfType(semantic, Statement.class);
			if (statement == null)
			{
				statement = EcoreUtil2.getContainerOfType(semantic, Function.class);
			}
			if (statement == semantic)
			{
				if (statement instanceof BindingBlock)
				{
					var name = ((BindingBlock) statement).getExpression().getName();
					var item = new CompletionItem(name);
					item.setDetail(library.getName(AtomicType.ENTITY));
					item.setSortText("0"+name);
					item.setKind(CompletionItemKind.Variable);
					result.add(item);
				}
				// identifier lost in a block
				var siblings = node.getParent().getParent().getChildren();
				for (var sibling : siblings)
				{
					if (sibling == node.getParent())
					{
						break;
					}
					else if (sibling instanceof CompositeNode && ((CompositeNode)sibling).getFirstChild() instanceof CompositeNodeWithSemanticElement)
					{
						var semanticOfSibling = ((CompositeNode)sibling).getFirstChild().getSemanticElement();
						if (semanticOfSibling instanceof Assignment)
						{
							var assignment = (Assignment) semanticOfSibling;
							if (assignment.getAtom() instanceof Value)
							{
								var atom = (Value) assignment.getAtom();

								var item = new CompletionItem(atom.getName());
								item.setKind(CompletionItemKind.Variable);
								result.add(item);
							}
						}
					}
				}
			}
			var container = statement.eContainer();

			while(! (container instanceof m.m.File))
			{
				if (container instanceof BindingBlock)
				{
					var name = ((BindingBlock) container).getExpression().getName();
					var item = new CompletionItem(name);
					item.setDetail(library.getName(AtomicType.ENTITY));
					item.setSortText("0"+name);
					item.setKind(CompletionItemKind.Variable);
					result.add(item);
				}

				List<Statement> statements;

				if (container instanceof Block)
				{
					statements = ((Block) container).getStatements();
				}
				else if (container instanceof BindingBlock)
				{
					statements = ((BindingBlock) container).getStatements();
				}
				else if (container instanceof Function)
				{
					statements = ((Function) container).getStatements();
				}
				else
				{
					statements = new ArrayList<>();
				}

				for (var s : statements)
				{
					if (s == statement)
					{
						break;
					}
					else
					{
						if (s instanceof Assignment)
						{
							var assignment = (Assignment) s;
							if (assignment.getAtom() instanceof Value)
							{
								var atom = (Value) assignment.getAtom();

								var item = new CompletionItem(atom.getName());
								item.setKind(CompletionItemKind.Variable);
								item.setSortText("0"+atom.getName());
								result.add(item);
							}
						}
					}
				}

				var temp = container.eContainer();
				statement = container;
				container = temp;
			}
		}
		else if (semantic instanceof Value)
		{
			for (var function : m.library.symbols.Function.values())
            {
                if (function != m.library.symbols.Function.ASSIGNMENT)
                {
                    var item = new CompletionItem(library.getName(function));
                    item.setKind(CompletionItemKind.Function);
                    result.add(item);
                }
            }

            for (var value : m.library.symbols.Value.values())
            {
                var item = new CompletionItem(library.getName(value));
                item.setKind(CompletionItemKind.Value);
                result.add(item);
			}
			
			EObject statement = EcoreUtil2.getContainerOfType(semantic, Statement.class);
			var container = statement.eContainer();

			while(! (container instanceof m.m.File))
			{
				if (container instanceof BindingBlock)
				{
					var name = ((BindingBlock) container).getExpression().getName();
					var item = new CompletionItem(name);
					item.setDetail(library.getName(AtomicType.ENTITY));
					item.setSortText("0"+name);
					item.setKind(CompletionItemKind.Variable);
					result.add(item);
				}

				List<Statement> statements;

				if (container instanceof Block)
				{
					statements = ((Block) container).getStatements();
				}
				else if (container instanceof BindingBlock)
				{
					statements = ((BindingBlock) container).getStatements();
				}
				else if (container instanceof Function)
				{
					statements = ((Function) container).getStatements();
				}
				else
				{
					statements = new ArrayList<>();
				}

				for (var s : statements)
				{
					if (s == statement)
					{
						break;
					}
					else
					{
						if (s instanceof Assignment)
						{
							var assignment = (Assignment) s;
							if (assignment.getAtom() instanceof Value)
							{
								var atom = (Value) assignment.getAtom();

								var item = new CompletionItem(atom.getName());
								item.setKind(CompletionItemKind.Variable);
								item.setSortText("0"+atom.getName());
								result.add(item);
							}
						}
					}
				}

				var temp = container.eContainer();
				statement = container;
				container = temp;
			}
		}
        
		return CompletableFuture.supplyAsync(() -> Either.forLeft(result));
    }
	
	
	@Override
	public CompletableFuture<SignatureHelp> signatureHelp(SignatureHelpParams params)
	{
		var file = decode(params.getTextDocument().getUri());
		var workspace = findWorkspace(file);
		var library = workspace.game.library;

		var offset = offset(workspace.files.get(file).text, params.getPosition().getLine(), params.getPosition().getCharacter());
		var node = NodeModelUtils.findLeafNodeAtOffset(workspace.files.get(file).rootNode, offset-1);

		var help = new SignatureHelp();
		var list = new ArrayList<SignatureInformation>();
		
		if (node != null && node.getSemanticElement() instanceof Application)
		{
			var application = (Application) node.getSemanticElement();
			var name = application.getName();
			var standard = library.getFunction(name);

			if (standard != null)
			{
				var type = (FunctionType) standard.getType();
				var parameters = new ArrayList<ParameterInformation>();
				for (var i = 0; i < type.parameterTypes.length; i++)
				{
					parameters.add(new ParameterInformation("Parameter "+i, library.getName(type.parameterTypes[i])));
				}
				var info = new SignatureInformation(name + " : " + library.getName(type), library.getDescription(standard), parameters);
				list.add(info);
				help.setSignatures(list);
				if (params.getContext().getTriggerCharacter() != null)
				{
					help.setActiveParameter(application.getArguments().size());
				}
				else
				{
					help.setActiveParameter(application.getArguments().size()-1);
				}
				return CompletableFuture.supplyAsync(()->help);
			}
		}

		
		
		help.setSignatures(list);
		return CompletableFuture.supplyAsync(()->help);
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	private HashMap<String, List<Diagnostic>> globalInference(String filePath, Workspace workspace, String text, boolean shouldGenerate)
	{
		var diagnostics = new HashMap<String, List<Diagnostic>>();
        
        // Delete previous crossReferences to this file

        var crossReferences = workspace.crossReferences;

		for (var i = 0; i < crossReferences.size(); i++)
		{
			var crossReference = crossReferences.get(i);
			if (crossReference.fileA.equals(filePath))
			{
				crossReference.nodeB.bindings.remove(crossReference.bindingB);
				crossReferences.remove(crossReference);
			}
			else if (crossReference.fileB.equals(filePath))
			{
				crossReference.nodeA.bindings.remove(crossReference.bindingA);
				crossReferences.remove(crossReference);
			}
		}
		
		
		var totalNodes = new ArrayList<ExpressionNode>();
		var totalComponents = new HashMap<String, ExpressionNode>();
		var totalFunctions = new HashMap<String, Function>();
		var definedIn = new HashMap<String,String>();
		

		for (var entry : workspace.files.entrySet())
		{
			var currentFile = entry.getKey();
			var data = entry.getValue();
			totalNodes.addAll(data.nodes);
			
			for (var component : data.components.keySet())
			{
				var existing = totalComponents.get(component);
				if (existing == null)
				{
					definedIn.put(component, currentFile);
					totalComponents.put(component, data.components.get(component));
				}
				else
				{
					var bindingA = new Binding(existing, BindingReason.SAME_COMPONENT);
					var bindingB = new Binding(data.components.get(component), BindingReason.SAME_COMPONENT);
					var crossReference = new CrossReference(currentFile, data.components.get(component), bindingA, definedIn.get(component), existing, bindingB);
					
					crossReferences.add(crossReference);
					
					data.components.get(component).bindings.add(bindingA);
					existing.bindings.add(bindingB);
				}
			}
			
			for (var function : data.functions.entrySet())
			{
				if (totalFunctions.containsKey(function.getKey()))
				{
					var file = fileOf(totalFunctions.get(function.getKey()), workspace);

					reportRedefinedFunction(totalFunctions.get(function.getKey()), workspace, file, diagnostics);
					reportRedefinedFunction(function.getValue(), workspace, currentFile, diagnostics);
				}
				else
				{
					totalFunctions.put(function.getKey(), function.getValue());
				}
			}
		}
		
		var inference = new Inference(totalNodes);
		var problems = inference.check();
		
		for (var problem : problems)
		{
			for (var d : problem.diagnostics(Library.ENGLISH, text))
			{
				//diagnostics.put(file, d);
			}
		}
		
		// Check for global and local errors
		var hasErrors = false;
		
		for (var list : diagnostics.values())
		{
			for (var i = 0; i < list.size() && !hasErrors; i++)
			{
				if (list.get(i).getSeverity() == DiagnosticSeverity.Error)
				{
					hasErrors = true;
				}
			}
		}

		for (var localData : workspace.files.values())
		{
			for (var problem : localData.problems)
			{
				for (var diagnostic : problem.diagnostics(Library.ENGLISH, localData.text))
				{
					if (diagnostic.getSeverity() == DiagnosticSeverity.Error)
					{
						hasErrors = true;
					}
				}
			}
		}

		var game = new Game(Library.ENGLISH);
			
		for (var component : totalComponents.entrySet())
		{
			var type = inference.infer(component.getValue());
			game.components.put(component.getKey(), type);
		}
		
		for (var function : totalFunctions.values())
		{
			game.functions.add(new UserFunction(function, new FunctionType(null, AtomicType.UNIT)));
		}

		workspace.game = game;

		if (!hasErrors && shouldGenerate)
		{
			generateCode(game, workspace);
		}
		
		return diagnostics;
	}

	private void reportRedefinedFunction(Function function, Workspace workspace, String file, Map<String,List<Diagnostic>> diagnostics)
	{
		if (! diagnostics.containsKey(file))
		{
			diagnostics.put(file, new ArrayList<>());
		}

		var redefined = new RedefinedSymbol(function, FUNCTION__NAME);

		for (var diagnostic : redefined.diagnostics(Library.ENGLISH, workspace.files.get(file).text))
		{
			diagnostics.get(file).add(diagnostic);
		}
	}

	private String fileOf(EObject o, Workspace workspace)
	{
		for (var file : workspace.files.entrySet())
		{
			if (EcoreUtil.getRoot(o, true) == file.getValue().rootObject)
			{
				return file.getKey();
			}
		}
		return "";
	}
	
	
	
	
	private List<Diagnostic> localInference(String file, Workspace workspace, String text)
	{
		var data = validator.validate(text);

		workspace.files.put(file, data);

		var diagnostics = new ArrayList<Diagnostic>();

		for (var problem : data.problems)
		{
			diagnostics.addAll(problem.diagnostics(Library.ENGLISH, text));
		}
		
		return diagnostics;
	}

	private void generateCode(Game game, Workspace workspace)
	{		
		var path = Paths.get(workspace.root.replace("/c:/", "C:/").replace("/d:/", "D:/").replace("/e:/",  "E:/"), "m.project");
		
		if (new File(path.toString()).exists())
		{
			try
			{
				var configuration = Files.readAllLines(path);
				for (var line : configuration)
				{
					for (var engine : Engine.values())
					{
						if (line.startsWith(engine.identifier))
						{
							var output = line.substring((engine.identifier+": ").length());
							if (output.startsWith("./"))
							{
								output = output.replace(".", workspace.root);
							}
							generator.generate(game, engine, output);
						}
					}
				}
			}
			catch (IOException e)
			{
				write(e.getMessage());
			}
		}
		else
		{
			for (var engine : Engine.values())
			{
				var defaultPath = Paths.get(workspace.root, "Output", engine.identifier).toString();
				generator.generate(game, engine, defaultPath);
			}
		}
	}
	
	private int character(String text, int offset)
	{
		var count = 0;
		for (var i = 0; i < offset && i < text.length(); i++)
		{
			count++;
			if (text.charAt(i) == '\n')
			{
				count = 0;
			}
		}
		return count;
	}
	
	
	
	private int offset(String text, int line, int character)
	{
		var count = 0;
		for (var i = 0; i < text.length(); i++)
		{
			if (count == line)
			{
				return i+character;
			}
			if (text.charAt(i) == '\n')
			{
				count++;
			}
		}
		return text.length();
	}
	
	private String decode(String path)
	{
		var result = "";
		try
		{
			result = URLDecoder.decode(path.replace("file://", ""),"UTF-8");
		}
		catch (UnsupportedEncodingException e)
		{
			result = "Unsupported encoding UTF-8";
		}

		var os = System.getProperty("os.name");

		if (os.startsWith("Win"))
		{
			result = result.substring(1);
		}

		return result;
	}
	
	private void write(String message)
	{
		if (client != null)
		{
			client.showMessage(new MessageParams(MessageType.Info, message));
		}
	}
}
