/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package domain;

import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.PreparedStatement;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Yo
 */
public class TrvS implements AbstractDomainObject
{
    private Travar travar;
    private Sertifikat sertifikat;
    private LocalDate datumIzdavanja;

    public TrvS(Travar travar, Sertifikat sertifikat, LocalDate datumIzdavanja)
    {
        this.travar = travar;
        this.sertifikat = sertifikat;
        this.datumIzdavanja = datumIzdavanja;
    }

    public TrvS()
    {
    }
    
    
    //=== Implemented ADO methods
    @Override
    public String nazivTabele()
    {
        return " trvs ";
    }

    @Override
    public String alijas()
    {
        return " ts ";
    }

    @Override
    public String join()
    {
        return " JOIN travar t ON (t.idTravar = ts.idTravar)\n"
                + "JOIN sertifikat s ON (s.idSertifikat = ts.idSertifikat) ";
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
            
            Sertifikat s = new Sertifikat(rs.getLong("idSertifikat"), rs.getString("naziv"), rs.getString("opis"));
            
            TrvS ts = new TrvS(t, s, rs.getObject("datumIzdavanja", LocalDate.class));
            
            lista.add(ts);
        }
        
        return lista;
    }

    @Override
    public String koloneZaInsert()
    {
        return " (idTravar, idSertifikat, datumIzdavanja) ";
    }

    @Override
    public String vrednostiZaInsert()
    {
        return " ?, ?, ? ";
    }

    @Override
    public String vrednostiZaUpdate()
    {
        return "";
    }

    @Override
    public String uslov()
    {
        return " idTravar = " + travar.getIdTravar();
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
    
    @Override
    public void prepareInsert(PreparedStatement ps) throws SQLException
    {
        ps.setLong(1, travar.getIdTravar());
        ps.setLong(2, sertifikat.getIdSertifikat());
        ps.setDate(3, Date.valueOf(datumIzdavanja));
    }

    @Override
    public void prepareUpdate(PreparedStatement ps) throws SQLException
    {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
    
    
    //=== Getters & Setters
    public Travar getTravar()
    {
        return travar;
    }

    public void setTravar(Travar travar)
    {
        this.travar = travar;
    }

    public Sertifikat getSertifikat()
    {
        return sertifikat;
    }

    public void setSertifikat(Sertifikat sertifikat)
    {
        this.sertifikat = sertifikat;
    }

    public LocalDate getDatumIzdavanja()
    {
        return datumIzdavanja;
    }

    public void setDatumIzdavanja(LocalDate datumIzdavanja)
    {
        this.datumIzdavanja = datumIzdavanja;
    }
    
}
