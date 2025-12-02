/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package so.kupac;

import db.DBBroker;
import domain.AbstractDomainObject;
import domain.Kupac;
import java.sql.Connection;
import java.util.List;
import so.AbstractSO;

/**
 *
 * @author Yo
 */
public class SOFilterKupac extends AbstractSO
{
    private List<Kupac> filtered;
   
    @Override
    protected void validate(AbstractDomainObject ado) throws Exception
    {
        if (!(ado instanceof Kupac))
            throw new Exception("Prosledjeni objekat nije instanca klase Kupac!");
    }

    @Override
    protected void execute(AbstractDomainObject ado, Connection connection) throws Exception
    {
        Kupac k = (Kupac) ado;
        filtered = (List<Kupac>) (List<?>) DBBroker.getInstance().filter(k, connection);
    }

    public List<Kupac> getFiltered()
    {
        return filtered;
    }
    
}
