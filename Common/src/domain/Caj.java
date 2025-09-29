/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package domain;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 *
 * @author Yo
 */
public class Caj extends AbstractDomainObject
{
    private int idCaj;
    private String naziv;
    private double cena;
    private String korisnickoUputstvo;
    private String opis;

    public Caj(int idCaj, String naziv, double cena, String korisnickoUputstvo, String opis)
    {
        this.idCaj = idCaj;
        this.naziv = naziv;
        this.cena = cena;
        this.korisnickoUputstvo = korisnickoUputstvo;
        this.opis = opis;
    }

    public Caj()
    {
    }
   

    @Override
    public String nazivTabele()
    {
        return " caj ";
    }

    @Override
    public String alijas()
    {
        return " c ";
    }

    @Override
    public String join()
    {
        return "";
    }

    @Override
    public ArrayList<AbstractDomainObject> vratiListu(ResultSet rs) throws SQLException
    {
        ArrayList<AbstractDomainObject> lista = new ArrayList<>();
        
        while(rs.next())
        {
            Caj c = new Caj(rs.getInt("idCaj"),rs.getString("naziv"), 
                    rs.getDouble("cena"), rs.getString("korisnickoUputstvo"),
                    rs.getString("opis"));
            lista.add(c);
        }
        return lista;
       
    }

    @Override
    public String koloneZaInsert()
    {
        return " (naziv, cena, korisnickoUputstvo, opis) ";
    }

    @Override
    public String vrednostiZaInsert()
    {
        return "'" + naziv + "', '" + cena + "', " + 
                "'" + korisnickoUputstvo + "', " + "'" + opis + "'";
    }

    @Override
    public String vrednostiZaUpdate()
    {
        return " naziv = '" + naziv + "', cena = '" + cena + "', korisnickoUputsvo = " + 
                "'" + korisnickoUputstvo + "', opis = " + "'" + opis + "'";
    }

    @Override
    public String uslov()
    {
        return " idCaj = " + idCaj;
    }

    @Override
    public String uslovZaSelect()
    {
        return "";
    }

    public int getIdCaj()
    {
        return idCaj;
    }

    public void setIdCaj(int idCaj)
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
}
