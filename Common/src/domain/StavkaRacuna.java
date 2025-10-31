/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package domain;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.ArrayList;

/**
 *
 * @author Yo
 */
public class StavkaRacuna extends AbstractDomainObject
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
    public String nazivTabele()
    {
        return " stavkaracuna ";
    }

    @Override
    public String alijas()
    {
        return " sr ";
    }

    @Override
    public String join()
    {
        return " JOIN caj c ON (c.idCaj = sr.idCaj)\n"
                + "JOIN racun r ON (r.idRacun = sr.idRacun)\n"
                + "JOIN travar t ON (t.idTravar = r.idTravar)\n"
                + "JOIN kupac k ON (k.idKupac = r.idKupac)\n"
                + "JOIN mesto m ON (m.idMesto = k.idMesto)";
    }

    @Override
    public ArrayList<AbstractDomainObject> vratiListu(ResultSet rs) throws SQLException
    {
        ArrayList<AbstractDomainObject> lista = new ArrayList<>();
        
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
        rs.close();
        
        return lista;
    }

    @Override
    public String koloneZaInsert()
    {
        return " (idRacun, rb, kolicina, cena, iznos, idCaj) ";
    }

    @Override
    public String vrednostiZaInsert()
    {
        return " " + racun.getIdRacun() + ", " + rb + ", " + kolicina + ", " + cena +
                ", " + iznos + ", " + caj.getIdCaj() + " ";
    }

    @Override
    public String vrednostiZaUpdate()
    {
        return "";
    }

    @Override
    public String uslov()
    {
        return " idRacun = " + racun.getIdRacun();
    }

    @Override
    public String uslovZaSelect()
    {
        if (racun != null) 
        {
            return " WHERE r.idRacun = " + racun.getIdRacun();
        }
        return " WHERE c.idCaj = " + caj.getIdCaj();
    }

    
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
}
