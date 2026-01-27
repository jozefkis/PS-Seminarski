/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package coordinator;

import domain.Travar;
import forms.ServerForm;
import java.util.ArrayList;
import java.util.List;
import thread.ClientThread;

/**
 *
 * @author SOFIJA
 */
public class ServerCoordinator
{

    private static ServerCoordinator instance;
    private final List<ClientThread> activeClients;
    private ServerForm serverForm;

    private ServerCoordinator()
    {
        this.activeClients = new ArrayList<>();
    }

    public static synchronized ServerCoordinator getInstance()
    {
        return (instance == null) ? instance = new ServerCoordinator() : instance;
    }

    public void setServerForm(ServerForm form)
    {
        this.serverForm = form;
    }

    public synchronized void addClient(ClientThread cth)
    {
        activeClients.add(cth);
    }

    public synchronized boolean isTravarAlreadyLogged(Travar travar)
    {
        for (ClientThread cth : activeClients)
        {
            if (cth.getLoggedTravar() != null && cth.getLoggedTravar().equals(travar))
            {
                return true;
            }
        }
        return false;
    }

    public synchronized void removeClient(ClientThread ct)
    {
        activeClients.remove(ct);
        notifyForm();
    }

    public synchronized List<Travar> getLoggedTravari()
    {
        List<Travar> logged = new ArrayList<>();

        for (ClientThread cth : activeClients)
        {
            if (cth.getLoggedTravar() != null)
            {
                logged.add(cth.getLoggedTravar());
            }
        }

        return logged;
    }

    public void notifyForm()
    {
        if (serverForm != null)
        {
            java.awt.EventQueue.invokeLater(() -> 
            {
                serverForm.refreshTable();
            });

        }

    }
}
