/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package so.kupac;

import db.DBBroker;
import domain.AbstractDomainObject;
import domain.Kupac;
import java.sql.Connection;
import so.AbstractSO;

/**
 *
 * @author Yo
 */
public class SODeleteKupac extends AbstractSO
{

    @Override
    protected void validate(AbstractDomainObject ado) throws Exception
    {
        if (!(ado instanceof Kupac))
            throw new Exception("Prosledjen objekat koji nije instanca klase Kupac!");
    }

    @Override
    protected void execute(AbstractDomainObject ado, Connection connection) throws Exception
    {
        DBBroker.getInstance().delete(ado, connection);
    }
}
