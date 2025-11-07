/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package domain;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Date;
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
        rs.close();
        
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
        return " " + travar.getIdTravar() + ", " + sertifikat.getIdSertifikat() + ", "
                + "'" + Date.valueOf(datumIzdavanja) + "' ";
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
