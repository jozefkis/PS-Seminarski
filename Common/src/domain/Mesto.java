/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package domain;

import java.util.ArrayList;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;


/**
 *
 * @author Yo
 */
public class Mesto implements AbstractDomainObject
{
    private long idMesto;
    private String naziv;

    @Override
    public String toString() 
    {
        return naziv;
    }

    public Mesto(long idMesto, String naziv) 
    {
        this.idMesto = idMesto;
        this.naziv = naziv;
    }

    public Mesto() 
    {
        
    }

    @Override
    public String nazivTabele() 
    {
        return " mesto ";
    }

    @Override
    public String alijas() 
    {
        return " m ";
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
            Mesto m = new Mesto(rs.getLong("idMesto"),rs.getString("m.naziv"));
            lista.add(m);
        }

        rs.close();
        return lista;
    }

    @Override
    public String koloneZaInsert() 
    {
        return " (naziv) ";
    }

    @Override
    public String vrednostiZaInsert() 
    {
        return " '" + naziv + "' ";
    }

    @Override
    public String vrednostiZaUpdate() 
    {
        return " naziv = '" + naziv + "' ";
    }

    @Override
    public String uslov() 
    {
        return " idMesto = " + idMesto;
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
    
    public long getIdMesto()
    {
        return idMesto;
    }

    public void setIdMesto(long idMesto)
    {
        this.idMesto = idMesto;
    }

    public String getNaziv() 
    {
        return naziv;
    }

    public void setNaziv(String naziv) 
    {
        this.naziv = naziv;
    }
}
