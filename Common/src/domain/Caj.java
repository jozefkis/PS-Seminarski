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
public class Caj implements AbstractDomainObject
{
    private long idCaj;
    private String naziv;
    private double cena;
    private String korisnickoUputstvo;
    private String opis;

    public Caj(long idCaj, String naziv, double cena, String korisnickoUputstvo, String opis)
    {
        this.idCaj = idCaj;
        this.naziv = naziv;
        this.cena = cena;
        this.korisnickoUputstvo = korisnickoUputstvo;
        this.opis = opis;
    }

    public Caj(String naziv, double cena, String korisnickoUputstvo, String opis)
    {
        this.naziv = naziv;
        this.cena = cena;
        this.korisnickoUputstvo = korisnickoUputstvo;
        this.opis = opis;
    }
    
    

    public Caj()
    {
    }

    @Override
    public String toString()
    {
        return naziv + " (" + String.format("%.2f", cena) + " rsd)";
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
        final Caj other = (Caj) obj;
        if (this.idCaj != other.idCaj)
        {
            return false;
        }
        return Objects.equals(this.naziv, other.naziv);
    }
    
    
    //=== Implemented ADO methods ===
    @Override
    public String getTableName()
    {
        return " caj ";
    }

    @Override
    public String getAlias()
    {
        return " c ";
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
            Caj c = new Caj(rs.getLong("idCaj"),rs.getString("naziv"), 
                    rs.getDouble("cena"), rs.getString("korisnickoUputstvo"),
                    rs.getString("opis"));
            lista.add(c);
        }
        
        return lista;    
    }

    @Override
    public String getInsertColumns()
    {
        return " (naziv, cena, korisnickoUputstvo, opis) ";
    }

    @Override
    public String getInsertPlaceholders()
    {
        return " ?, ?, ?, ? ";
    }

    @Override
    public String getUpdatePlaceholders()
    {
        return " naziv = ?, cena = ?, korisnickoUputstvo = ?, opis = ? ";
    }

    @Override
    public String getConditionPlaceholder()
    {
        return " idCaj = ? ";
    }

    @Override
    public String getSelectConditionPlaceholder()
    {
        return "";
    }
    
    @Override
    public String getFilterConditionPlaceholder()
    {
        return "LOWER(naziv) LIKE ? ";
    }

    @Override
    public void prepareInsert(PreparedStatement ps) throws SQLException
    {
        ps.setString(1, naziv);
        ps.setDouble(2, cena);
        ps.setString(3, korisnickoUputstvo);
        ps.setString(4, opis);
    }

    @Override
    public void prepareUpdate(PreparedStatement ps) throws SQLException
    {
        ps.setString(1, naziv);
        ps.setDouble(2, cena);
        ps.setString(3, korisnickoUputstvo);
        ps.setString(4, opis);
        ps.setLong(5, idCaj);
    }
    
    @Override
    public void prepareCondition(PreparedStatement ps) throws SQLException
    {
        ps.setLong(1, idCaj);
    }

    @Override
    public void prepareSelect(PreparedStatement ps) throws SQLException
    {
    }

    @Override
    public void prepareFilter(PreparedStatement ps) throws SQLException
    {
        ps.setString(1, naziv.toLowerCase()+"%");
    }
    
    
    //=== Getters & Setters ===
    public long getIdCaj()
    {
        return idCaj;
    }

    public void setIdCaj(long idCaj)
    {
        this.idCaj = idCaj;
    }

    public String getNaziv()
    {
        return naziv;
    }

    public void setNaziv(String naziv)
    {
        this.naziv = naziv;
    }

    public double getCena()
    {
        return cena;
    }

    public void setCena(double cena)
    {
        this.cena = cena;
    }

    public String getKorisnickoUputstvo()
    {
        return korisnickoUputstvo;
    }

    public void setKorisnickoUputstvo(String korisnickoUputstvo)
    {
        this.korisnickoUputstvo = korisnickoUputstvo;
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
