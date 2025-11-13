/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package so.mesto;

import db.DBBroker;
import domain.AbstractDomainObject;
import domain.Mesto;
import java.util.List;
import so.AbstractSO;
import java.sql.Connection;

/**
 *
 * @author Yo
 */
public class SOGetAllMesto extends AbstractSO
{
    private List<Mesto> all;
    
    @Override
    protected void validate(AbstractDomainObject ado) throws Exception
    {
        if (!(ado instanceof Mesto))
            throw new Exception("Prosledjeni objekat nije instanca klase Mesto!");
    }

    @Override
    protected void execute(AbstractDomainObject ado, Connection connection) throws Exception
    {
        Mesto k = (Mesto) ado;
        
        all = (List<Mesto>) (List<?>) DBBroker.getInstance().select(k, connection);
    }

    public List<Mesto> getAll()
    {
        return all;
    }
}
