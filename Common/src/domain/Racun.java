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

    public Racun(long idRacun, LocalDateTime datum, double ukupanIznos, Travar travar, Kupac kupac, ArrayList<StavkaRacuna> stavkeRacuna)
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
    public List<AbstractDomainObject> vratiListu(ResultSet rs) throws SQLException
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
        return " '" + Timestamp.valueOf(datum) + "', " + ukupanIznos + ", " + travar.getIdTravar() + ", " + kupac.getIdKupac() + " ";
    }

    @Override
    public String vrednostiZaUpdate()
    {
        return " datum = '" + Timestamp.valueOf(datum) + "', "
                + "ukupanIznos = " + ukupanIznos + " ";

    }

    @Override
    public String uslov()
    {
        return "idRacun = " + idRacun;
    }

    @Override
    public String uslovZaSelect()
    {
        if(kupac != null){
            return " WHERE k.idKupac = " + kupac.getIdKupac();
        }
        return "";
    }

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
    
    public void dodajStavku(StavkaRacuna stavka)
    {
        stavkeRacuna.add(stavka);
        ukupanIznos += stavka.getIznos();
    }
    
    public void izbaciStavku(StavkaRacuna stavka)
    {
        stavkeRacuna.remove(stavka);
        ukupanIznos -= stavka.getIznos();
    }
}
