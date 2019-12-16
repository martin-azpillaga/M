import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Scanner;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

import com.google.inject.Guice;
import com.google.inject.Inject;

import org.eclipse.lsp4j.InitializeParams;
import org.eclipse.lsp4j.MessageActionItem;
import org.eclipse.lsp4j.MessageParams;
import org.eclipse.lsp4j.PublishDiagnosticsParams;
import org.eclipse.lsp4j.ShowMessageRequestParams;
import org.eclipse.lsp4j.jsonrpc.Launcher;
import org.eclipse.lsp4j.launch.LSPLauncher;
import org.eclipse.lsp4j.services.LanguageClient;
import org.eclipse.xtext.ide.server.LanguageServerImpl;
import org.eclipse.xtext.ide.server.ServerModule;
import org.eclipse.xtext.xbase.lib.InputOutput;

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
            serverSocket = new ServerSocket(5555);
            var threadPool = Executors.newCachedThreadPool();
            threadPool.execute(() ->
            {
                Socket clientSocket = null;
                try 
                {
                    clientSocket = serverSocket.accept();
                    System.out.println("Client connected");

                    Launcher<LanguageClient> launcher = LSPLauncher.createServerLauncher(server, clientSocket.getInputStream(), clientSocket.getOutputStream(),false, new PrintWriter(System.out));

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
            var launcher = LSPLauncher.createClientLauncher(this, socket.getInputStream(), socket.getOutputStream(),false, new PrintWriter(System.out));
        
            var end = launcher.getRemoteProxy();
            var params = new InitializeParams();
            end.initialize(params);

            thread = launcher.startListening();
            
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
    public void publishDiagnostics(PublishDiagnosticsParams diagnostics) {
        System.out.println("Diagnostics");

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

}