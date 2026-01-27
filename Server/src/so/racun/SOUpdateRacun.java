/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package so.racun;

import db.DBBroker;
import domain.AbstractDomainObject;
import domain.Racun;
import domain.StavkaRacuna;
import java.sql.Connection;
import so.AbstractSO;

/**
 *
 * @author Yo
 */
public class SOUpdateRacun extends AbstractSO
{
    private Racun racun;

    @Override
    protected void validate(AbstractDomainObject ado) throws Exception
    {
        if (!(ado instanceof Racun))
            throw new Exception("Prosledjeni objekat nije instanca klase Racun!");
        
        Racun rc = (Racun) ado;
        
        if ( rc.getStavkeRacuna() == null || rc.getStavkeRacuna().size() <= 0)
            throw new Exception("Racun mora imati barem jednu stavku!");
        
        if (((Racun) ado).getIdRacun() <= 0)
            throw new Exception("Racun nije u bazi!");
    }

    @Override
    protected void execute(AbstractDomainObject ado, Connection connection) throws Exception
    {
        racun = (Racun) ado;
        
        DBBroker.getInstance().update(racun, connection);
        
        for (StavkaRacuna sr: racun.getStavkeRacuna())
        {
            if (sr.getStatus() == null)
                continue;
            
            switch (sr.getStatus())
            {
                case "DB_DELETE":
                    DBBroker.getInstance().delete(sr, connection);
                    break;
                case "DB_UPDATE":
                    DBBroker.getInstance().update(sr, connection);
                    break;
                case "DB_INSERT":
                    DBBroker.getInstance().insert(sr, connection);
                    break;
            }
        }
    }
    
}
