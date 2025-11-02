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
    private ServerSocket serverSocket;

    public ServerThread()
    {
        try
        {
            serverSocket = new ServerSocket(9999);
        }
        catch (Exception ex)
        {
            ex.printStackTrace();
        }
    }
    
    @Override
    public void run()
    {
        while (!serverSocket.isClosed())
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
                ex.printStackTrace();
            }
        }
    }

    public ServerSocket getServerSocket()
    {
        return serverSocket;
    }

    public void setServerSocket(ServerSocket serverSocket)
    {
        this.serverSocket = serverSocket;
    }
    
}
