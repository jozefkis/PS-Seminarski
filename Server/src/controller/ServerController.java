/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controller;

import domain.Travar;
import java.util.ArrayList;
import java.util.List;
import so.login.SOLogin;
import so.logout.SOLogout;
import so.travar.SOGetAllTravar;

/**
 *
 * @author Yo
 */
public class ServerController
{
    private static ServerController instance;
    private List<Travar> ulogovaniTravari = new ArrayList<>();

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
    
    public Travar login(Travar travar) throws Exception
    {
        SOLogin so = new SOLogin();
        so.templateExecute(travar);
        return so.getUlogovani();
    }
    
    public Travar logout(Travar travar) throws Exception
    {
        SOLogout so = new SOLogout();
        so.templateExecute(travar);
        return so.getZaOdjavu();
    }
    
    public List<Travar> getAllTravar() throws Exception
    {
        SOGetAllTravar so = new SOGetAllTravar();
        so.templateExecute(new Travar());
        return so.getAll();
    }

    public List<Travar> getUlogovaniTravari()
    {
        return ulogovaniTravari;
    }

    public void setUlogovaniTravari(List<Travar> ulogovaniTravari)
    {
        this.ulogovaniTravari = ulogovaniTravari;
    }
}
