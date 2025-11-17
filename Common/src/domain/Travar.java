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
public class Travar implements AbstractDomainObject
{
    private long idTravar;
    private String ime;
    private String prezime;
    private String telefon;
    private String username;
    private String password;

    public Travar()
    {
    }

    public Travar(long idTravar, String ime, String prezime, String telefon, String username, String password)
    {
        this.idTravar = idTravar;
        this.ime = ime;
        this.prezime = prezime;
        this.telefon = telefon;
        this.username = username;
        this.password = password;
    }

    @Override
    public String toString()
    {
        return ime + " " + prezime + " (@" + username + ")";
    }

    @Override
    public int hashCode()
    {
        int hash = 3;
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
        final Travar other = (Travar) obj;
        return this.username.equals(other.username);
    }

    
    @Override
    public String nazivTabele()
    {
        return " travar ";
    }

    @Override
    public String alijas()
    {
        return " t ";
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
            Travar t = new Travar(rs.getLong("idTravar"),rs.getString("ime"), 
                    rs.getString("prezime"), rs.getString("telefon"),
                    rs.getString("korisnickoIme"), rs.getString("sifra"));
            lista.add(t);
        }
        rs.close();
        
        return lista;
    }

    @Override
    public String koloneZaInsert()
    {
        return " (ime, prezime, telefon, korisnickoIme, sifra) ";
    }

    @Override
    public String vrednostiZaInsert()
    {
         return " '" + ime + "', '" + prezime + "', " + "'" + telefon + "', " 
                + "'" + username + "', '" + password + "' ";
    }

    @Override
    public String vrednostiZaUpdate()
    {
        return " ime = '" + ime + "', prezime = '" + prezime + "', telefon = " + 
                "'" + telefon + "', korisnickoIme = " 
                + "'" + username + "', sifra = '" + password + "' ";
    }

    @Override
    public String uslov()
    {
        return " idTravar = " + idTravar;
    }

    @Override
    public String uslovZaSelect()
    {
        return "";
    }

    @Override
    public String uslovZaFilter()
    {
        return "";
    }
    
    public long getIdTravar()
    {
        return idTravar;
    }

    public void setIdTravar(long idTravar)
    {
        this.idTravar = idTravar;
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

    public String getUsername()
    {
        return username;
    }

    public void setUsername(String username)
    {
        this.username = username;
    }

    public String getPassword()
    {
        return password;
    }

    public void setPassword(String password)
    {
        this.password = password;
    }    
}
