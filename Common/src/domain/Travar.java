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

    
    //=== Implemented ADO methods ===
    @Override
    public String getTableName()
    {
        return " travar ";
    }

    @Override
    public String getAlias()
    {
        return " t ";
    }

    @Override
    public String getJoinCondition()
    {
        return "";
    }

    @Override
    public List<AbstractDomainObject> getList(ResultSet rs) throws SQLException
    {
        List<AbstractDomainObject> lista = new ArrayList<>();
        
        while(rs.next())
        {
            Travar t = new Travar(rs.getLong("idTravar"),rs.getString("ime"), 
                    rs.getString("prezime"), rs.getString("telefon"),
                    rs.getString("korisnickoIme"), rs.getString("sifra"));
            lista.add(t);
        }
        
        return lista;
    }

    @Override
    public String getInsertColumns()
    {
        return " (ime, prezime, telefon, korisnickoIme, sifra) ";
    }

    @Override
    public String getInsertPlaceholders()
    {
         return " ?, ?, ?, ?, ? ";
    }

    @Override
    public String getUpdatePlaceholders()
    {
        return " ime = ?, prezime = ?, telefon = ?, korisnickoIme = ?, sifra = ? ";
    }

    @Override
    public String getConditionPlaceholder()
    {
        return " idTravar = ? ";
    }

    @Override
    public String getSelectConditionPlaceholder()
    {
        return "";
    }

    @Override
    public String getFilterConditionPlaceholder()
    {
        return "";
    }
   
     @Override
    public String getExistenceConditionPlaceholder()
    {
        return " korisnickoIme = ? AND sifra = ? ";
    }
    
    @Override
    public void prepareInsert(PreparedStatement ps) throws SQLException
    {
        ps.setString(1, ime);
        ps.setString(2, prezime);
        ps.setString(3, telefon);
        ps.setString(4, username);
        ps.setString(5, password);
    }

    @Override
    public void prepareUpdate(PreparedStatement ps) throws SQLException
    {
        ps.setString(1, ime);
        ps.setString(2, prezime);
        ps.setString(3, telefon);
        ps.setString(4, username);
        ps.setString(5, password);
        ps.setLong(6, idTravar);
    }
    
    @Override
    public void prepareCondition(PreparedStatement ps) throws SQLException
    {
        ps.setLong(1, idTravar);
    }

    @Override
    public void prepareSelect(PreparedStatement ps) throws SQLException
    {
    }

    @Override
    public void prepareFilter(PreparedStatement ps) throws SQLException
    {
    }
    
    @Override
    public void prepareExistenceCondition(PreparedStatement ps) throws SQLException
    {
        ps.setString(1, username);
        ps.setString(2, password);
    }
    
    //=== Getters & Setters
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
