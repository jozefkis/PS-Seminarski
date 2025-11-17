/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package models;

import domain.StavkaRacuna;
import java.util.ArrayList;
import java.util.List;
import javax.swing.table.AbstractTableModel;

/**
 *
 * @author Yo
 */
public class TableModelStavkeRacuna extends AbstractTableModel
{
    private final String[] columnNames = {"rb", "Čaj", "Cena", "Količina", "Iznos"};
    private List<StavkaRacuna> stavke;
    private int rb;

    public TableModelStavkeRacuna()
    {
        stavke = new ArrayList<>();
        rb = 0;
    }
    
    
    @Override
    public int getRowCount()
    {
        return stavke.size();
    }

    @Override
    public int getColumnCount()
    {
        return columnNames.length;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex)
    {
        StavkaRacuna sr = stavke.get(rowIndex);
        
        switch(columnIndex)
        {
            case 0:
                return sr.getRb();
            case 1:
                return sr.getCaj().getNaziv();
            case 2:
                return sr.getCaj().getCena();
            case 3:
                return sr.getKolicina();
            case 4:
                return sr.getIznos();
            default:
                return null;
        }
    }

    @Override
    public String getColumnName(int column)
    {
        return columnNames[column];
    }

    public List<StavkaRacuna> getStavke()
    {
        return stavke;
    }
    
    public boolean addStavka(StavkaRacuna sr)
    {
        if (stavke.contains(sr))
            return false;
        
        sr.setRb(++rb);
        stavke.add(sr);
        fireTableDataChanged();
        
        return true;
    }
    
    public boolean removeStavka(StavkaRacuna sr)
    {
        if (stavke.contains(sr))
        {
            int rbToRemove = sr.getRb();
            stavke.remove(sr);
            
            for (int i = rbToRemove - 1; i < stavke.size(); i++)
            {
                StavkaRacuna s = stavke.get(i);
                s.setRb(s.getRb() - 1);
            }
            --rb;
            
            fireTableDataChanged();
            return true;
        }
        
        return false;
    }
    
}
