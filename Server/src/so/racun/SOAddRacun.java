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
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import so.AbstractSO;

/**
 *
 * @author Yo
 */
public class SOAddRacun extends AbstractSO
{
    private Racun racun;

    @Override
    protected void validate(AbstractDomainObject ado) throws Exception
    {
        if (!(ado instanceof Racun))
            throw new Exception("Prosledjeni objekat nije instanca klase Racun!");
        
        if (((Racun)ado).getStavkeRacuna().size() <= 0)
            throw new Exception("Racun mora imati barem jednu stavku!");
    }

    @Override
    protected void execute(AbstractDomainObject ado, Connection connection) throws Exception
    {
        racun = (Racun) ado;
        
        PreparedStatement ps = DBBroker.getInstance().insert(racun, connection);
        
        ResultSet rs = ps.getGeneratedKeys();
        if (rs.next())
            racun.setIdRacun(rs.getLong(1));
        
        for (StavkaRacuna sr: racun.getStavkeRacuna())
        {
            sr.setRacun(racun);
            DBBroker.getInstance().insert(sr, connection);
        }
    }

    public Racun getRacun()
    {
        return racun;
    }
    
    
    
}
