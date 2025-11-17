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
public class Sertifikat implements AbstractDomainObject
{
    private long idSertifikat;
    private String naziv;
    private String opis;

    public Sertifikat(long idSertifikat, String naziv, String opis)
    {
        this.idSertifikat = idSertifikat;
        this.naziv = naziv;
        this.opis = opis;
    }

    @Override
    public String nazivTabele()
    {
        return " sertifikat ";
    }

    @Override
    public String alijas()
    {
        return " s ";
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
            Sertifikat s = new Sertifikat(rs.getLong("idSertifikat"), rs.getString("naziv"), rs.getString("opis"));
            lista.add(s);
        }
        rs.close();
        
        return lista;
    }

    @Override
    public String koloneZaInsert()
    {
        return " (naziv, opis) ";
    }

    @Override
    public String vrednostiZaInsert()
    {
        return " '" + naziv + "', '" + opis + "' ";
    }

    @Override
    public String vrednostiZaUpdate()
    {
        return " naziv = '" + naziv + "', opis = '" + opis + "' ";
    }

    @Override
    public String uslov()
    {
        return " idSertifikat = " + idSertifikat;
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
    
    public long getIdSertifikat()
    {
        return idSertifikat;
    }

    public void setIdSertifikat(long idSertifikat)
    {
        this.idSertifikat = idSertifikat;
    }

    public String getNaziv()
    {
        return naziv;
    }

    public void setNaziv(String naziv)
    {
        this.naziv = naziv;
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
