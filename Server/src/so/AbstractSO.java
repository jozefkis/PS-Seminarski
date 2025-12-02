/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package so;

import db.DataSource;
import domain.AbstractDomainObject;
import java.sql.SQLException;
import java.sql.Connection;

/**
 *
 * @author Yo
 */
public abstract class AbstractSO
{
    protected abstract void validate(AbstractDomainObject ado) throws Exception;
    protected abstract void execute(AbstractDomainObject ado, Connection connection) throws Exception;
    
    private Connection connection;

    public void templateExecute(AbstractDomainObject ado) throws Exception 
    {
        connection = DataSource.getInstance().getConnection();
        connection.setAutoCommit(false);

        try 
        {
            validate(ado);
            execute(ado, connection);
            commit();
        }
        catch (Exception e) 
        {
            rollback();
            throw e;
        }
        finally
        {
            closeConnection();
        }
    }

    public void commit() throws SQLException 
    {
        if (connection != null)
        {
            connection.commit();
            System.out.println("Transaction COMMITED.\n");
        }
    }

    public void rollback()
    {
        if (connection != null)
        {
            try 
            {
                connection.rollback();
                System.err.println("Transaction ROLLBACKED.\n");
            }
            catch (SQLException ex) 
            {
                System.err.println("ROLLBACK ERROR: " + ex.getMessage());
            }
        }   
    }
    
    public void closeConnection() throws SQLException
    {
        if (connection != null && !connection.isClosed())
            connection.close();
    }
}
