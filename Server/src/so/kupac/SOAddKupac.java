/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package so.kupac;

import db.DBBroker;
import domain.AbstractDomainObject;
import domain.Kupac;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import so.AbstractSO;

/**
 *
 * @author Yo
 */
public class SOAddKupac extends AbstractSO
{
    private Kupac kupac;

    @Override
    protected void validate(AbstractDomainObject ado) throws Exception
    {
        if (!(ado instanceof Kupac))
        {
            throw new Exception("Prosledjeni objekat nije instanca klase Kupac!");
        }
    }

    @Override
    protected void execute(AbstractDomainObject ado, Connection connection) throws Exception
    {
        kupac = (Kupac) ado;
        PreparedStatement ps = DBBroker.getInstance().insert(kupac, connection);
        
        ResultSet rs = ps.getGeneratedKeys();
        if (rs.next())
            kupac.setIdKupac(rs.getLong(1));
    }

    public Kupac getKupac()
    {
        return kupac;
    }
    
}
