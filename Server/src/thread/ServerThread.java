/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package thread;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

/**
 *
 * @author Yo
 */
public class ServerThread implements Runnable
{
    private final ServerSocket serverSocket;
    private volatile boolean running = true;

    public ServerThread() throws IOException
    {
        serverSocket = new ServerSocket(9999);
    }
    
    @Override
    public void run()
    {
        while (running)
        {
            try
            {
                System.out.println("Cekam klijente...");
                Socket socket = serverSocket.accept();
                System.out.println("Klijent se povezao!");
                
                Thread cth = new Thread(new ClientThread(socket));
                cth.start();
            }
            catch (IOException ex)
            {
                if (!running)
                    System.out.println("Server shutting down...");
                else
                    ex.printStackTrace();
            }
        }
        System.out.println("Server is down.\n");
    }
    
    public void stopServer()
    {
        this.running = false;
        
        try
        {
            if (serverSocket != null && !serverSocket.isClosed())
            {
                serverSocket.close();
            }
        }
        catch (IOException ex)
        {
            ex.printStackTrace();
        }
    }
}
