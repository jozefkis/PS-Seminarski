/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package domain;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.ArrayList;

/**
 *
 * @author Yo
 */
public class Racun extends AbstractDomainObject
{
    private int idRacun;
    private LocalDateTime datum;
    private double ukupanIznos;
    private Travar travar;
    private Kupac kupac;
    private ArrayList<StavkaRacuna> stavkeRacuna;

    public Racun(int idRacun, LocalDateTime datum, double ukupanIznos, Travar travar, Kupac kupac, ArrayList<StavkaRacuna> stavkeRacuna)
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

    @Override
    public String nazivTabele()
    {
        return " racun ";
    }

    @Override
    public String alijas()
    {
        return " r ";
    }

    @Override
    public String join()
    {
        return " JOIN travar t ON (t.idTravar = r.idTravar)\n"
                + "JOIN kupac k ON (k.idKupac = r.idKupac)\n"
                + "JOIN mesto m oN (m.idMesto = k.idMesto)";
    }

    @Override
    public ArrayList<AbstractDomainObject> vratiListu(ResultSet rs) throws SQLException
    {
        ArrayList<AbstractDomainObject> lista = new ArrayList<>();
        
        while (rs.next())
        {
            Travar t = new Travar(rs.getInt("idTravar"),rs.getString("t.ime"), 
                    rs.getString("t.prezime"), rs.getString("t.telefon"),
                    rs.getString("korisnickoIme"), rs.getString("sifra"));
            
            Mesto m  = new Mesto(rs.getInt("idMesto"), rs.getString("m.naziv"));
            
            Kupac k = new Kupac(rs.getInt("idKupac"), rs.getString("k.ime"), rs.getString("k.prezime"), 
                    rs.getString("k.telefon"), m);
            
            Racun r = new Racun(rs.getInt("idRacun"), rs.getObject("datum", LocalDateTime.class), 
                    rs.getDouble("ukupanIznos"), t, k, null);
            
            lista.add(r);
        }
        rs.close();
        
        return lista;
    }

    @Override
    public String koloneZaInsert()
    {
        return " (datum, ukupanIznos, idTravar, idKupac) ";
    }

    @Override
    public String vrednostiZaInsert()
    {
        return "'" + Timestamp.valueOf(datum);
    }

    @Override
    public String vrednostiZaUpdate()
    {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public String uslov()
    {
        return "";
    }

    @Override
    public String uslovZaSelect()
    {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    public int getIdRacun()
    {
        return idRacun;
    }

    public void setIdRacun(int idRacun)
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

    public ArrayList<StavkaRacuna> getStavkeRacuna()
    {
        return stavkeRacuna;
    }

    public void setStavkeRacuna(ArrayList<StavkaRacuna> stavkeRacuna)
    {
        this.stavkeRacuna = stavkeRacuna;
    }
}
