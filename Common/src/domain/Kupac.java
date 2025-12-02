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
    public String nazivTabele()
    {
        return "kupac";
    }

    @Override
    public String alijas()
    {
        return "k";
    }

    @Override
    public String join()
    {
        return "JOIN Mesto m ON (m.idMesto = k.idMesto)";
    }

    @Override
    public List<AbstractDomainObject> vratiListu(ResultSet rs) throws SQLException
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
    public String koloneZaInsert()
    {
        return "(ime, prezime, telefon, idMesto)";
    }

    @Override
    public String vrednostiZaInsert()
    {
        return "?, ?, ?, ?";
    }

    @Override
    public String vrednostiZaUpdate()
    {
        return "ime = ?, prezime = ?, telefon = ?, idMesto = ?";
    }

    @Override
    public String uslov()
    {
        return "idKupac = ?";
    }

    @Override
    public String uslovZaSelect()
    {
        return "";
    }

    @Override
    public String uslovZaFilter()
    {
        return "WHERE LOWER(CONCAT(ime, ' ', prezime)) LIKE ?";
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
}
