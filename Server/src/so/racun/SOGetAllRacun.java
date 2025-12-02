/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package so.racun;

import db.DBBroker;
import domain.AbstractDomainObject;
import domain.Racun;
import java.sql.Connection;
import java.util.List;
import so.AbstractSO;

/**
 *
 * @author Yo
 */
public class SOGetAllRacun extends AbstractSO
{
    private List<Racun> all;

    @Override
    protected void validate(AbstractDomainObject ado) throws Exception
    {
        if (!(ado instanceof Racun))
            throw new Exception("Prosledjeni objekat nije instanca klase Racun!");
            
    }

    @Override
    protected void execute(AbstractDomainObject ado, Connection connection) throws Exception
    {
        Racun r = (Racun) ado;
        all = (List<Racun>) (List<?>) DBBroker.getInstance().select(r, connection);
    }
    
    public List<Racun> getAll()
    {
        return all;
    }
}
