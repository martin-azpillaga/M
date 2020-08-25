package m.main;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.eclipse.lsp4j.CompletionItem;
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
import org.eclipse.lsp4j.ExecuteCommandParams;
import org.eclipse.lsp4j.FileChangeType;
import org.eclipse.lsp4j.Hover;
import org.eclipse.lsp4j.HoverParams;
import org.eclipse.lsp4j.InitializeParams;
import org.eclipse.lsp4j.InitializeResult;
import org.eclipse.lsp4j.MarkupContent;
import org.eclipse.lsp4j.MessageParams;
import org.eclipse.lsp4j.MessageType;
import org.eclipse.lsp4j.ParameterInformation;
import org.eclipse.lsp4j.Position;
import org.eclipse.lsp4j.PublishDiagnosticsParams;
import org.eclipse.lsp4j.Range;
import org.eclipse.lsp4j.ServerCapabilities;
import org.eclipse.lsp4j.SignatureHelp;
import org.eclipse.lsp4j.SignatureHelpOptions;
import org.eclipse.lsp4j.SignatureHelpParams;
import org.eclipse.lsp4j.SignatureInformation;
import org.eclipse.lsp4j.SymbolInformation;
import org.eclipse.lsp4j.TextDocumentIdentifier;
import org.eclipse.lsp4j.TextDocumentSyncKind;
import org.eclipse.lsp4j.WorkspaceSymbolParams;
import org.eclipse.lsp4j.jsonrpc.messages.Either;
import org.eclipse.lsp4j.launch.LSPLauncher;
import org.eclipse.lsp4j.services.LanguageClient;
import org.eclipse.lsp4j.services.LanguageClientAware;
import org.eclipse.lsp4j.services.LanguageServer;
import org.eclipse.lsp4j.services.TextDocumentService;
import org.eclipse.lsp4j.services.WorkspaceService;
import org.eclipse.xtext.EcoreUtil2;
import org.eclipse.xtext.diagnostics.Severity;
import org.eclipse.xtext.generator.JavaIoFileSystemAccess;
import org.eclipse.xtext.nodemodel.INode;
import org.eclipse.xtext.nodemodel.util.NodeModelUtils;
import org.eclipse.xtext.parser.IParser;
import org.eclipse.xtext.resource.EObjectAtOffsetHelper;
import org.eclipse.xtext.resource.XtextResource;
import org.eclipse.xtext.util.CancelIndicator;
import org.eclipse.xtext.validation.CheckMode;
import org.eclipse.xtext.validation.IResourceValidator;

import com.google.common.collect.HashBiMap;
import com.google.inject.spi.Message;

import m.MStandaloneSetup;
import m.generator.Engine;
import m.generator.MGenerator;
import m.library.Library;
import m.library.symbols.Component;
import m.m.Assignment;
import m.m.Block;
import m.m.Cell;
import m.m.Function;
import m.m.Value;
import m.validation.InferenceGraph;
import m.validation.MValidator;
import m.validation.problems.errors.IncompatibleTypes;
import m.validation.problems.errors.UndecidableType;
import m.validation.rules.Binding;
import m.validation.rules.Binding.BindingReason;
import m.validation.rules.ExpressionNode;

public class Main implements LanguageServer, LanguageClientAware, TextDocumentService, WorkspaceService
{
	LanguageClient client;
	
	List<Workspace> workspaces;
	
	IParser parser;
	MValidator validator;
	MGenerator generator;
	JavaIoFileSystemAccess fileSystem;
	
	static Main instance;
	
	public static void main(String[] arguments) throws IOException
	{
		var server = new Main();
		instance = server;
		var launcher = LSPLauncher.createServerLauncher(server, System.in, System.out);
		var client = launcher.getRemoteProxy();
		
		((LanguageClientAware)server).connect(client);
		
		launcher.startListening();
	}
	
	public static void writeMessage(String message)
	{
		instance.write(message);
	}
	
	private void write(String message)
	{
		if (client != null)
		{
			client.showMessage(new MessageParams(MessageType.Info, message));
		}
	}
	
	@Override
	public CompletableFuture<InitializeResult> initialize(InitializeParams params)
	{
		var injector = new MStandaloneSetup().createInjectorAndDoEMFRegistration();
		validator = injector.getInstance(MValidator.class);
		generator = injector.getInstance(MGenerator.class);
		parser = injector.getInstance(IParser.class);
		fileSystem = injector.getInstance(JavaIoFileSystemAccess.class);
		
		
		var capabilities = new ServerCapabilities();
		capabilities.setTextDocumentSync(TextDocumentSyncKind.Full);
		//capabilities.setHoverProvider(true);
		//capabilities.setCompletionProvider(new CompletionOptions());
		//capabilities.setSignatureHelpProvider(new SignatureHelpOptions(Arrays.asList("(", ",")));
		
		workspaces = new ArrayList<>();
		
		for (var folder : params.getWorkspaceFolders())
		{
			String root = "";
			try {
				root = URLDecoder.decode(folder.getUri().replace("file://", ""),"UTF-8");
			} catch (UnsupportedEncodingException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			
			var workspace = new Workspace(root);
			workspaces.add(workspace);
			
			try
			{
				Files.walk(Paths.get(root)).forEach(f -> 
				{
					if (f.toString().endsWith(".m"))
					{
						try
						{
							var parseResult = parser.parse(new InputStreamReader(new FileInputStream(f.toString())));
							for (var syntaxError : parseResult.getSyntaxErrors())
							{
								write(f.toString()+": "+syntaxError.toString());
							}
							
							var file = (m.m.File) parseResult.getRootASTElement();
							
							if (file == null)
							{
								workspace.files.put(f.toString(), new InferenceData());
								write(f.toString()+": empty");
							}
							else
							{
								var inferenceData = validator.localValidate(file);
								
								workspace.files.put(f.toString(), inferenceData);
								write(f.toString()+": "+inferenceData.nodes.size() + " nodes and "+inferenceData.components.size()+" components");
							}
						}
						catch (FileNotFoundException e)
						{
							e.printStackTrace();
						}
					}
				});
			}
			catch (IOException e)
			{
				e.printStackTrace();
			}
		}
		
		return CompletableFuture.supplyAsync(()->new InitializeResult(capabilities));
	}
	
	@Override
	public void connect(LanguageClient client)
	{
		this.client = client;
	}

	@Override
	public CompletableFuture<Object> shutdown()
	{
		return CompletableFuture.supplyAsync(() -> Boolean.TRUE);
	}

	@Override
	public void exit()
	{
		
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

	
	
	
	
	// Workspaces, initialized in initialize call
	
	
	@Override
	public void didChangeWorkspaceFolders(DidChangeWorkspaceFoldersParams params)
	{
		write("changed workspace");
		for (var added : params.getEvent().getAdded())
		{
			//workspaces.add(added.getUri().replace("file://", ""));
		}
		for (var removed : params.getEvent().getRemoved())
		{
			workspaces.remove(removed.getUri().replace("file://", ""));
		}
	}
	
	@Override
	public void didChangeConfiguration(DidChangeConfigurationParams params)
	{
		
	}

	@Override
	public void didChangeWatchedFiles(DidChangeWatchedFilesParams params)
	{
		for (var change : params.getChanges())
		{
			if (change.getType() == FileChangeType.Created)
			{
				//files.add(change.getUri().replace("file://", ""));
			}
			else if (change.getType() == FileChangeType.Deleted)
			{
				//files.remove(change.getUri().replace("file://", ""));
			}
		}
	}
	
	public CompletableFuture<Object> executeCommand(ExecuteCommandParams params)
	{
		write("executing command ...");
		
		return CompletableFuture.supplyAsync(() -> Boolean.TRUE);
	}
	
	
	public CompletableFuture<List<? extends SymbolInformation>> symbol(WorkspaceSymbolParams params)
	{
		write("symbol information");
		var list = new ArrayList<SymbolInformation>();
		
		
		return CompletableFuture.supplyAsync(() -> list);
	}

	
	
	
	
	
	
	
	
	@Override
	public void didOpen(DidOpenTextDocumentParams params)
	{
		/*
		var injector = new MStandaloneSetup().createInjectorAndDoEMFRegistration();
		var resourceSet = injector.getInstance(ResourceSet.class);
		var validator = injector.getInstance(IResourceValidator.class);
		
		XtextResource resource = null;
		try {
			resource = (XtextResource) resourceSet.createResource(URI.createURI(params.getTextDocument().getUri()));
			resource.load(null);
		} catch (IOException e) {
			
		}
		var issues = validator.validate(resource, CheckMode.ALL, CancelIndicator.NullImpl);
		
		var list = new ArrayList<Diagnostic>();
		
		for (var issue : issues)
		{
			var position = new Position(issue.getLineNumber()-1, issue.getColumn()-1);
			var positionEnd = new Position(issue.getLineNumberEnd()-1, issue.getColumnEnd()-1);
			
			var range = new Range(position, positionEnd);
			
			var severity = DiagnosticSeverity.Information;
			switch (issue.getSeverity())
			{
			case INFO: severity = DiagnosticSeverity.Information; break;
			case WARNING: severity = DiagnosticSeverity.Warning; break;
			case ERROR: severity = DiagnosticSeverity.Error; break;
			case IGNORE: break;
			}
			
			var diagnostic = new Diagnostic(range, issue.getMessage(), severity, "");
			list.add(diagnostic);
		}
		
		
		var info = new Diagnostic(new Range(new Position(0, 0), new Position(0,3)), "Thanks for using M", DiagnosticSeverity.Hint, "");
		list.add(info);
		var diagnostics = new PublishDiagnosticsParams(params.getTextDocument().getUri(),list);
		client.publishDiagnostics(diagnostics);*/
	}

	@Override
	public void didChange(DidChangeTextDocumentParams params)
	{
		String filePath = "";
		try
		{
			filePath = URLDecoder.decode(params.getTextDocument().getUri().replace("file://", ""), "UTF-8");
		}
		catch (UnsupportedEncodingException e)
		{
			e.printStackTrace();
		}
		
		write(filePath + " changed");
		
		Workspace workspace = null;
		var found = false;
		for (var i = 0; i < workspaces.size() && !found; i++)
		{
			if (filePath.startsWith(workspaces.get(i).root))
			{
				workspace = workspaces.get(i);
				found = true;
			}
		}
		
		if (workspace == null)
		{
			write("Could not find workspace for "+ filePath);
			return;
		}
		
		write("workspace has "+workspace.files.size()+ " files");
		
		var text = params.getContentChanges().get(0).getText();
		var targetStream = new ByteArrayInputStream(text.getBytes());
		var diagnostics = new ArrayList<Diagnostic>();
		
		var parseResult = parser.parse(new InputStreamReader(targetStream));
		
		for (var syntaxError : parseResult.getSyntaxErrors())
		{
			var character = character(text, syntaxError.getOffset());
			var endCharacter = character(text, syntaxError.getEndOffset());
			
			var position = new Position(syntaxError.getStartLine()-1, character);
			var positionEnd = new Position(syntaxError.getStartLine()-1, Math.max(endCharacter, character+1));
			
			var range = new Range(position, positionEnd);
			
			var message = syntaxError.getSyntaxErrorMessage().getMessage();
			
			if (message.equals("mismatched input '<EOF>' expecting '}'"))
			{
				message = "Introduce another statement or \nWrite } to finish statements";
			}
			
			diagnostics.add(new Diagnostic(range, message));
		}
		
		write("Found "+ parseResult.getSyntaxErrors() + " syntax errors");
		
		var file = (m.m.File) parseResult.getRootASTElement();
		
		var inferenceData = validator.localValidate(file);
		
		write("Validation complete");
		
		// Dangling references from files that reference nodes from previous versions of a file
		workspace.files.put(filePath, inferenceData);
		
		var totalNodes = new ArrayList<ExpressionNode>();
		var totalComponents = new HashMap<String, ExpressionNode>();
		
		var i = 0;
		for (var data : workspace.files.values())
		{
			write("File "+i++);
			totalNodes.addAll(data.nodes);
			
			for (var component : data.components.keySet())
			{
				var existing = totalComponents.get(component);
				if (existing == null)
				{
					totalComponents.put(component, data.components.get(component));
				}
				else
				{
					data.components.get(component).bindings.add(new Binding(existing, BindingReason.SAME_COMPONENT));
					existing.bindings.add(new Binding(data.components.get(component), BindingReason.SAME_COMPONENT));
				}
			}
		}
		
		var inference = new InferenceGraph(totalNodes);
		var problems = inference.check();
		
		write("Inference problems: "+problems.size());
		
		for (var problem : problems)
		{
			for (var message : problem.messages(Library.ENGLISH))
			{
				if (EcoreUtil2.getRoot(message.source, true) != file)
				{
					write("Node "+message.source+" is in another file");
					continue;
				}
				var node = NodeModelUtils.getNode(message.source);
				if (node == null)
				{
					write("Node for "+message.source+ "not found");
					continue;
				}
				var range = new Range(new Position(node.getStartLine()-1, character(text,node.getOffset())), new Position(node.getEndLine()-1, character(text,node.getEndOffset())));
				var severity = DiagnosticSeverity.Information;
				switch (message.severity)
				{
				case INFO: severity = DiagnosticSeverity.Information; break;
				case WARNING: severity = DiagnosticSeverity.Warning; break;
				case ERROR: severity = DiagnosticSeverity.Error; break;
				}
				var diagnostic = new Diagnostic(range, message.message, severity, "");
				write("Diagnostic at "+node.getText()+" at "+range);
				
				diagnostics.add(diagnostic);
				
			}
		}
		
		var hasErrors = false;
		
		for (var issue : problems)
		{
			for (var message : issue.messages(Library.ENGLISH))
			{
				if (message.severity == m.validation.problems.ProblemMessage.Severity.ERROR)
				{
					hasErrors = true;
				}
			}
		}

		if (!hasErrors)
		{
			write("Generate code");
			//generateCode();
		}
		
		var info = new Diagnostic(new Range(new Position(0, 0), new Position(0,3)), "Thanks for using M", DiagnosticSeverity.Hint, "");
		diagnostics.add(info);
		var diagnosticParams = new PublishDiagnosticsParams(params.getTextDocument().getUri(),diagnostics);
		client.publishDiagnostics(diagnosticParams);
		/*
		var issues = inferenceData.problems;
		
		var list = new ArrayList<Diagnostic>();
		
		for (var issue : issues)
		{
			var position = new Position(issue.getLineNumber()-1, issue.getColumn()-1);
			var positionEnd = new Position(issue.getLineNumberEnd()-1, issue.getColumnEnd()-1);
			
			var range = new Range(position, positionEnd);
			
			var severity = DiagnosticSeverity.Information;
			switch (issue.getSeverity())
			{
			case INFO: severity = DiagnosticSeverity.Information; break;
			case WARNING: severity = DiagnosticSeverity.Warning; break;
			case ERROR: severity = DiagnosticSeverity.Error; break;
			case IGNORE: break;
			}
			
			var diagnostic = new Diagnostic(range, issue.getMessage(), severity, "");
			list.add(diagnostic);
		}
		
		var hasErrors = false;
		
		for (var issue : issues)
		{
			if (issue.getSeverity() == Severity.ERROR)
			{
				hasErrors = true;
			}
		}

		if (!hasErrors)
		{
			generateCode();
		}
		
		
		var info = new Diagnostic(new Range(new Position(0, 0), new Position(0,3)), "Thanks for using M", DiagnosticSeverity.Hint, "");
		list.add(info);
		var diagnostics = new PublishDiagnosticsParams(params.getTextDocument().getUri(),list);
		client.publishDiagnostics(diagnostics);
		*/
	}

	@Override
	public void didClose(DidCloseTextDocumentParams params)
	{
		
	}

	@Override
	public void didSave(DidSaveTextDocumentParams params)
	{
		
	}
	
	
	private void generateCode()
	{
		/* Apply edit to remove a file
		 * 
		 
		var edit = new WorkspaceEdit();
		var operation = new DeleteFile("");
		var operations = new ArrayList<Either<TextDocumentEdit,ResourceOperation>>();
		operations.add(Either.forRight(operation));
		edit.setDocumentChanges(operations);
		client.applyEdit(new ApplyWorkspaceEditParams(edit));
		*/
		
		write("generator called");
		var game = MValidator.game;
		if (game == null) return;
		
		write("game is ok");
		
		Path path = Paths.get(workspaces.get(0).root.replace("/c:/", "C:/").replace("/d:/", "D:/").replace("/e:/",  "E:/"), "m.project");
		
		
		write("Finding project file: "+path.toString());

		if (new File(path.toString()).exists())
		{
			try
			{
				var configuration = Files.readAllLines(path);
				for (var line : configuration)
				{
					if (line.startsWith("Unity: "))
					{
						var unity = line.substring("Unity: ".length());
						fileSystem.setOutputPath(unity);
						
						write("Generating code at: "+unity);

						generator.generate(game, fileSystem, Engine.Unity);
						
						write("Generated");
					}
					else if (line.startsWith("Unreal: "))
					{
						var unreal = line.substring("Unreal: ".length());
						fileSystem.setOutputPath(unreal);

						generator.generate(game, fileSystem, Engine.Unreal);
					}
					else if (line.startsWith("Godot: "))
					{
						var godot = line.substring("Godot: ".length());
						fileSystem.setOutputPath(godot);

						generator.generate(game, fileSystem, Engine.Godot);
					}
				}
			}
			catch (IOException e)
			{
			}
		}
		else
		{
			var defaultPath = Paths.get(workspaces.get(0).root, "src-gen", "ClassicUnity").toString();
			
			write("Generating in default path: "+defaultPath);
			fileSystem.setOutputPath(defaultPath);
			generator.generate(game, fileSystem, Engine.Unity);
			write("Generated");
		}
	}
	
	
	
	
	
	
	@Override
	public CompletableFuture<Hover> hover(HoverParams params)
	{
		var result = "";
		
		var node = node(params.getTextDocument(), params.getPosition(), false);
		
		if (MValidator.game == null)
		{
			return CompletableFuture.supplyAsync(() -> new Hover());
		}
		
		var semantic = node.getSemanticElement();
		
		if (semantic instanceof Function)
		{
			var function = (Function) semantic;
			if (node.getText().equals(function.getName()))
			{
				result = "Function " + function.getName();
			}
			else
			{
				result = "Inside function " + function.getName(); 
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
					result = "Type: "+MValidator.game.inference.infer(cell);
				}
				else
				{
					result = "Type: "+MValidator.game.inference.infer(value);
				}
			}
			else
			{
				result = "Type: "+MValidator.game.inference.infer(value);
			}
		}
		else
		{
			result = semantic.toString();
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
		
		var node = node(params.getTextDocument(), params.getPosition(), true);
		var semantic = node.getSemanticElement();
		
		if (semantic == null)
		{
			result.add(new CompletionItem("semantic is null\n\n"+node.getText()));
		}
		else if (semantic instanceof Cell)
		{
			for (var component : Component.values())
			{
				// Try bimaps again
				result.add(new CompletionItem(component.name()));
			}
		}
		else
		{
			if (semantic instanceof Block)
			{
				var block = (Block) semantic;
				
				result.add(new CompletionItem(node.getText()+ " " + block.getStatements().size()));
				if (block.getStatements().size() > 0)
				{
					var ass = (Assignment) block.getStatements().get(0);
					result.add(new CompletionItem(ass.getAtom().toString()));
					result.add(new CompletionItem("Exp: "+ass.getExpression()));
					result.add(new CompletionItem("Node: "+node.getText()));
				}
			}
			result.add(new CompletionItem(node.getText() + " " + semantic.toString()));
		}
		
		return CompletableFuture.supplyAsync(() -> Either.forLeft(result));
	}
	
	
	@Override
	public CompletableFuture<SignatureHelp> signatureHelp(SignatureHelpParams params)
	{
		write("signature triggered");
		var list = new ArrayList<SignatureInformation>();
		var parameters = new ArrayList<ParameterInformation>();
		var info = new SignatureInformation("enableParameter", "enables the given boolean parameter in the animator", parameters);
		list.add(info);
		var help = new SignatureHelp();
		help.setSignatures(list);
		return CompletableFuture.supplyAsync(()->help);
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	private INode node(TextDocumentIdentifier document, Position position, boolean minusOne)
	{
		var injector = new MStandaloneSetup().createInjectorAndDoEMFRegistration();
		var resourceSet = injector.getInstance(ResourceSet.class);
		var validator = injector.getInstance(IResourceValidator.class);
		
		XtextResource resource = null;
		try {
			resource = (XtextResource) resourceSet.createResource(URI.createURI(document.getUri()));
			resource.load(null);
		} catch (IOException e) {
			
		}
		validator.validate(resource, CheckMode.ALL, CancelIndicator.NullImpl);
		var parseResult = resource.getParseResult();
		
		var text = parseResult.getRootNode().getText();
		var offset = offset(text, position.getLine(), position.getCharacter());
		if (minusOne) {offset --; }
		var node = NodeModelUtils.findLeafNodeAtOffset(parseResult.getRootNode(), offset);
		
		return node;
	}
	
	private int character(String text, int offset)
	{
		var count = 0;
		for (var i = 0; i < offset; i++)
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
		return -1;
	}
}
