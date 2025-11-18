/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package models;

import controller.ClientController;
import domain.Kupac;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.table.AbstractTableModel;

/**
 *
 * @author SOFIJA
 */
public class TableModelKupac extends AbstractTableModel
{
    private final String[] columnNames = {"Ime", "Prezime", "Telefon", "Mesto"}; 
    private List<Kupac> kupci;

    public TableModelKupac()
    {
        try
        {
            kupci = ClientController.getInstance().getAllKupac();
        }
        catch (Exception ex)
        {
            System.getLogger(TableModelKupac.class.getName()).log(System.Logger.Level.ERROR, (String) null, ex);
        }
    }
    
    
    
    @Override
    public int getRowCount()
    {
        return kupci.size();
    }

    @Override
    public int getColumnCount()
    {
        return columnNames.length;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex)
    {
        Kupac k = kupci.get(rowIndex);
        
        switch(columnIndex)
        {
            case 0:
                return k.getIme();
            case 1:
                return k.getPrezime();
            case 2:
                return k.getTelefon();
            case 3:
                return k.getMesto();
            default:
                return null;
        }
    }

    @Override
    public String getColumnName(int column)
    {
        return columnNames[column];
    }

    public List<Kupac> getKupci()
    {
        return kupci;
    }

    public void setKupci(List<Kupac> kupci)
    {
        this.kupci = kupci;
        fireTableDataChanged();
    }
    
    public void refresh()
    {
        try
        {
            kupci = ClientController.getInstance().getAllKupac();
            fireTableDataChanged();
        }
        catch (Exception ex)
        {
            Logger.getLogger(TableModelKupac.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
