/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package thread;

import communication.Receiver;
import communication.Request;
import communication.Response;
import communication.Sender;
import java.io.IOException;
import java.net.Socket;

/**
 *
 * @author Yo
 */
public class ClientThread implements Runnable
{
    Socket socket;

    public ClientThread(Socket socket)
    {
        this.socket = socket;
    }

    
    @Override
    public void run()
    {
        try
        {
            Receiver receiver = new Receiver(socket);
            Sender sender = new Sender(socket);
            
            while (!socket.isClosed())
            {
                Request req = (Request) receiver.receive();
                Response res = handleRequest(req);
                sender.send(res);
            }
        }
        catch (Exception ex)
        {
            ex.printStackTrace();
        }
        finally
        {
            try 
            {
                if (socket != null && !socket.isClosed()) 
                {
                    socket.close();
                }
            } 
            catch (IOException e) 
            {
                e.printStackTrace();
            }
        }
    }

    private Response handleRequest(Request req)
    {
        Response response = new Response(null, null);
        
        try
        {
            switch (req.getOperation())
            {
                case LOGIN:
                    // uradi ovde handle
                    break;
                case LOGOUT:
                    // -||-
                    break;
            }
        }
        catch (Exception e)
        {
            response.setEx(e);
        }
        
        return response;
    }
    
}
