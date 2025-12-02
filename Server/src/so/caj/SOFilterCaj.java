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
 * @author SOFIJA
 */
public class SOFilterCaj extends AbstractSO
{
    private List<Caj> filtered;

    @Override
    protected void validate(AbstractDomainObject ado) throws Exception
    {
        if (!(ado instanceof Caj))
            throw new Exception("Prosledjeni objekat nije instaca klase Caj!");
    }

    @Override
    protected void execute(AbstractDomainObject ado, Connection connection) throws Exception
    {
        Caj caj = (Caj) ado;
        filtered = (List<Caj>) (List<?>) DBBroker.getInstance().filter(caj, connection);
    }

    public List<Caj> getFiltered()
    {
        return filtered;
    }
    
}
