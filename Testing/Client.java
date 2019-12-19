
import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

import com.google.inject.Guice;
import com.google.inject.Inject;

import org.eclipse.lsp4j.ApplyWorkspaceEditParams;
import org.eclipse.lsp4j.ClientCapabilities;
import org.eclipse.lsp4j.CompletionCapabilities;
import org.eclipse.lsp4j.CompletionParams;
import org.eclipse.lsp4j.DidChangeConfigurationCapabilities;
import org.eclipse.lsp4j.DidChangeConfigurationParams;
import org.eclipse.lsp4j.DidChangeTextDocumentParams;
import org.eclipse.lsp4j.DidChangeWatchedFilesCapabilities;
import org.eclipse.lsp4j.DidOpenTextDocumentParams;
import org.eclipse.lsp4j.DocumentFormattingParams;
import org.eclipse.lsp4j.DocumentSymbolParams;
import org.eclipse.lsp4j.ExecuteCommandCapabilities;
import org.eclipse.lsp4j.FoldingRangeRequestParams;
import org.eclipse.lsp4j.FormattingCapabilities;
import org.eclipse.lsp4j.FormattingOptions;
import org.eclipse.lsp4j.HoverCapabilities;
import org.eclipse.lsp4j.InitializeParams;
import org.eclipse.lsp4j.InitializedParams;
import org.eclipse.lsp4j.MessageActionItem;
import org.eclipse.lsp4j.MessageParams;
import org.eclipse.lsp4j.Position;
import org.eclipse.lsp4j.PublishDiagnosticsCapabilities;
import org.eclipse.lsp4j.PublishDiagnosticsParams;
import org.eclipse.lsp4j.Range;
import org.eclipse.lsp4j.ShowMessageRequestParams;
import org.eclipse.lsp4j.SymbolCapabilities;
import org.eclipse.lsp4j.SynchronizationCapabilities;
import org.eclipse.lsp4j.TextDocumentClientCapabilities;
import org.eclipse.lsp4j.TextDocumentContentChangeEvent;
import org.eclipse.lsp4j.TextDocumentItem;
import org.eclipse.lsp4j.TextDocumentPositionParams;
import org.eclipse.lsp4j.TextDocumentSaveReason;
import org.eclipse.lsp4j.VersionedTextDocumentIdentifier;
import org.eclipse.lsp4j.WillSaveTextDocumentParams;
import org.eclipse.lsp4j.WorkspaceClientCapabilities;
import org.eclipse.lsp4j.WorkspaceEditCapabilities;
import org.eclipse.lsp4j.WorkspaceFolder;
import org.eclipse.lsp4j.jsonrpc.Launcher;
import org.eclipse.lsp4j.launch.LSPLauncher;
import org.eclipse.lsp4j.services.LanguageClient;
import org.eclipse.xtext.ide.server.LanguageServerImpl;
import org.eclipse.xtext.ide.server.ServerModule;

public class Client implements LanguageClient {

    @Inject
    public LanguageServerImpl server;

    ServerSocket serverSocket;
    Socket socket;

    public static void main(String[] args) 
    {
        var serverModule = new ServerModule();
        var client = Guice.createInjector(serverModule).<Client>getInstance(Client.class);
        client.start();
    }

    public void start() 
    {
        Future<Void> thread = null;
        try
        {
            var writer = new PrintWriter(new File("communication"));
            serverSocket = new ServerSocket(5555);
            var threadPool = Executors.newCachedThreadPool();
            threadPool.execute(() ->
            {
                Socket clientSocket = null;
                try 
                {
                    clientSocket = serverSocket.accept();
                    System.out.println("Client connected");

                    Launcher<LanguageClient> launcher = LSPLauncher.createServerLauncher(server, clientSocket.getInputStream(), clientSocket.getOutputStream(),true, writer);

                    var client = launcher.getRemoteProxy();
                    server.connect(client);

                    launcher.startListening();

                    System.out.println("Out");
                } 
                catch (IOException e) 
                {
					e.printStackTrace();
                }
            });

            threadPool.shutdown();

            System.out.println("Out thread is here");

            socket = new Socket("localhost", 5555);
            var launcher = LSPLauncher.createClientLauncher(this, socket.getInputStream(), socket.getOutputStream(),true, writer);
        
            var end = launcher.getRemoteProxy();
            thread = launcher.startListening();

            var versioned = new VersionedTextDocumentIdentifier();
            versioned.setUri("file:///home/martin/Documents/java/m/hello.m");
            versioned.setVersion(0);

            var params = new InitializeParams();
            params.setRootUri("file:///home/martin/Documents/java/m");
            params.setProcessId((int)ProcessHandle.current().pid());
            var capabilities = new ClientCapabilities();
            var textCapabilities = new TextDocumentClientCapabilities();
            textCapabilities.setPublishDiagnostics(new PublishDiagnosticsCapabilities(true));
            textCapabilities.setSynchronization(new SynchronizationCapabilities(true,true,true,true));
            textCapabilities.setCompletion(new CompletionCapabilities(true));
            textCapabilities.setHover(new HoverCapabilities(true));
            textCapabilities.setFormatting(new FormattingCapabilities(true));
            var workCapabilities = new WorkspaceClientCapabilities();
            workCapabilities.setApplyEdit(true);
            capabilities.setTextDocument(textCapabilities);
            params.setCapabilities(capabilities);
            end.initialize(initializeParams());
            Thread.sleep(1000);
            end.initialized(new InitializedParams());
            Thread.sleep(1000);
            var openParams = new DidOpenTextDocumentParams();
            var textDocument = new TextDocumentItem("file:///home/martin/Documents/java/m/hello.m", "m", 0, "entity{}another{}");
            openParams.setTextDocument(textDocument);
            end.getTextDocumentService().didOpen(openParams);
            Thread.sleep(1000);

            end.getTextDocumentService().documentSymbol(new DocumentSymbolParams(versioned));
            /*
            
            var changeParams = new DidChangeTextDocumentParams();
            changeParams.setTextDocument(versioned);
            var change = new TextDocumentContentChangeEvent();
            change.setRange(new Range(new Position(0,0), new Position(0,0)));
            change.setText("Hello\n");
            change.setRangeLength(6);
            var list = new ArrayList<TextDocumentContentChangeEvent>();
            list.add(change);
            changeParams.setContentChanges(list);
            end.getTextDocumentService().didChange(changeParams);
            Thread.sleep(1000);*/
            end.getTextDocumentService().hover(new TextDocumentPositionParams(versioned, new Position(0,2)));
            end.getTextDocumentService().formatting(new DocumentFormattingParams(versioned, new FormattingOptions(4, true)));
        }
        catch (Exception e) 
        {
            e.printStackTrace();
        }
        var scanner = new Scanner(System.in);
        System.out.println("Type a line to exit: ");
        var line = scanner.nextLine();
        scanner.close();
        System.out.println(line);
        try
        {

            if (thread != null)
            {
                thread.cancel(true);
            }   
            if (serverSocket != null)
            {
                System.out.println("Serversocket closed");
                serverSocket.close();
            }
            if (socket != null)
            {
                System.out.println("Socket closed");
                socket.close();
            }
            server.exit();
        }
        catch (Exception e)
        {
            System.err.println(e);
        }
    }

    @Override
    public void telemetryEvent(Object object) {
        System.out.println("Telemetry");

    }

    @Override
    public void publishDiagnostics(PublishDiagnosticsParams diagnostics) 
    {
        System.out.println(diagnostics.getUri());
        for (var d : diagnostics.getDiagnostics())
        {
            System.out.println(d.getSeverity());
            System.out.println(d.getMessage());
        }
    }

    @Override
    public void showMessage(MessageParams messageParams) {
        System.out.println("Show 1");

    }

    @Override
    public CompletableFuture<MessageActionItem> showMessageRequest(ShowMessageRequestParams requestParams) {
        System.out.println("Show message");
        return null;
    }

    @Override
    public void logMessage(MessageParams message) {
        System.out.println("Logtging");

    }

    InitializeParams initializeParams()
    {
        var params = new InitializeParams();
        
        params.setRootUri("file:///home/martin/Documents/java/m");

        var work = new WorkspaceClientCapabilities();
        work.setApplyEdit(true);
        work.setWorkspaceEdit(new WorkspaceEditCapabilities());
        work.setDidChangeConfiguration(new DidChangeConfigurationCapabilities(true));
        work.setDidChangeWatchedFiles(new DidChangeWatchedFilesCapabilities(true));
        work.setSymbol(new SymbolCapabilities(true));
        work.setExecuteCommand(new ExecuteCommandCapabilities(true));
        work.setConfiguration(true);

        params.setTrace("verbose");
        var folders = new ArrayList<WorkspaceFolder>();
        var folder = new WorkspaceFolder("file:///home/martin/Documents/java/m", "folder");
        folders.add(folder);
        params.setWorkspaceFolders(folders);
        return params;
    }

}