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
import java.util.Objects;

/**
 *
 * @author Yo
 */
public class Kupac implements AbstractDomainObject
{
    private long idKupac;
    private String ime;
    private String prezime;
    private String telefon;
    private Mesto mesto;

    public Kupac(long idKupac, String ime, String prezime, String telefon, Mesto mesto)
    {
        this.idKupac = idKupac;
        this.ime = ime;
        this.prezime = prezime;
        this.telefon = telefon;
        this.mesto = mesto;
    }

    public Kupac(String ime, String prezime, String telefon, Mesto mesto)
    {
        this.ime = ime;
        this.prezime = prezime;
        this.telefon = telefon;
        this.mesto = mesto;
    }

    public Kupac() {}

    @Override
    public String toString()
    {
        return ime + " " + prezime + " (Telefon: " + telefon + ")";
    }

    @Override
    public int hashCode()
    {
        return Objects.hash(idKupac, ime, prezime);
    }

    @Override
    public boolean equals(Object obj)
    {
        if (this == obj)
        {
            return true;
        }
        if (obj == null || getClass() != obj.getClass())
        {
            return false;
        }
        final Kupac other = (Kupac) obj;
        return idKupac == other.idKupac &&
               Objects.equals(ime, other.ime) &&
               Objects.equals(prezime, other.prezime);
    }


    //=== Implemented ADO methods ===
    @Override
    public String getTableName()
    {
        return "kupac";
    }

    @Override
    public String getAlias()
    {
        return "k";
    }

    @Override
    public String getJoinCondition()
    {
        return "JOIN Mesto m ON (m.idMesto = k.idMesto)";
    }

    @Override
    public List<AbstractDomainObject> getList(ResultSet rs) throws SQLException
    {
        List<AbstractDomainObject> lista = new ArrayList<>();
        while (rs.next())
        {
            Mesto m = new Mesto(rs.getLong("idMesto"), rs.getString("m.naziv"));
            Kupac k = new Kupac(rs.getLong("idKupac"),
                                rs.getString("ime"),
                                rs.getString("prezime"),
                                rs.getString("telefon"),
                                m);
            lista.add(k);
        }
        return lista;
    }

    @Override
    public String getInsertColumns()
    {
        return "(ime, prezime, telefon, idMesto)";
    }

    @Override
    public String getInsertPlaceholders()
    {
        return "?, ?, ?, ?";
    }

    @Override
    public String getUpdatePlaceholders()
    {
        return "ime = ?, prezime = ?, telefon = ?, idMesto = ?";
    }

    @Override
    public String getConditionPlaceholder()
    {
        return "idKupac = ?";
    }

    @Override
    public String getSelectConditionPlaceholder()
    {
        return " ORDER BY prezime,ime ASC ";
    }

    @Override
    public String getFilterConditionPlaceholder()
    {
        return " LOWER(CONCAT(ime, ' ', prezime)) LIKE ? ";
    }

    @Override
    public void prepareInsert(PreparedStatement ps) throws SQLException
    {
        ps.setString(1, ime);
        ps.setString(2, prezime);
        ps.setString(3, telefon);
        ps.setLong(4, mesto.getIdMesto());
    }

    @Override
    public void prepareUpdate(PreparedStatement ps) throws SQLException
    {
        ps.setString(1, ime);
        ps.setString(2, prezime);
        ps.setString(3, telefon);
        ps.setLong(4, mesto.getIdMesto());
        ps.setLong(5, idKupac);
    }
    
    @Override
    public void prepareCondition(PreparedStatement ps) throws SQLException
    {
        ps.setLong(1, idKupac);
    }

    @Override
    public void prepareSelect(PreparedStatement ps) throws SQLException
    {
    }

    @Override
    public void prepareFilter(PreparedStatement ps) throws SQLException
    {
        String filterValue = "";
        if (ime != null && !ime.isEmpty())
            filterValue += ime;
        if (prezime != null && !prezime.isEmpty())
            filterValue += " " + prezime;
        
        filterValue +="%";
        
        ps.setString(1, filterValue.toLowerCase());
    }


    //=== Getters & Setters ===
    public long getIdKupac()
    {
        return idKupac;
    }

    public void setIdKupac(long idKupac)
    {
        this.idKupac = idKupac;
    }

    public String getIme()
    {
        return ime;
    }

    public void setIme(String ime)
    {
        this.ime = ime;
    }

    public String getPrezime()
    {
        return prezime;
    }

    public void setPrezime(String prezime)
    {
        this.prezime = prezime;
    }

    public String getTelefon()
    {
        return telefon;
    }

    public void setTelefon(String telefon)
    {
        this.telefon = telefon;
    }

    public Mesto getMesto()
    {
        return mesto;
    }

    public void setMesto(Mesto mesto)
    {
        this.mesto = mesto;
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
