/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package domain;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Yo
 */
public class Racun implements AbstractDomainObject
{
    private long idRacun;
    private LocalDateTime datum;
    private double ukupanIznos;
    private Travar travar;
    private Kupac kupac;
    private List<StavkaRacuna> stavkeRacuna;

    public Racun(long idRacun, LocalDateTime datum, double ukupanIznos, Travar travar, Kupac kupac, List<StavkaRacuna> stavkeRacuna)
    {
        this.idRacun = idRacun;
        this.datum = datum;
        this.ukupanIznos = ukupanIznos;
        this.travar = travar;
        this.kupac = kupac;
        this.stavkeRacuna = stavkeRacuna;
    }

    public Racun()
    {
    }

    
    //=== Implemented ADO methods ===
    @Override
    public String getTableName()
    {
        return " racun ";
    }

    @Override
    public String getAlias()
    {
        return " r ";
    }

    @Override
    public String getJoinCondition()
    {
        return " JOIN travar t ON (t.idTravar = r.idTravar)\n"
                + "JOIN kupac k ON (k.idKupac = r.idKupac)\n"
                + "JOIN mesto m oN (m.idMesto = k.idMesto)\n";
    }

    @Override
    public List<AbstractDomainObject> getList(ResultSet rs) throws SQLException
    {
        List<AbstractDomainObject> lista = new ArrayList<>();
        
        while (rs.next())
        {
            Travar t = new Travar(rs.getLong("idTravar"),rs.getString("t.ime"), 
                    rs.getString("t.prezime"), rs.getString("t.telefon"),
                    rs.getString("korisnickoIme"), rs.getString("sifra"));
            
            Mesto m  = new Mesto(rs.getLong("idMesto"), rs.getString("m.naziv"));
            
            Kupac k = new Kupac(rs.getLong("idKupac"), rs.getString("k.ime"), rs.getString("k.prezime"), 
                    rs.getString("k.telefon"), m);
            
            Racun r = new Racun(rs.getLong("idRacun"), rs.getObject("datum", LocalDateTime.class), 
                    rs.getDouble("ukupanIznos"), t, k, null);
            
            lista.add(r);
        }
        
        return lista;
    }

    @Override
    public String getInsertColumns()
    {
        return " (datum, ukupanIznos, idTravar, idKupac) ";
    }

    @Override
    public String getInsertPlaceholders()
    {
        return " ?, ?, ?, ? ";
    }

    @Override
    public String getUpdatePlaceholders()
    {
        return " datum = ?, ukupanIznos = ? ";

    }

    @Override
    public String getConditionPlaceholder()
    {
        return "idRacun = ? ";
    }

    @Override
    public String getSelectConditionPlaceholder()
    {
        if (this.kupac != null && this.travar != null){
            return " WHERE k.idKupac = ? AND t.idTravar = ? ";
        }
        
        if (this.kupac != null)
            return " WHERE k.idKupac = ? ";
        
        if (this.travar != null)
            return " WHERE t.idTravar = ? ";
        
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
        ps.setTimestamp(1, Timestamp.valueOf(datum));
        ps.setDouble(2, ukupanIznos);
        ps.setLong(3, travar.getIdTravar());
        ps.setLong(4, kupac.getIdKupac());
        
    }

    @Override
    public void prepareUpdate(PreparedStatement ps) throws SQLException
    {
        ps.setTimestamp(1, Timestamp.valueOf(datum));
        ps.setDouble(2, ukupanIznos);
        ps.setLong(3, idRacun);
    }
    
    @Override
    public void prepareCondition(PreparedStatement ps) throws SQLException
    {
        ps.setLong(1, idRacun);
    }

    @Override
    public void prepareSelect(PreparedStatement ps) throws SQLException
    {
        if (kupac != null && travar != null)
        {
            ps.setLong(1, kupac.getIdKupac());
            ps.setLong(2, travar.getIdTravar());
            
            return;
        }
        
        if (kupac != null)
        {
            ps.setLong(1, kupac.getIdKupac());
            return;
        }
        
        if (travar != null)
            ps.setLong(1, travar.getIdTravar());
            
    }

    @Override
    public void prepareFilter(PreparedStatement ps) throws SQLException
    {
    }
    
    
    //=== Getters & Setters
    public long getIdRacun()
    {
        return idRacun;
    }

    public void setIdRacun(long idRacun)
    {
        this.idRacun = idRacun;
    }

    public LocalDateTime getDatum()
    {
        return datum;
    }

    public void setDatum(LocalDateTime datum)
    {
        this.datum = datum;
    }

    public double getUkupanIznos()
    {
        return ukupanIznos;
    }

    public void setUkupanIznos(double ukupanIznos)
    {
        this.ukupanIznos = ukupanIznos;
    }

    public Travar getTravar()
    {
        return travar;
    }

    public void setTravar(Travar travar)
    {
        this.travar = travar;
    }

    public Kupac getKupac()
    {
        return kupac;
    }

    public void setKupac(Kupac kupac)
    {
        this.kupac = kupac;
    }

    public List<StavkaRacuna> getStavkeRacuna()
    {
        return stavkeRacuna;
    }

    public void setStavkeRacuna(ArrayList<StavkaRacuna> stavkeRacuna)
    {
        this.stavkeRacuna = stavkeRacuna;
    }
    
    
    
    public void addStavka(StavkaRacuna stavka)
    {
        stavkeRacuna.add(stavka);
        ukupanIznos += stavka.getIznos();
    }
    
    public void removeStavka(StavkaRacuna stavka)
    {
        stavkeRacuna.remove(stavka);
        ukupanIznos -= stavka.getIznos();
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
