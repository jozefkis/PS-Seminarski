/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package so.caj;

import db.DBBroker;
import domain.AbstractDomainObject;
import domain.Caj;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import so.AbstractSO;

/**
 *
 * @author Yo
 */
public class SOAddCaj extends AbstractSO
{
    private Caj caj;

    @Override
    protected void validate(AbstractDomainObject ado) throws Exception
    {
        if (!(ado instanceof Caj))
        {
            throw new Exception("Prosledjeni objekat nije instanca klase Caj!");
        }
    }

    @Override
    protected void execute(AbstractDomainObject ado, Connection connection) throws Exception
    {
        caj = (Caj) ado;
        PreparedStatement ps = DBBroker.getInstance().insert(caj, connection);
        
        ResultSet rs = ps.getGeneratedKeys();
        if (rs.next())
            caj.setIdCaj(rs.getLong(1));
    }

    public Caj getCaj()
    {
        return caj;
    }
    
}
