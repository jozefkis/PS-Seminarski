/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package so.login;
import controller.ServerController;
import domain.AbstractDomainObject;
import domain.Travar;
import java.sql.Connection;
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
        
        Travar travar = (Travar) ado;
        
        for (Travar t: ServerController.getInstance().getUlogovaniTravari())
        {
            if (t.equals(travar))
                throw new Exception("Taj travar je vec ulogovan!");
        }
            
    }

    @Override
    protected void execute(AbstractDomainObject ado, Connection connection) throws Exception
    {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
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
