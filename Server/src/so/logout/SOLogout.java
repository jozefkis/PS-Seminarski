/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package so.logout;

import controller.ServerController;
import domain.AbstractDomainObject;
import domain.Travar;
import java.sql.Connection;
import so.AbstractSO;

/**
 *
 * @author Yo
 */
public class SOLogout extends AbstractSO
{
    
    private Travar zaOdjavu;

    @Override
    protected void validate(AbstractDomainObject ado) throws Exception
    {
        if (ado == null || !(ado instanceof Travar))
        {
            throw new Exception("Poslat neodgovarajuci objekat!");
        }
    }

    @Override
    protected void execute(AbstractDomainObject ado, Connection connection) throws Exception
    {
        zaOdjavu = (Travar) ado;

        synchronized (ServerController.getInstance().getUlogovaniTravari())
        {
            if (!ServerController.getInstance().getUlogovaniTravari().contains(zaOdjavu))
            {
                throw new Exception("Travar nije ulogovan!");
            }

            ServerController.getInstance().getUlogovaniTravari().remove(zaOdjavu);
        }
        return;
    }

    public Travar getZaOdjavu()
    {
        return zaOdjavu;
    }
    
    
}
