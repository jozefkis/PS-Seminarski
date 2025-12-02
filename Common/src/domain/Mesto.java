/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package domain;

import java.sql.PreparedStatement;
import java.util.ArrayList;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Objects;


/**
 *
 * @author Yo
 */
public class Mesto implements AbstractDomainObject
{
    private long idMesto;
    private String naziv;

    @Override
    public String toString() 
    {
        return naziv;
    }

    public Mesto(long idMesto, String naziv) 
    {
        this.idMesto = idMesto;
        this.naziv = naziv;
    }

    public Mesto() 
    {
        
    }

    @Override
    public int hashCode()
    {
        int hash = 7;
        return hash;
    }

    @Override
    public boolean equals(Object obj)
    {
        if (this == obj)
        {
            return true;
        }
        if (obj == null)
        {
            return false;
        }
        if (getClass() != obj.getClass())
        {
            return false;
        }
        final Mesto other = (Mesto) obj;
        if (this.idMesto != other.idMesto)
        {
            return false;
        }
        return Objects.equals(this.naziv, other.naziv);
    }
    
    
    //=== Implemented ADO methods ===
    @Override
    public String getTableName() 
    {
        return " mesto ";
    }

    @Override
    public String getAlias() 
    {
        return " m ";
    }

    @Override
    public String getJoinCondition() 
    {
        return "";
    }

    @Override
    public List<AbstractDomainObject> getList(ResultSet rs) throws SQLException 
    {
        List<AbstractDomainObject> lista = new ArrayList<>();

        while(rs.next()) 
        {
            Mesto m = new Mesto(rs.getLong("idMesto"),rs.getString("m.naziv"));
            lista.add(m);
        }

        return lista;
    }

    @Override
    public String getInsertColumns() 
    {
        return " (naziv) ";
    }

    @Override
    public String getInsertPlaceholders() 
    {
        return " ? ";
    }

    @Override
    public String getUpdatePlaceholders() 
    {
        return " naziv = ? ";
    }

    @Override
    public String getConditionPlaceholder() 
    {
        return " idMesto = ? ";
    }

    @Override
    public String getSelectConditionPlaceholder() 
    {
        return "";
    }

    @Override
    public String getFilterConditionPlaceholder()
    {
        return "";
    }
    
    @Override
    public void prepareInsert(PreparedStatement ps) throws SQLException
    {
        ps.setString(1, naziv);
    }

    @Override
    public void prepareUpdate(PreparedStatement ps) throws SQLException
    {
        ps.setString(1, naziv);
        ps.setLong(2, idMesto);
    }
    
    @Override
    public void prepareCondition(PreparedStatement ps) throws SQLException
    {
        ps.setLong(1, idMesto);
    }

    @Override
    public void prepareSelect(PreparedStatement ps) throws SQLException
    {
    }

    @Override
    public void prepareFilter(PreparedStatement ps) throws SQLException
    {
    }
        
    
    //=== Getters & Setters ===
    public long getIdMesto()
    {
        return idMesto;
    }

    public void setIdMesto(long idMesto)
    {
        this.idMesto = idMesto;
    }

    public String getNaziv() 
    {
        return naziv;
    }

    public void setNaziv(String naziv) 
    {
        this.naziv = naziv;
    }

    @Override
    public String getExistenceConditionPlaceholder()
    {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public void prepareExistenceCondition(PreparedStatement ps) throws SQLException
    {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
}
