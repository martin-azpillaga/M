package m.main;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.concurrent.CompletableFuture;

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
import org.eclipse.lsp4j.ServerCapabilities;
import org.eclipse.lsp4j.TextDocumentSyncKind;
import org.eclipse.lsp4j.launch.LSPLauncher;
import org.eclipse.lsp4j.services.LanguageClient;
import org.eclipse.lsp4j.services.LanguageClientAware;
import org.eclipse.lsp4j.services.LanguageServer;
import org.eclipse.lsp4j.services.TextDocumentService;
import org.eclipse.lsp4j.services.WorkspaceService;

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
		var hover = new Hover();
		var contents = new MarkupContent("markdown", "**hello** *world*\n"+params.getTextDocument().getUri()+"\n"+params.getPosition().getLine()+" : "+params.getPosition().getCharacter());
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
}
