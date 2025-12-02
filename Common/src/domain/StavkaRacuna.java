/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package domain;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 *
 * @author Yo
 */
public class StavkaRacuna implements AbstractDomainObject
{
    private Racun racun;
    private int rb;
    private int kolicina;
    private double cena;
    private double iznos;
    private Caj caj;

    public StavkaRacuna(Racun racun, int rb, int kolicina, double cena, double iznos, Caj caj)
    {
        this.racun = racun;
        this.rb = rb;
        this.kolicina = kolicina;
        this.cena = cena;
        this.iznos = iznos;
        this.caj = caj;
    }

    public StavkaRacuna()
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
        final StavkaRacuna other = (StavkaRacuna) obj;
        return Objects.equals(this.caj, other.caj);
    }
    
    
    

    //=== Implement ADO methods ===
    @Override
    public String getTableName()
    {
        return " stavkaracuna ";
    }

    @Override
    public String getAlias()
    {
        return " sr ";
    }

    @Override
    public String getJoinCondition()
    {
        return " JOIN caj c ON (c.idCaj = sr.idCaj)\n"
                + "JOIN racun r ON (r.idRacun = sr.idRacun)\n"
                + "JOIN travar t ON (t.idTravar = r.idTravar)\n"
                + "JOIN kupac k ON (k.idKupac = r.idKupac)\n"
                + "JOIN mesto m ON (m.idMesto = k.idMesto)";
    }

    @Override
    public List<AbstractDomainObject> getList(ResultSet rs) throws SQLException
    {
        List<AbstractDomainObject> lista = new ArrayList<>();
        
        while (rs.next())
        {
            Travar t = new Travar(rs.getLong("idTravar"),rs.getString("t.ime"), 
                    rs.getString("t.prezime"), rs.getString("t.telefon"),
                    rs.getString("t.korisnickoIme"), rs.getString("t.sifra"));
            
            Mesto m = new Mesto(rs.getLong("m.idMesto"),rs.getString("m.naziv"));
            
            Kupac k = new Kupac(rs.getLong("idKupac"), rs.getString("k.ime"), rs.getString("k.prezime"), 
                    rs.getString("k.telefon"), m);
            
            Racun r = new Racun(rs.getLong("idRacun"), rs.getObject("r.datum", LocalDateTime.class), 
                    rs.getDouble("r.ukupanIznos"), t, k, null);
            
            Caj c = new Caj(rs.getLong("idCaj"),rs.getString("c.naziv"), 
                    rs.getDouble("c.cena"), rs.getString("c.korisnickoUputstvo"),
                    rs.getString("c.opis"));
            
            StavkaRacuna sr = new StavkaRacuna(r, rs.getInt("rb"), rs.getInt("kolicina"),
                    rs.getDouble("sr.cena"), rs.getDouble("sr.iznos"), c);
            
            lista.add(sr);
        }
        
        return lista;
    }

    @Override
    public String getInsertColumns()
    {
        return " (idRacun, rb, kolicina, cena, iznos, idCaj) ";
    }

    @Override
    public String getInsertPlaceholders()
    {
        return " ?, ?, ?, ?, ?, ? ";
    }

    @Override
    public String getUpdatePlaceholders()
    {
        return "";
    }

    @Override
    public String getConditionPlaceholder()
    {
        return " idRacun = ? ";
    }

    @Override
    public String getSelectConditionPlaceholder()
    {
        if (racun != null) 
        {
            return " WHERE r.idRacun = ? ";
        }
        return " WHERE c.idCaj = ? ";
    }

    @Override
    public String getFilterConditionPlaceholder()
    {
        return "";
    }
    
    @Override
    public void prepareInsert(PreparedStatement ps) throws SQLException
    {
        ps.setLong(1, racun.getIdRacun());
        ps.setInt(2, rb);
        ps.setInt(3, kolicina);
        ps.setDouble(4, cena);
        ps.setDouble(5, iznos);
        ps.setLong(6, caj.getIdCaj());
    }

    @Override
    public void prepareUpdate(PreparedStatement ps) throws SQLException
    {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
    
    @Override
    public void prepareCondition(PreparedStatement ps) throws SQLException
    {
        ps.setLong(1, racun.getIdRacun());
    }

    @Override
    public void prepareSelect(PreparedStatement ps) throws SQLException
    {
        if (racun != null) 
        {
            ps.setLong(1, racun.getIdRacun());
        }
        else
        {
            
            ps.setLong(1, caj.getIdCaj());
        }
    }

    @Override
    public void prepareFilter(PreparedStatement ps) throws SQLException
    {
    }
    
    
    //=== Getters & Setters ===
    public Racun getRacun()
    {
        return racun;
    }

    public void setRacun(Racun racun)
    {
        this.racun = racun;
    }

    public int getRb()
    {
        return rb;
    }

    public void setRb(int rb)
    {
        this.rb = rb;
    }

    public int getKolicina()
    {
        return kolicina;
    }

    public void setKolicina(int kolicina)
    {
        this.kolicina = kolicina;
    }

    public double getCena()
    {
        return cena;
    }

    public void setCena(double cena)
    {
        this.cena = cena;
    }

    public double getIznos()
    {
        return iznos;
    }

    public void setIznos(double iznos)
    {
        this.iznos = iznos;
    }

    public Caj getCaj()
    {
        return caj;
    }

    public void setCaj(Caj caj)
    {
        this.caj = caj;
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
