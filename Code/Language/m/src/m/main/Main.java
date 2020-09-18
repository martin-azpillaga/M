package m.main;

import java.io.File;
import java.io.IOException;
import java.io.StringReader;
import java.io.UnsupportedEncodingException;
import java.net.ServerSocket;
import java.net.URLDecoder;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.CompletableFuture;

import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.eclipse.emf.ecore.util.EcoreUtil;
import org.eclipse.lsp4j.CompletionItem;
import org.eclipse.lsp4j.CompletionList;
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
import org.eclipse.lsp4j.Position;
import org.eclipse.lsp4j.PublishDiagnosticsParams;
import org.eclipse.lsp4j.Range;
import org.eclipse.lsp4j.ServerCapabilities;
import org.eclipse.lsp4j.SignatureHelp;
import org.eclipse.lsp4j.SignatureHelpParams;
import org.eclipse.lsp4j.SignatureInformation;
import org.eclipse.lsp4j.TextDocumentIdentifier;
import org.eclipse.lsp4j.TextDocumentSyncKind;
import org.eclipse.lsp4j.WorkspaceFoldersOptions;
import org.eclipse.lsp4j.WorkspaceServerCapabilities;
import org.eclipse.lsp4j.jsonrpc.messages.Either;
import org.eclipse.lsp4j.launch.LSPLauncher;
import org.eclipse.lsp4j.services.LanguageClient;
import org.eclipse.lsp4j.services.LanguageClientAware;
import org.eclipse.lsp4j.services.LanguageServer;
import org.eclipse.lsp4j.services.TextDocumentService;
import org.eclipse.lsp4j.services.WorkspaceService;
import org.eclipse.xtext.nodemodel.INode;
import org.eclipse.xtext.nodemodel.util.NodeModelUtils;
import org.eclipse.xtext.parser.IParser;
import org.eclipse.xtext.resource.XtextResource;
import org.eclipse.xtext.util.CancelIndicator;
import org.eclipse.xtext.validation.CheckMode;
import org.eclipse.xtext.validation.IResourceValidator;

import m.MStandaloneSetup;
import m.generator.Engine;
import m.generator.Game;
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
import m.validation.problems.Problem;
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
	
	static Main instance;
	
	List<CrossReference> crossReferences;
	
	// 1 Connection
	public static void main(String[] arguments)
	{
		instance = new Main();
		
		if (arguments.length == 0)
		{
			var launcher = LSPLauncher.createServerLauncher(instance, System.in, System.out);
			
			var client = launcher.getRemoteProxy();
			instance.connect(client);
			
			launcher.startListening();
		}
		else
		{
			var socketNumber = Integer.parseInt(arguments[0]);
			try (var socket = new ServerSocket(socketNumber))
			{
				var clientSocket = socket.accept();
				var launcher = LSPLauncher.createServerLauncher(instance, clientSocket.getInputStream(), clientSocket.getOutputStream());
				
				var client = launcher.getRemoteProxy();
				instance.connect(client);
				
				launcher.startListening();
			}
			catch (IOException e)
			{
				e.printStackTrace();
			}
		}
	}
	
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
		validator = injector.getInstance(MValidator.class);
		generator = injector.getInstance(MGenerator.class);
		parser = injector.getInstance(IParser.class);
		crossReferences = new ArrayList<>();
		
		
		var capabilities = new ServerCapabilities();
		capabilities.setTextDocumentSync(TextDocumentSyncKind.Full);
		var workspaceFolders = new WorkspaceFoldersOptions();
		workspaceFolders.setSupported(true);
		workspaceFolders.setChangeNotifications(true);
		capabilities.setWorkspace(new WorkspaceServerCapabilities(workspaceFolders));
		//capabilities.setHoverProvider(true);
		//capabilities.setCompletionProvider(new CompletionOptions());
		//capabilities.setSignatureHelpProvider(new SignatureHelpOptions(Arrays.asList("(", ",")));
		
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
		
		try (var folder = Files.walk(Paths.get(root)))
		{
			folder.forEach(f -> 
			{
				if (f.toString().endsWith(".m"))
				{
					try
					{
						localInference(f.toString(), workspace, new String(Files.readAllBytes(Paths.get(f.toString()))));
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
	}

	
	
	
	
	// Never called by VSCode?
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
					write("Added file "+ decode(change.getUri()));
				}
				catch (IOException e)
				{
					write(e.getMessage());
				}
			}
			else if (change.getType() == FileChangeType.Deleted)
			{
				workspace.files.remove(path);
				write("Deleted file "+ path);
			}
		}
	}
	
	
	
	
	
	
	
	
	
	
	@Override
	public void didOpen(DidOpenTextDocumentParams params)
	{
		var filePath = decode(params.getTextDocument().getUri());
			
		var workspace = findWorkspace(filePath);
		
		var text = params.getTextDocument().getText();
		
		var diagnostics = localInference(filePath, workspace, text);
		
		//diagnostics.addAll(globalInference(filePath, workspace, text, true));
		
		client.publishDiagnostics(new PublishDiagnosticsParams(params.getTextDocument().getUri(),diagnostics));
	}
	
	

	@Override
	public void didChange(DidChangeTextDocumentParams params)
	{
		var filePath = decode(params.getTextDocument().getUri());
		
		var workspace = findWorkspace(filePath);
		
		var text = params.getContentChanges().get(0).getText();
		
		var diagnostics = localInference(filePath, workspace, text);
		
		diagnostics.addAll(globalInference(filePath, workspace, text, true));
		
		client.publishDiagnostics(new PublishDiagnosticsParams(params.getTextDocument().getUri(),diagnostics));
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








	
	
	private void generateCode(Game game, Workspace workspace)
	{		
		var path = Paths.get(workspace.root.replace("/c:/", "C:/").replace("/d:/", "D:/").replace("/e:/",  "E:/"), "m.project");
		
		var map = Map.of("Unity", Engine.Unity, "Unreal", Engine.Unreal, "Godot", Engine.Godot);

		if (new File(path.toString()).exists())
		{
			try
			{
				var configuration = Files.readAllLines(path);
				for (var line : configuration)
				{
					for (var engine : map.entrySet())
					{
						if (line.startsWith(engine.getKey()))
						{
							var output = line.substring((engine.getKey()+": ").length());
							if (output.startsWith("./"))
							{
								output = output.replace(".", workspace.root);
							}
							generator.generate(game, engine.getValue(), output);
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
			var defaultPath = Paths.get(workspace.root, "Output", "ClassicUnity").toString();
			
			generator.generate(game, Engine.Unity, defaultPath);
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
					result = MValidator.game.inference.infer(cell).toString();
				}
				else
				{
					result = MValidator.game.inference.infer(value).toString();
				}
			}
			else
			{
				result = MValidator.game.inference.infer(value).toString();
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
	
	private List<Diagnostic> globalInference(String filePath, Workspace workspace, String text, boolean shouldGenerate)
	{
		var diagnostics = new ArrayList<Diagnostic>();
		// Delete previous crossReferences to this file
		
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
					var crossReference = new CrossReference();
					crossReference.fileA = currentFile;
					crossReference.nodeA = data.components.get(component);
					crossReference.bindingA = bindingA;
					
					crossReference.fileB = definedIn.get(component);
					crossReference.nodeB = existing;
					crossReference.bindingB = bindingB;
					
					crossReferences.add(crossReference);
					
					data.components.get(component).bindings.add(bindingA);
					existing.bindings.add(bindingB);
				}
			}
			
			for (var function : data.functions.entrySet())
			{
				totalFunctions.put(function.getKey(), function.getValue());
			}
		}
		
		var inference = new InferenceGraph(totalNodes);
		var problems = inference.check();
		
		
		for (var problem : problems)
		{
			for (var message : problem.messages(Library.ENGLISH))
			{
				if (EcoreUtil.getRoot(message.source, true) != workspace.files.get(filePath).rootObject)
				{
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
				
				diagnostics.add(diagnostic);
				
			}
		}
		
		var hasErrors = false;
		
		for (var i = 0; i < diagnostics.size() && !hasErrors; i++)
		{
			if (diagnostics.get(i).getSeverity() == DiagnosticSeverity.Error)
			{
				hasErrors = true;
			}
		}
		
		if (!hasErrors && shouldGenerate)
		{
			var game = new Game();
			
			for (var component : totalComponents.entrySet())
			{
				var type = inference.infer(component.getValue());
				if (type != null)
				{
					game.components.put(component.getKey(), type);
				}
			}
			
			for (var function : totalFunctions.values())
			{
				game.systems.add(function);
			}
			
			game.library = Library.ENGLISH;
			game.inference = inference;
			
			
			generateCode(game, workspace);
		}
		
		return diagnostics;
	}

	private List<Diagnostic> toDiagnostics(List<Problem> problems, String text)
	{
		var diagnostics = new ArrayList<Diagnostic>();

		for (var problem : problems)
		{
			for (var message : problem.messages(Library.ENGLISH))
			{
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
				
				diagnostics.add(diagnostic);
				
			}
		}
		return diagnostics;
	}
	
	
	
	
	private List<Diagnostic> localInference(String f, Workspace workspace, String fileText)
	{
		var result = new ArrayList<Diagnostic>();
		
		var parseResult = parser.parse(new StringReader(fileText));
		for (var syntaxError : parseResult.getSyntaxErrors())
		{
			var character = character(fileText, syntaxError.getOffset());
			var endCharacter = character(fileText, syntaxError.getEndOffset());
			
			var position = new Position(syntaxError.getStartLine()-1, character);
			var positionEnd = new Position(syntaxError.getStartLine()-1, Math.max(endCharacter, character+1));
			
			var range = new Range(position, positionEnd);
			
			var message = syntaxError.getSyntaxErrorMessage().getMessage();
			
			if (message.equals("mismatched input '<EOF>' expecting '}'"))
			{
				message = "Expecting a statement or }";
			}
			
			result.add(new Diagnostic(range, message));
		}
		
		var file = (m.m.File) parseResult.getRootASTElement();
		
		if (file == null)
		{
			workspace.files.put(f, new InferenceData());
			write("File is null");
		}
		else
		{
			var inferenceData = validator.localValidate(file);
			inferenceData.rootObject = file;

			result.addAll(toDiagnostics(inferenceData.problems, fileText));
			
			workspace.files.put(f, inferenceData);
		}
		
		return result;
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
			write(e.getMessage());
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
		return -1;
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
}
