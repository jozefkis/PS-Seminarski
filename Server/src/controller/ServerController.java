/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controller;

import coordinator.ServerCoordinator;
import domain.Caj;
import domain.Kupac;
import domain.Mesto;
import domain.Racun;
import domain.Travar;
import java.util.List;
import so.kupac.SOGetAllKupac;
import so.login.SOLogin;
import so.caj.SOGetAllCaj;
import so.caj.SOAddCaj;
import so.caj.SODeleteCaj;
import so.caj.SOFilterCaj;
import so.caj.SOUpdateCaj;
import so.kupac.SOAddKupac;
import so.kupac.SODeleteKupac;
import so.kupac.SOFilterKupac;
import so.kupac.SOUpdateKupac;
import so.logout.SOLogout;
import so.mesto.SOGetAllMesto;
import so.racun.SOAddRacun;
import so.racun.SOGetAllRacun;
import so.racun.SOUpdateRacun;
import so.travar.SOGetAllTravar;

/**
 *
 * @author Yo
 */
public class ServerController
{

    private static ServerController instance;

    private ServerController()
    {
    }

    public static ServerController getInstance()
    {
        if (instance == null)
        {
            instance = new ServerController();
        }
        return instance;
    }

    //==== Travar operations
    public Travar login(Travar travar) throws Exception
    {
        SOLogin soLogin = new SOLogin();
        soLogin.templateExecute(travar);
        Travar ulogovani = soLogin.getUlogovani();

        if (ulogovani == null)
        {
            throw new Exception("Korisničko ime ili lozinka su pogrešni!");
        }

        if (ServerCoordinator.getInstance().isTravarAlreadyLogged(ulogovani))
        {
            throw new Exception("Travar je već ulogovan!");
        }

        return ulogovani;
    }

    public Travar logout(Travar travar) throws Exception
    {
        SOLogout so = new SOLogout();
        so.templateExecute(travar);
        return so.getTravarToLogOut();
    }

    public List<Travar> getAllTravar() throws Exception
    {
        SOGetAllTravar so = new SOGetAllTravar();
        so.templateExecute(new Travar());
        return so.getAll();
    }
    
    
    
    
    //==== Kupac operations ====
    public List<Kupac> getAllKupac() throws Exception
    {
        SOGetAllKupac so = new SOGetAllKupac();
        so.templateExecute(new Kupac());
        return so.getAll();
    }

    public List<Kupac> filterKupac(Kupac kupac) throws Exception
    {
        SOFilterKupac so = new SOFilterKupac();
        so.templateExecute(kupac);
        return so.getFiltered();
    }

    public Kupac addKupac(Kupac kupac) throws Exception
    {
        SOAddKupac so = new SOAddKupac();
        so.templateExecute(kupac);
        return so.getKupac();
    }

    public void updateKupac(Kupac kupac) throws Exception
    {
        SOUpdateKupac so = new SOUpdateKupac();
        so.templateExecute(kupac);
    }

    public void deleteKupac(Kupac kupac) throws Exception
    {
        SODeleteKupac so = new SODeleteKupac();
        so.templateExecute(kupac);
    }
    

    
    
    //==== Caj operations ==== 
    public List<Caj> getAllCaj() throws Exception
    {
        SOGetAllCaj so = new SOGetAllCaj();
        so.templateExecute(new Caj());
        return so.getAll();
    }
    
    public Caj addCaj(Caj caj) throws Exception
    {
        SOAddCaj so = new SOAddCaj();
        so.templateExecute(caj);
        return so.getCaj();
    }
    
    public List<Caj> filterCaj(Caj caj) throws Exception
    {
        SOFilterCaj so = new SOFilterCaj();
        so.templateExecute(caj);
        return so.getFiltered();
    }
    
    public void deleteCaj(Caj caj) throws Exception
    {
        SODeleteCaj so = new SODeleteCaj();
        so.templateExecute(caj);
    }

    public void updateCaj(Caj caj) throws Exception
    {
        SOUpdateCaj so = new SOUpdateCaj();
        so.templateExecute(caj);
    }
    
    
    
    //==== Mesto operations ====
    public List<Mesto> getAllMesto() throws Exception
    {
        SOGetAllMesto so = new SOGetAllMesto();
        so.templateExecute(new Mesto());
        return so.getAll();
    }
    
    
    
    //==== Racun operations ====
    public Racun addRacun(Racun racun) throws Exception
    {
        SOAddRacun so = new SOAddRacun();
        so.templateExecute(racun);
        return so.getRacun();
    }

    public List<Racun> getAllRacun(Racun racun) throws Exception
    {
        SOGetAllRacun so = new SOGetAllRacun();
        so.templateExecute(racun);
        return so.getAll();
    }
    
    public void updateRacun(Racun racun) throws Exception
    {
        SOUpdateRacun so = new SOUpdateRacun();
        so.templateExecute(racun);
    }
}
