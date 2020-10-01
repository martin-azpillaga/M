package m.main;

import java.io.IOException;
import java.net.ServerSocket;
import org.eclipse.lsp4j.launch.LSPLauncher;

public class Main
{
	public static void main(String[] arguments)
	{
		var languageServer = new LanguageServer();
		
		if (arguments.length == 0)
		{
			var launcher = LSPLauncher.createServerLauncher(languageServer, System.in, System.out);
			
			var client = launcher.getRemoteProxy();
			languageServer.connect(client);
			
			launcher.startListening();
		}
		else
		{
			var socketNumber = Integer.parseInt(arguments[0]);
			try (var socket = new ServerSocket(socketNumber))
			{
				var clientSocket = socket.accept();
				var launcher = LSPLauncher.createServerLauncher(languageServer, clientSocket.getInputStream(), clientSocket.getOutputStream());
				
				var client = launcher.getRemoteProxy();
				languageServer.connect(client);
				
				launcher.startListening();
			}
			catch (IOException e)
			{
				
			}
		}
	}
}
