/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package so.kupac;

import db.DBBroker;
import domain.AbstractDomainObject;
import domain.Kupac;
import java.util.List;
import so.AbstractSO;
import java.sql.Connection;

/**
 *
 * @author Yo
 */
public class SOGetAllKupac extends AbstractSO
{
    private List<Kupac> all;
    
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
        
        all = (List<Kupac>) (List<?>) DBBroker.getInstance().select(k, connection);
    }

    public List<Kupac> getAll()
    {
        return all;
    }
}
