/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package db;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;
import java.sql.Connection;
import java.sql.SQLException;

/**
 *
 * @author Yo
 */
public class DataSource
{
    private final static DataSource instance = new DataSource();
    private final HikariDataSource hikariDataSource;
    
    private DataSource()
    {
        {
            HikariConfig config = new HikariConfig("hikari.properties");
            System.out.println(config.isAutoCommit());
            hikariDataSource = new HikariDataSource(config);
        }
        
    }
    
    public static DataSource getInstance()
    {
        return instance;
    }
    
    public Connection getConnection() throws SQLException
    {
        return hikariDataSource.getConnection();
    }
    
    public void closePool()
    {
        if (hikariDataSource != null && !hikariDataSource.isClosed())
            hikariDataSource.close();
    }
}
