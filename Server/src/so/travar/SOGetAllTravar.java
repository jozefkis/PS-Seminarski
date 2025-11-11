/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package so.travar;

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
public class SOGetAllTravar extends AbstractSO
{
    private List<Travar> all;
    
    @Override
    protected void validate(AbstractDomainObject ado) throws Exception
    {
        if (!(ado instanceof Travar))
            throw new Exception("Prosledjeni objekat nije instanca klase Travar!");
    }

    @Override
    protected void execute(AbstractDomainObject ado, Connection connection) throws Exception
    {
        Travar t = (Travar) ado;
        
        all = (List<Travar>) (List<?>) DBBroker.getInstance().select(t, connection);
    }

    public List<Travar> getAll()
    {
        return all;
    }
    
}
