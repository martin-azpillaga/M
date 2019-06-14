import java.net.*;
import java.io.*;

public class server
{
    public static void main(String[] args) throws IOException
    {
        System.out.println("Hello world");
        ServerSocket ss = new ServerSocket(4999);
        Socket s = ss.accept();
        s.close();
        ss.close();
        System.out.println("Bye");
    }
}