/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package db;

import domain.AbstractDomainObject;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;


/**
 *
 * @author Yo
 */
public class DBBroker
{
    private static DBBroker instance;

    private DBBroker() 
    {
       
    }

    public static DBBroker getInstance() 
    {
        if (instance == null) 
            instance = new DBBroker();
        
        return instance;
    }

    public List<AbstractDomainObject> select(AbstractDomainObject ado, Connection connection) throws SQLException 
    {
        String upit = "SELECT * FROM " + ado.nazivTabele() + " " + ado.alijas()
                + " " + ado.join() + " " + ado.uslovZaSelect();
        System.out.println(upit);
        
        Statement s = connection.createStatement();
        ResultSet rs = s.executeQuery(upit);
        
        return ado.vratiListu(rs);
    }

    public PreparedStatement insert(AbstractDomainObject ado, Connection connection) throws SQLException 
    {
        String naredba = "INSERT INTO " + ado.nazivTabele() + " "
                + ado.koloneZaInsert() + " VALUES(" + ado.vrednostiZaInsert() + ")";
        System.out.println(naredba);
        
        PreparedStatement ps = connection.prepareStatement(naredba, Statement.RETURN_GENERATED_KEYS);
        ps.executeUpdate();
        
        return ps;
    }

    public void update(AbstractDomainObject ado, Connection connection) throws SQLException 
    {
        String naredba = "UPDATE " + ado.nazivTabele() + " SET "
                + ado.vrednostiZaUpdate() + " WHERE " + ado.uslov();
        System.out.println(naredba);
    
        Statement s = connection.createStatement();
        s.executeUpdate(naredba);
    }

    public void delete(AbstractDomainObject ado, Connection connection) throws SQLException 
    {
        String naredba = "DELETE FROM " + ado.nazivTabele() + " WHERE " + ado.uslov();
        System.out.println(naredba);
        
        Statement s = connection.createStatement();
        s.executeUpdate(naredba);
    }

}
