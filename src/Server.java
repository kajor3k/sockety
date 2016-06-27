import java.net.*;
import java.io.*;

public class Server extends Thread
{
    private ServerSocket serverSocket;

    public Server(int port) throws IOException
    {
        serverSocket = new ServerSocket(port);
        serverSocket.setSoTimeout(10000);
    }

    public void run()
    {
        while(true)
        {
            try
            {
                System.out.println("Oczekuje klienta na porcie " +
                        serverSocket.getLocalPort() + "...");
                Socket server = serverSocket.accept();
                DataInputStream in =

                        new DataInputStream(server.getInputStream());// send book list
                System.out.println(in.readUTF());
                DataOutputStream out =
                        new DataOutputStream(server.getOutputStream());
                out.writeUTF("Koniec polaczenia "
                        + server.getLocalSocketAddress() + "\n");
                server.close();
            }catch(SocketTimeoutException s)
            {
                System.out.println("Przekroczono czas oczekiwania!");
                break;
            }catch(IOException e)
            {
                e.printStackTrace();
                break;
            }
        }
    }
    public static void main(String [] args)
    {
        int port = Integer.parseInt(args[0]);
        try
        {
            Thread t = new Server(port);
            t.start();
        }catch(IOException e)
        {
            e.printStackTrace();
        }
    }
}