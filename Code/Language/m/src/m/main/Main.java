package m.main;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.OpenOption;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.concurrent.CompletableFuture;

import org.eclipse.lsp4j.DidChangeConfigurationParams;
import org.eclipse.lsp4j.DidChangeTextDocumentParams;
import org.eclipse.lsp4j.DidChangeWatchedFilesParams;
import org.eclipse.lsp4j.DidCloseTextDocumentParams;
import org.eclipse.lsp4j.DidOpenTextDocumentParams;
import org.eclipse.lsp4j.DidSaveTextDocumentParams;
import org.eclipse.lsp4j.InitializeParams;
import org.eclipse.lsp4j.InitializeResult;
import org.eclipse.lsp4j.ServerCapabilities;
import org.eclipse.lsp4j.TextDocumentSyncKind;
import org.eclipse.lsp4j.jsonrpc.Launcher;
import org.eclipse.lsp4j.launch.LSPLauncher;
import org.eclipse.lsp4j.services.LanguageClient;
import org.eclipse.lsp4j.services.LanguageClientAware;
import org.eclipse.lsp4j.services.LanguageServer;
import org.eclipse.lsp4j.services.TextDocumentService;
import org.eclipse.lsp4j.services.WorkspaceService;

public class Main
{
	public static void main(String[] arguments) throws IOException
	{
		LSPLauncher.createServerLauncher(new Server(), System.in, System.out).startListening();
	}
}

class Server implements LanguageServer, LanguageClientAware
{
	DocumentService document;
	WorkspaceService workspace;
	
	public Server()
	{
		document = new DocumentService();
		workspace = new Workspace();
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
		return document;
	}

	@Override
	public WorkspaceService getWorkspaceService() {
		return workspace;
	}

	@Override
	public void connect(LanguageClient client) {
		try {
			Files.write(Paths.get("communication.t"), "Hello".getBytes());
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
}

class DocumentService implements TextDocumentService
{

	@Override
	public void didOpen(DidOpenTextDocumentParams params) {
		Server.write("didOpen");
	}

	@Override
	public void didChange(DidChangeTextDocumentParams params) {
		Server.write("didChange");
	}

	@Override
	public void didClose(DidCloseTextDocumentParams params) {
		Server.write("didClose");
	}

	@Override
	public void didSave(DidSaveTextDocumentParams params) {
		Server.write("didSave");
	}
}

class Workspace implements WorkspaceService
{

	@Override
	public void didChangeConfiguration(DidChangeConfigurationParams params) {
		Server.write("workspace configuration changed");
		
	}

	@Override
	public void didChangeWatchedFiles(DidChangeWatchedFilesParams params) {
		Server.write("workspace files changed");
		
	}
}
