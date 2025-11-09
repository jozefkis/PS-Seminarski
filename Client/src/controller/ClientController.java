/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controller;

import communication.Operation;
import communication.Receiver;
import communication.Request;
import communication.Response;
import communication.Sender;
import domain.Travar;
import java.io.IOException;
import java.net.Socket;

/**
 *
 * @author Yo
 */
public class ClientController
{

    private static ClientController instance;
    private Socket socket;
    private Sender sender;
    private Receiver receiver;

    private ClientController()
    {
        try
        {
            socket = new Socket("localhost", 9999);
            sender = new Sender(socket);
            receiver = new Receiver(socket);
        }
        catch (IOException ex)
        {
            System.out.println("Client Controller initialization error!");
            ex.printStackTrace();
        }
    }

    public static ClientController getInstance()
    {
        if (instance == null)
        {
            instance = new ClientController();
        }
        return instance;
    }

    public Travar login(Travar travar) throws Exception
    {
        Request request = new Request(Operation.LOGIN, travar);
        sender.send(request);

        Response res = (Response) receiver.receive();

        if (res.getException() == null)
        {
            return (Travar) res.getResult();
        }
        else
        {
            throw res.getException();
        }
    }

    public Travar logout(Travar travar) throws Exception
    {
        Request request = new Request(Operation.LOGOUT, travar);
        sender.send(request);

        Response res = (Response) receiver.receive();

        if (res.getException() == null)
        {
            closeConnection();
            return (Travar) res.getResult();
        }
        else
        {
            throw res.getException();
        }
    }

    public void closeConnection()
    {
        try
        {
            if (socket != null && !socket.isClosed())
            {
                socket.close();
                System.out.println("Konekcija sa serverom uspešno zatvorena.");
                instance = null;
            }
        }
        catch (IOException ex)
        {
            ex.printStackTrace();
        }

    }
}
