/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package so.login;
import coordinator.ServerCoordinator;
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
        List<Travar> list = (List<Travar>) (List<?>) DBBroker.getInstance().selectByExistenceCondition(travar, connection);
        
        if (list == null || list.isEmpty())
        {
            ulogovani = null;
            return;
        }
        
        ulogovani = list.get(0);
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
