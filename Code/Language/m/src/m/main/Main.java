package m.main;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.concurrent.CompletableFuture;

import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.eclipse.lsp4j.DidChangeConfigurationParams;
import org.eclipse.lsp4j.DidChangeTextDocumentParams;
import org.eclipse.lsp4j.DidChangeWatchedFilesParams;
import org.eclipse.lsp4j.DidCloseTextDocumentParams;
import org.eclipse.lsp4j.DidOpenTextDocumentParams;
import org.eclipse.lsp4j.DidSaveTextDocumentParams;
import org.eclipse.lsp4j.Hover;
import org.eclipse.lsp4j.HoverParams;
import org.eclipse.lsp4j.InitializeParams;
import org.eclipse.lsp4j.InitializeResult;
import org.eclipse.lsp4j.MarkupContent;
import org.eclipse.lsp4j.Position;
import org.eclipse.lsp4j.ServerCapabilities;
import org.eclipse.lsp4j.TextDocumentSyncKind;
import org.eclipse.lsp4j.launch.LSPLauncher;
import org.eclipse.lsp4j.services.LanguageClient;
import org.eclipse.lsp4j.services.LanguageClientAware;
import org.eclipse.lsp4j.services.LanguageServer;
import org.eclipse.lsp4j.services.TextDocumentService;
import org.eclipse.lsp4j.services.WorkspaceService;
import org.eclipse.xtext.EcoreUtil2;
import org.eclipse.xtext.RuleCall;
import org.eclipse.xtext.nodemodel.util.NodeModelUtils;
import org.eclipse.xtext.parser.IParser;
import org.eclipse.xtext.resource.XtextResource;
import org.eclipse.xtext.util.CancelIndicator;
import org.eclipse.xtext.util.UriExtensions;
import org.eclipse.xtext.validation.CheckMode;
import org.eclipse.xtext.validation.IResourceValidator;

import m.MStandaloneSetup;
import m.m.Cell;
import m.m.Function;
import m.m.Value;
import m.validation.MValidator;

public class Main implements LanguageServer, LanguageClientAware, TextDocumentService, WorkspaceService
{	
	public static void main(String[] arguments) throws IOException
	{
		LSPLauncher.createServerLauncher(new Main(), System.in, System.out).startListening();
	}
	
	public static void write(String message)
	{
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
		write("Initialize");
		
		var capabilities = new ServerCapabilities();
		capabilities.setHoverProvider(true);
		capabilities.setTextDocumentSync(TextDocumentSyncKind.Full);
		
		return CompletableFuture.supplyAsync(()->new InitializeResult(capabilities));
	}

	@Override
	public CompletableFuture<Object> shutdown() {
		write("shutdown");
		return CompletableFuture.supplyAsync(() -> Boolean.TRUE);
	}

	@Override
	public void exit() {
		write("exit");
	}

	@Override
	public TextDocumentService getTextDocumentService() {
		return this;
	}

	@Override
	public WorkspaceService getWorkspaceService() {
		return this;
	}

	@Override
	public void connect(LanguageClient client) {
		Main.write("connect");
	}
	
	
	
	@Override
	public void didOpen(DidOpenTextDocumentParams params) {
		Main.write("didOpen");
	}

	@Override
	public void didChange(DidChangeTextDocumentParams params) {
		Main.write("didChange");
	}

	@Override
	public void didClose(DidCloseTextDocumentParams params) {
		Main.write("didClose");
	}

	@Override
	public void didSave(DidSaveTextDocumentParams params) {
		Main.write("didSave");
	}
	
	@Override
	public CompletableFuture<Hover> hover(HoverParams params)
	{
		Main.write("hover");
		
		var injector = new MStandaloneSetup().createInjectorAndDoEMFRegistration();
		var resourceSet = injector.getInstance(ResourceSet.class);
		var validator = injector.getInstance(IResourceValidator.class);
		
		
		var read = "";
		InputStream input = null;
		XtextResource resource = null;
		try {
			input = Files.newInputStream(Paths.get(params.getTextDocument().getUri().replace("file://", "")));
			read = Files.readString(Paths.get(params.getTextDocument().getUri().replace("file://", "")));
			read += "\n\n" + input.readAllBytes();
			resource = (XtextResource) resourceSet.createResource(URI.createURI(params.getTextDocument().getUri()));
			resource.load(null);
		} catch (IOException e) {
			read = "IO Exception";
		}
		
		
		for(var syntaxError : resource.getErrors())
		{
			read += "\n\n"+syntaxError.getMessage() + " : " + syntaxError.getLocation();
		}
		
		var diagnostics = validator.validate(resource, CheckMode.ALL, CancelIndicator.NullImpl);
		
		for (var validationError : diagnostics)
		{
			read += "\n\n"+validationError.getMessage();
		}
		var parseResult = resource.getParseResult();
		
		if (resource.getErrors().size() > 0)
		{
			var h = new Hover();
			h.setContents(new MarkupContent("markdown", "solve syntax errors first"+read));
			return CompletableFuture.supplyAsync(()->h);
		}
		var text = parseResult.getRootNode().getText();
		var offset = offset(text, params.getPosition().getLine(), params.getPosition().getCharacter());
		if (offset >= text.length())
		{
			var h = new Hover();
			h.setContents(new MarkupContent("markdown", "end of file"));
			return CompletableFuture.supplyAsync(()->h);
		}
		var node = NodeModelUtils.findLeafNodeAtOffset(parseResult.getRootNode(), offset);

		var semantic = node.getSemanticElement();
		
		if (semantic instanceof Function)
		{
			var function = (Function) semantic;
			if (node.getText().equals(function.getName()))
			{
				read = "Function " + function.getName();
			}
			else
			{
				read = "Inside function " + function.getName(); 
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
					read = "Type: "+MValidator.game.inference.infer(cell);
				}
				else
				{
					read = "Type: "+MValidator.game.inference.infer(value);
				}
			}
			else
			{
				read = "Type: "+MValidator.game.inference.infer(value);
			}
		}
		else
		{
			read = semantic.toString();
		}
		
		var hover = new Hover();
		var contents = new MarkupContent("markdown", read);
		hover.setContents(contents);
		return CompletableFuture.supplyAsync(() -> hover);
	}
	
	@Override
	public void didChangeConfiguration(DidChangeConfigurationParams params) {
		Main.write("workspace configuration changed");
		
	}

	@Override
	public void didChangeWatchedFiles(DidChangeWatchedFilesParams params) {
		Main.write("workspace files changed");
		
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
