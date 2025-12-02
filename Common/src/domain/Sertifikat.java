/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package domain;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Yo
 */
public class Sertifikat implements AbstractDomainObject
{
    private long idSertifikat;
    private String naziv;
    private String opis;

    public Sertifikat(long idSertifikat, String naziv, String opis)
    {
        this.idSertifikat = idSertifikat;
        this.naziv = naziv;
        this.opis = opis;
    }

    public Sertifikat()
    {
    }
    
    
    //=== Implemented ADO methods ===
    @Override
    public String getTableName()
    {
        return " sertifikat ";
    }

    @Override
    public String getAlias()
    {
        return " s ";
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
            Sertifikat s = new Sertifikat(rs.getLong("idSertifikat"), rs.getString("naziv"), rs.getString("opis"));
            lista.add(s);
        }
        
        return lista;
    }

    @Override
    public String getInsertColumns()
    {
        return " (naziv, opis) ";
    }

    @Override
    public String getInsertPlaceholders()
    {
        return " ?, ? ";
    }

    @Override
    public String getUpdatePlaceholders()
    {
        return " naziv = ?, opis = ? ";
    }

    @Override
    public String getConditionPlaceholder()
    {
        return " idSertifikat = ? ";
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
        ps.setString(2, opis);
    }

    @Override
    public void prepareUpdate(PreparedStatement ps) throws SQLException
    {
        ps.setString(1, naziv);
        ps.setString(2, opis);
        ps.setLong(3, idSertifikat);
    }
    
    @Override
    public void prepareCondition(PreparedStatement ps) throws SQLException
    {
        ps.setLong(1, idSertifikat);
    }

    @Override
    public void prepareSelect(PreparedStatement ps) throws SQLException
    {
    }

    @Override
    public void prepareFilter(PreparedStatement ps) throws SQLException
    {
    }
    
    
    //=== Getters & Setters
    public long getIdSertifikat()
    {
        return idSertifikat;
    }

    public void setIdSertifikat(long idSertifikat)
    {
        this.idSertifikat = idSertifikat;
    }

    public String getNaziv()
    {
        return naziv;
    }

    public void setNaziv(String naziv)
    {
        this.naziv = naziv;
    }

    public String getOpis()
    {
        return opis;
    }

    public void setOpis(String opis)
    {
        this.opis = opis;
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
