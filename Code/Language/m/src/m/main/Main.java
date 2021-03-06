package m.main;

import java.io.IOException;
import java.net.ServerSocket;

public class Main
{
	public static void main(String[] arguments) throws IOException
	{
		var server = new Server();

		var debugging = arguments.length > 0;

		if (!debugging)
		{
			server.connect(System.in, System.out);
		}
		else
		{
			var socketNumber = Integer.parseInt(arguments[0]);

			try (var socket = new ServerSocket(socketNumber))
			{
				while(true)
				{
					var clientSocket = socket.accept();

					server.connect(clientSocket.getInputStream(), clientSocket.getOutputStream());
				}
			}
		}
	}
}
