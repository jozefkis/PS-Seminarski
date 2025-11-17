/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package domain;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

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

    public Kupac()
    {
    }

    @Override
    public String toString()
    {
        return ime + " " + prezime + " (Telefon: " + telefon + ")";
    }
    
    
    @Override
    public String nazivTabele()
    {
        return " kupac ";
    }

    @Override
    public String alijas()
    {
        return " k ";
    }

    @Override
    public String join()
    {
        return " JOIN Mesto m ON (m.idMesto = k.idMesto) ";
    }

    @Override
    public List<AbstractDomainObject> vratiListu(ResultSet rs) throws SQLException
    {
        List<AbstractDomainObject> lista = new ArrayList<>();
        
        while (rs.next())
        {
            Mesto m  = new Mesto(rs.getLong("idMesto"), rs.getString("m.naziv"));
            
            Kupac k = new Kupac(rs.getLong("idKupac"), rs.getString("ime"), rs.getString("prezime"), 
                    rs.getString("telefon"), m);
            
            lista.add(k);
        }
        rs.close();
        
        return lista;
    }

    @Override
    public String koloneZaInsert()
    {
        return " (ime, prezime, telefon, idMesto) ";
    }

    @Override
    public String vrednostiZaInsert()
    {
        return "'" + ime + "', '" + prezime + "', " + "'" + telefon + "', " 
                 + mesto.getIdMesto() + " ";
    }

    @Override
    public String vrednostiZaUpdate()
    {
        return " ime = '" + ime + "', prezime = '" + prezime + "', telefon = " + 
                "'" + telefon + "', idMesto = " + mesto.getIdMesto() + " ";
    }

    @Override
    public String uslov()
    {
        return " idKupac = " + idKupac;
    }

    @Override
    public String uslovZaSelect()
    {
        return "";
    }
    
    @Override
    public String uslovZaFilter()
    {
        return " WHERE LOWER(CONCAT(ime, ' ', prezime)) LIKE ? ";
    }
    
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
}
