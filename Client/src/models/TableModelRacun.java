/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package models;

import domain.Racun;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import javax.swing.table.AbstractTableModel;

/**
 *
 * @author Yo
 */
public class TableModelRacun extends AbstractTableModel
{
    private final String[] columnNames = {"ID", "Travar", "Kupac", "Datum", "Vreme", "Ukupan iznos"};
    private  List<Racun> racuni;

    public TableModelRacun()
    {
        racuni = new ArrayList<>();
    }

    public TableModelRacun(List<Racun> racuni)
    {
        this.racuni = racuni;
    }
    
    
    
    @Override
    public int getRowCount()
    {
        return racuni.size();
    }

    @Override
    public int getColumnCount()
    {
        return columnNames.length;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex)
    {
        Racun r = racuni.get(rowIndex);
        DateTimeFormatter customFormatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        
        switch(columnIndex)
        {
            case 0:
                return r.getIdRacun();
            case 1:
                return r.getTravar();
            case 2:
                return r.getKupac();
            case 3:
                return r.getDatum().toLocalDate().format(customFormatter);
            case 4:
                return r.getDatum().toLocalTime();
            case 5:
                return r.getUkupanIznos();
            default:
                return null;
        }
    }

    @Override
    public String getColumnName(int column)
    {
        return columnNames[column];
    }
    
    public Racun getRacun(int row)
    {
        return racuni.get(row);
    }

    public List<Racun> getRacuni()
    {
        return racuni;
    }

    public void setRacuni(List<Racun> racuni)
    {
        this.racuni = racuni;
        fireTableDataChanged();
    }
    
    
    
}
