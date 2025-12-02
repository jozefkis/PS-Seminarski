/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package models;

import controller.ClientController;
import domain.Caj;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.table.AbstractTableModel;

/**
 *
 * @author Yo
 */
public class TableModelCaj extends AbstractTableModel
{

    private final String[] columnNames = {"Naziv", "Cena"}; 
    private List<Caj> cajevi;

    public TableModelCaj()
    {
        try
        {
            cajevi = ClientController.getInstance().getAllCaj();
        }
        catch (Exception ex)
        {
            System.getLogger(TableModelKupac.class.getName()).log(System.Logger.Level.ERROR, (String) null, ex);
        }
    }
    
    
    
    @Override
    public int getRowCount()
    {
        return cajevi.size();
    }

    @Override
    public int getColumnCount()
    {
        return columnNames.length;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex)
    {
        Caj c = cajevi.get(rowIndex);
        
        switch(columnIndex)
        {
            case 0:
                return c.getNaziv();
            case 1:
                return c.getCena();
            default:
                return null;
        }
    }

    @Override
    public String getColumnName(int column)
    {
        return columnNames[column];
    }

    public List<Caj> getCajevi()
    {
        return cajevi;
    }

    public void setCajevi(List<Caj> cajevi)
    {
        this.cajevi = cajevi;
        fireTableDataChanged();
    }
    
    public void refresh()
    {
        try
        {
            cajevi = ClientController.getInstance().getAllCaj();
            fireTableDataChanged();
        }
        catch (Exception ex)
        {
            Logger.getLogger(TableModelCaj.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
}
