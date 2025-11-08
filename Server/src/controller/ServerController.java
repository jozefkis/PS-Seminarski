/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controller;

import domain.Travar;
import java.util.ArrayList;
import java.util.List;
import so.login.SOLogin;

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

    public List<Travar> getUlogovaniTravari()
    {
        return ulogovaniTravari;
    }

    public void setUlogovaniTravari(List<Travar> ulogovaniTravari)
    {
        this.ulogovaniTravari = ulogovaniTravari;
    }
}
