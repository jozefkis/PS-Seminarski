/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package communication;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.net.Socket;

/**
 *
 * @author Yo
 */
public class Receiver 
{
    private final ObjectInputStream ois;

    public Receiver(Socket socket) throws IOException
    {
        ois = new ObjectInputStream(socket.getInputStream());
    }
    
    public Object receive() throws Exception
    {
        try
        {
            return ois.readObject();
        }
        catch (IOException iOException)
        {
            iOException.printStackTrace();
            throw new Exception("Receiving object error:\n"+iOException.getMessage());
        }       
    }
}
