/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package thread;

import communication.Receiver;
import communication.Request;
import communication.Response;
import communication.Sender;
import controller.ServerController;
import domain.Travar;
import java.io.IOException;
import java.net.Socket;

/**
 *
 * @author Yo
 */
public class ClientThread implements Runnable
{
    private Socket socket;
    private volatile boolean logoutRequested = false;

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
            
            while (!socket.isClosed() && !logoutRequested)
            {
                Request req = (Request) receiver.receive();
                System.out.println("\nStigao je zahtev za operaciju: " + req.getOperation() + "\n");
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
                    Travar travar = ServerController.getInstance().login((Travar) req.getArgument());
                    response.setResult(travar);
                    break;
                case LOGOUT:
                    Travar t = ServerController.getInstance().logout((Travar) req.getArgument());
                    logoutRequested = true;
                    response.setResult(t);
                    break;
                case GET_ALL_TRAVAR:
                    response.setResult(ServerController.getInstance().getAllTravar());
                    break;
                case TEST:
                    response.setResult("ODGOVOR USPESAN");
                    break;
                    
            }
        }
        catch (Exception e)
        {
            response.setException(e);
        }
        
        return response;
    }
    
}
