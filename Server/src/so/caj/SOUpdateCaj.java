/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package so.caj;

import db.DBBroker;
import domain.AbstractDomainObject;
import domain.Caj;
import java.sql.Connection;
import so.AbstractSO;

/**
 *
 * @author Yo
 */
public class SOUpdateCaj extends AbstractSO
{

    @Override
    protected void validate(AbstractDomainObject ado) throws Exception
    {
        if (!(ado instanceof Caj))
            throw new Exception("Prosledjeni objekat nije klase Caj!");
    }

    @Override
    protected void execute(AbstractDomainObject ado, Connection connection) throws Exception
    {
        DBBroker.getInstance().update(ado, connection);
    }
    
}
