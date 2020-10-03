package m.main;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.ServerSocket;
import org.eclipse.lsp4j.launch.LSPLauncher;

public class Main
{
	public static void main(String[] arguments) throws IOException
	{
		var server = new Server();
		
		if (arguments.length == 0)
		{
			connect(server, System.in, System.out);
		}
		else
		{
			var socketNumber = Integer.parseInt(arguments[0]);
			try (var socket = new ServerSocket(socketNumber))
			{
				var clientSocket = socket.accept();
				connect(server, clientSocket.getInputStream(), clientSocket.getOutputStream());
			}
		}
	}

	private static void connect(Server server, InputStream input, OutputStream output)
	{
		var launcher = LSPLauncher.createServerLauncher(server, input, output);

		var client = launcher.getRemoteProxy();
		server.connect(client);
		
		launcher.startListening();
	}
}
