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
    private Socket socket;

    public Receiver(Socket socket)
    {
        this.socket = socket;
    }
    
    public Object receive() throws Exception
    {
        try
        {
            ObjectInputStream ois = new ObjectInputStream(socket.getInputStream());
            return ois.readObject();
        }
        catch (IOException iOException)
        {
            iOException.printStackTrace();
            throw new Exception("Receiving object error:\n"+iOException.getMessage());
        }       
    }
}
