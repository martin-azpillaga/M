
import java.io.File;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.Executors;

import com.google.inject.Guice;
import com.google.inject.Inject;

import org.eclipse.lsp4j.DidChangeConfigurationCapabilities;
import org.eclipse.lsp4j.DidChangeWatchedFilesCapabilities;
import org.eclipse.lsp4j.ExecuteCommandCapabilities;
import org.eclipse.lsp4j.InitializeParams;
import org.eclipse.lsp4j.InitializedParams;
import org.eclipse.lsp4j.MessageActionItem;
import org.eclipse.lsp4j.MessageParams;
import org.eclipse.lsp4j.PublishDiagnosticsParams;
import org.eclipse.lsp4j.ShowMessageRequestParams;
import org.eclipse.lsp4j.SymbolCapabilities;
import org.eclipse.lsp4j.WorkspaceClientCapabilities;
import org.eclipse.lsp4j.WorkspaceEditCapabilities;
import org.eclipse.lsp4j.WorkspaceFolder;
import org.eclipse.lsp4j.jsonrpc.Launcher;
import org.eclipse.lsp4j.launch.LSPLauncher;
import org.eclipse.lsp4j.services.LanguageClient;
import org.eclipse.xtext.ide.server.LanguageServerImpl;
import org.eclipse.xtext.ide.server.ServerModule;

public class Client implements LanguageClient {

    // strace -f -e trace=write -e "signal=!all" -o trace -s 1999 java -cp "ls.jar:." Client.java

    // cat trace | sed '/+++/d' | sed '/write([237],/d' > out
    
    @Inject
    LanguageServerImpl languageServer;

    public static void main(String[] args) 
    {
        var serverModule = new ServerModule();
        var client = Guice.createInjector(serverModule).<Client>getInstance(Client.class);
        client.start();
    }

    public void start() 
    {
        try
        {
            var serverSocket = new ServerSocket(5555);
            var serverRunnable = new ServerRunnable();
            serverRunnable.serverSocket = serverSocket;

            var threadPool = Executors.newCachedThreadPool();
            threadPool.execute(serverRunnable);
            threadPool.shutdown();

            var clientSocket = new Socket("localhost", 5555);
            var launcher = LSPLauncher.createClientLauncher(this, clientSocket.getInputStream(), clientSocket.getOutputStream(), true, new PrintWriter(new File("client")));
        
            var server = launcher.getRemoteProxy();
            launcher.startListening();

            server.initialize(initializeParams());
            Thread.sleep(1000);
            server.initialized(new InitializedParams());
            Thread.sleep(1000);

            serverSocket.close();
            clientSocket.close();

            languageServer.exit();
        }
        catch (Exception e) 
        {
            e.printStackTrace();
        }
        
    }

    class ServerRunnable implements Runnable
    {
        public ServerSocket serverSocket;

        @Override
        public void run() {
            try 
            {
                var clientSocket = serverSocket.accept();

                Launcher<LanguageClient> launcher = LSPLauncher.createServerLauncher(languageServer, clientSocket.getInputStream(), clientSocket.getOutputStream(), true, new PrintWriter(new File("server")));

                var client = launcher.getRemoteProxy();
                languageServer.connect(client);

                launcher.startListening();
            }
            catch (Exception e) 
            {
                e.printStackTrace();
            }
        }
        
    }

    @Override
    public void telemetryEvent(Object object) {
        System.out.println("Telemetry");

    }

    @Override
    public void publishDiagnostics(PublishDiagnosticsParams diagnostics) 
    {
        if (diagnostics.getDiagnostics().size() == 0) return;
        System.out.print(diagnostics.getUri()+"\n");
        for (var d : diagnostics.getDiagnostics())
        {
            System.out.print(d.getSeverity()+": "+d.getMessage()+"\n"+d.getCode()+"\n"+d.getRange()+"\n");
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
        System.out.println("Logging");

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