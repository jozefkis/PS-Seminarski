/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package so.caj;

import db.DBBroker;
import domain.AbstractDomainObject;
import domain.Caj;
import java.sql.Connection;
import java.util.List;
import so.AbstractSO;

/**
 *
 * @author Yo
 */
public class SOGetAllCaj extends AbstractSO
{

    private List<Caj> all;
    
    @Override
    protected void validate(AbstractDomainObject ado) throws Exception
    {
        if (!(ado instanceof Caj))
            throw new Exception("Prosledjeni objekat nije instanca klase Caj!");
    }

    @Override
    protected void execute(AbstractDomainObject ado, Connection connection) throws Exception
    {
        Caj c = (Caj) ado;
        
        all = (List<Caj>) (List<?>) DBBroker.getInstance().select(c, connection);
    }

    public List<Caj> getAll()
    {
        return all;
    }
    
    
    
}
