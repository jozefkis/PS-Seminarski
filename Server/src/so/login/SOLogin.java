/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package so.login;
import controller.ServerController;
import db.DBBroker;
import domain.AbstractDomainObject;
import domain.Travar;
import java.sql.Connection;
import java.util.List;
import so.AbstractSO;

/**
 *
 * @author Yo
 */
public class SOLogin extends AbstractSO
{
    
    private Travar ulogovani;
    
    @Override
    protected void validate(AbstractDomainObject ado) throws Exception
    {
        if (ado == null || !(ado instanceof Travar))
            throw new Exception("Poslat neodgovarajuci objekat!");
                  
    }

    @Override
    protected void execute(AbstractDomainObject ado, Connection connection) throws Exception
    {
        Travar travar = (Travar) ado;
        List<Travar> all = (List<Travar>) (List<?>) DBBroker.getInstance().select(travar, connection);
        
        for (Travar t: all)
        {
            if (t.getUsername().equals(travar.getUsername()) && t.getPassword().equals(travar.getPassword()))
            {
                ulogovani = t;
                
                synchronized (ServerController.getInstance().getUlogovaniTravari())
                {
                    if (ServerController.getInstance().getUlogovaniTravari().contains(ulogovani)) 
                    {
                        throw new Exception("Travar je vec ulogovan!"); 
                    }
            
                    ServerController.getInstance().getUlogovaniTravari().add(ulogovani);
                }
                return;
            }
        }
        
        throw new Exception("Ne postoji travar sa tim kredencijalima.");
    }

    public Travar getUlogovani()
    {
        return ulogovani;
    }

    public void setUlogovani(Travar ulogovani)
    {
        this.ulogovani = ulogovani;
    }
}
