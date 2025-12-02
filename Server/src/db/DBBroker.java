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
        {
            instance = new DBBroker();
        }

        return instance;
    }

    public List<AbstractDomainObject> select(AbstractDomainObject ado, Connection connection) throws SQLException
    {
        String selectCondition = ado.getSelectConditionPlaceholder();
        
        String query = "SELECT * FROM " + ado.getTableName() + " " + ado.getAlias() + " " + ado.getJoinCondition();

        if (selectCondition != null && !selectCondition.isEmpty())
        {
            query += selectCondition;
        }
        System.out.println(query);

        PreparedStatement ps = connection.prepareStatement(query);
        ado.prepareSelect(ps);

        ResultSet rs = ps.executeQuery();
        List<AbstractDomainObject> list = ado.getList(rs);

        ps.close();
        return list;
    }

    public List<AbstractDomainObject> filter(AbstractDomainObject ado, Connection connection) throws SQLException
    {
        String query = "SELECT * FROM " + ado.getTableName() + " " + ado.getAlias() + " " + ado.getJoinCondition() + " WHERE " + ado.getFilterConditionPlaceholder();
        System.out.println(query);

        PreparedStatement ps = connection.prepareStatement(query);
        ado.prepareFilter(ps);
        
        ResultSet rs = ps.executeQuery();
        List<AbstractDomainObject> list = ado.getList(rs);

        ps.close();
        return list;
    }

    public PreparedStatement insert(AbstractDomainObject ado, Connection connection) throws SQLException
    {
        String command = "INSERT INTO " + ado.getTableName() + " " + ado.getInsertColumns() + " VALUES (" + ado.getInsertPlaceholders() + ")";
        System.out.println(command);

        PreparedStatement ps = connection.prepareStatement(command, PreparedStatement.RETURN_GENERATED_KEYS);
        ado.prepareInsert(ps);
        
        
        ps.executeUpdate();
        
        return ps;
    }

    public void update(AbstractDomainObject ado, Connection connection) throws SQLException
    {
        String command = "UPDATE " + ado.getTableName() + " SET " + ado.getUpdatePlaceholders() + " WHERE " + ado.getConditionPlaceholder();
        System.out.println(command);

        PreparedStatement ps = connection.prepareStatement(command);
        ado.prepareUpdate(ps);

        ps.executeUpdate();
        ps.close();
    }

    public void delete(AbstractDomainObject ado, Connection connection) throws SQLException
    {
        String command = "DELETE FROM " + ado.getTableName() + " WHERE " + ado.getConditionPlaceholder();
        System.out.println(command);

        PreparedStatement ps = connection.prepareStatement(command);
        ado.prepareCondition(ps);

        ps.executeUpdate();
        ps.close();
    }

    public List<AbstractDomainObject> selectByExistenceCondition(AbstractDomainObject ado, Connection connection) throws SQLException
    {
        String query = "SELECT * FROM " + ado.getTableName() + " " + ado.getAlias() + " " + ado.getJoinCondition() + " WHERE " + ado.getExistenceConditionPlaceholder();

        System.out.println(query);

        PreparedStatement ps = connection.prepareStatement(query);
        ado.prepareExistenceCondition(ps);

        ResultSet rs = ps.executeQuery();
        List<AbstractDomainObject> list = ado.getList(rs);

        ps.close();
        return list;
    }

}
