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
import coordinator.ServerCoordinator;
import domain.Caj;
import domain.Kupac;
import domain.Racun;
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
    private Travar loggedTravar;

    public ClientThread(Socket socket)
    {
        this.socket = socket;
    }

    public Travar getLoggedTravar()
    {
        return loggedTravar;
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
            ServerCoordinator.getInstance().removeClient(this);
            
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
                    loggedTravar = travar;
                    ServerCoordinator.getInstance().notifyForm();
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
                case GET_ALL_KUPAC:
                    response.setResult(ServerController.getInstance().getAllKupac());
                    break;
                case GET_ALL_CAJ:
                    response.setResult(ServerController.getInstance().getAllCaj());
                    break;
                case GET_ALL_MESTO:
                    response.setResult(ServerController.getInstance().getAllMesto());
                    break;
                case FILTER_KUPAC:
                    response.setResult(ServerController.getInstance().filterKupac((Kupac) req.getArgument()));
                    break;
                case UPDATE_KUPAC:
                    ServerController.getInstance().updateKupac((Kupac) req.getArgument());
                    break;
                case DELETE_KUPAC:
                    ServerController.getInstance().deleteKupac((Kupac) req.getArgument());
                    break;
                case ADD_KUPAC:
                    response.setResult(ServerController.getInstance().addKupac((Kupac) req.getArgument()));
                    break;
                case ADD_CAJ:
                    response.setResult(ServerController.getInstance().addCaj((Caj) req.getArgument()));
                    break;
                case FILTER_CAJ:
                    response.setResult(ServerController.getInstance().filterCaj((Caj) req.getArgument()));
                    break;
                case DELETE_CAJ:
                    ServerController.getInstance().deleteCaj((Caj) req.getArgument());
                    break;
                case UPDATE_CAJ:
                    ServerController.getInstance().updateCaj((Caj) req.getArgument());
                    break;
                case ADD_RACUN:
                    response.setResult(ServerController.getInstance().addRacun((Racun) req.getArgument()));
                    break;
                case GET_ALL_RACUN:
                    Racun r = (Racun) req.getArgument();
                    System.out.println("Racun: " + r.getTravar());
                    response.setResult(ServerController.getInstance().getAllRacun(r));
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
