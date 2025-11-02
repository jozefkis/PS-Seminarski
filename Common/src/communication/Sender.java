/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package communication;

import java.io.IOException;
import java.io.ObjectOutputStream;
import java.net.Socket;

/**
 *
 * @author Yo
 */
public class Sender 
{
    private final ObjectOutputStream oos;

    public Sender(Socket socket) throws IOException
    {
        oos = new ObjectOutputStream(socket.getOutputStream());
    }
    
    public void send(Object obj) throws Exception
    {
        try
        {
            oos.writeObject(obj);
            oos.flush();
        }
        catch (IOException iOException)
        {
            iOException.printStackTrace();
            throw new Exception("Sending object error:\n"+iOException.getMessage());
        }
    }
    
}
