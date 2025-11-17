/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package domain;

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

    public Caj()
    {
    }

    @Override
    public String toString()
    {
        return naziv + " (" + String.format("%.2f", cena) + " rsd)";
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
    public List<AbstractDomainObject> vratiListu(ResultSet rs) throws SQLException
    {
        List<AbstractDomainObject> lista = new ArrayList<>();
        
        while(rs.next())
        {
            Caj c = new Caj(rs.getLong("idCaj"),rs.getString("naziv"), 
                    rs.getDouble("cena"), rs.getString("korisnickoUputstvo"),
                    rs.getString("opis"));
            lista.add(c);
        }
        rs.close();
        
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
        return "'" + naziv + "', " + cena + ", " + 
                "'" + korisnickoUputstvo + "', " + "'" + opis + "' ";
    }

    @Override
    public String vrednostiZaUpdate()
    {
        return " naziv = '" + naziv + "', cena = " + cena + ", korisnickoUputsvo = " + 
                "'" + korisnickoUputstvo + "', opis = " + "'" + opis + "' ";
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
    
    
}
