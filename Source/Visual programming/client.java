import java.net.*;
import java.io.*;

public class client
{
    public static void main(String[] args) throws IOException
    {
        Socket s = new Socket("192.168.1.64", 4999);
        s.close();
    }
}