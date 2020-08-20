package m.main;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CompletableFuture;

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
import org.eclipse.lsp4j.FileChangeType;
import org.eclipse.lsp4j.Hover;
import org.eclipse.lsp4j.HoverParams;
import org.eclipse.lsp4j.InitializeParams;
import org.eclipse.lsp4j.InitializeResult;
import org.eclipse.lsp4j.MarkupContent;
import org.eclipse.lsp4j.MessageActionItem;
import org.eclipse.lsp4j.MessageParams;
import org.eclipse.lsp4j.MessageType;
import org.eclipse.lsp4j.Position;
import org.eclipse.lsp4j.PublishDiagnosticsParams;
import org.eclipse.lsp4j.Range;
import org.eclipse.lsp4j.SemanticHighlightingInformation;
import org.eclipse.lsp4j.SemanticHighlightingParams;
import org.eclipse.lsp4j.ServerCapabilities;
import org.eclipse.lsp4j.ShowMessageRequestParams;
import org.eclipse.lsp4j.TextDocumentIdentifier;
import org.eclipse.lsp4j.TextDocumentSyncKind;
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
import m.generator.MGenerator;
import m.library.symbols.Component;
import m.m.Assignment;
import m.m.Block;
import m.m.Cell;
import m.m.Function;
import m.m.Value;
import m.validation.MValidator;

public class Main implements LanguageServer, LanguageClientAware, TextDocumentService, WorkspaceService
{
	LanguageClient client;
	
	List<Path> workspaces;
	List<String> files;
	
	IParser parser;
	MValidator validator;
	MGenerator generator;
	
	public static void main(String[] arguments) throws IOException
	{
		var server = new Main();
		var launcher = LSPLauncher.createServerLauncher(server, System.in, System.out);
		var client = launcher.getRemoteProxy();
		
		((LanguageClientAware)server).connect(client);
		
		launcher.startListening();
	}
	
	public void write(String message)
	{
		if (client != null)
		{
			client.showMessage(new MessageParams(MessageType.Info, message));
		}
		
		try {
			Files.writeString(Paths.get("communication.t"), message+"\n", StandardOpenOption.APPEND);
		}
		catch (IOException e)
		{
			e.printStackTrace();
		}
	}
	
	@Override
	public CompletableFuture<InitializeResult> initialize(InitializeParams params)
	{
		var injector = new MStandaloneSetup().createInjectorAndDoEMFRegistration();
		validator = injector.getInstance(MValidator.class);
		generator = injector.getInstance(MGenerator.class);
		parser = injector.getInstance(IParser.class);
		
		parser.parse(null).getRootNode();
		
		var capabilities = new ServerCapabilities();
		capabilities.setTextDocumentSync(TextDocumentSyncKind.Full);
		
		capabilities.setHoverProvider(true);
		capabilities.setCompletionProvider(new CompletionOptions());
		
		workspaces = new ArrayList<Path>();
		files = new ArrayList<String>();
		
		for (var folder : params.getWorkspaceFolders())
		{
			var dir = Paths.get(folder.getUri().replace("file://", ""));
			
			workspaces.add(dir);
			
			try {
				// Only in workspace folder
				Files.list(dir).forEach(f -> 
				{
					if (f.toString().endsWith(".m"))
					{
						files.add(f.toString());
						//write(f.toString());
					}
				});
				
				// Also subfolders
				Files.walk(dir).forEach(f -> 
				{
					if (f.toString().endsWith(".m"))
					{
						//write(f.toString());
					}
				});
			} catch (IOException e) {
				// TODO Auto-generated catch block
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
			workspaces.add(Paths.get(added.getUri().replace("file://", "")));
		}
		for (var removed : params.getEvent().getRemoved())
		{
			workspaces.remove(Paths.get(removed.getUri().replace("file://", "")));
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
				files.add(change.getUri().replace("file://", ""));
			}
			else if (change.getType() == FileChangeType.Deleted)
			{
				files.remove(change.getUri().replace("file://", ""));
			}
		}
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	@Override
	public void didOpen(DidOpenTextDocumentParams params)
	{
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
		client.publishDiagnostics(diagnostics);
	}

	@Override
	public void didChange(DidChangeTextDocumentParams params)
	{
		/* Semantic highlighting
		var highlightParams = new SemanticHighlightingParams();
		highlightParams.setTextDocument(params.getTextDocument());
		var lines = new ArrayList<SemanticHighlightingInformation>();
		var line = new SemanticHighlightingInformation();
		line.setLine(0);
		lines.add(line);
		highlightParams.setLines(lines);
		client.semanticHighlighting(highlightParams);
		*/
		var text = params.getContentChanges().get(0).getText();
		var targetStream = new ByteArrayInputStream(text.getBytes());

		
		var injector = new MStandaloneSetup().createInjectorAndDoEMFRegistration();
		var resourceSet = injector.getInstance(ResourceSet.class);
		var validator = injector.getInstance(IResourceValidator.class);
		
		XtextResource resource = null;
		try {
			resource = (XtextResource) resourceSet.createResource(URI.createURI(params.getTextDocument().getUri()));
			resource.load(targetStream,null);
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
		client.publishDiagnostics(diagnostics);
	}

	@Override
	public void didClose(DidCloseTextDocumentParams params)
	{
		
	}

	@Override
	public void didSave(DidSaveTextDocumentParams params)
	{
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
