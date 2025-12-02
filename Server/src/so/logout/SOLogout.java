/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package so.logout;

import controller.ServerController;
import coordinator.ServerCoordinator;
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
    
    private Travar travarToLogOut;

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
        travarToLogOut = (Travar) ado;
    }

    public Travar getTravarToLogOut()
    {
        return travarToLogOut;
    }
    
    
}
