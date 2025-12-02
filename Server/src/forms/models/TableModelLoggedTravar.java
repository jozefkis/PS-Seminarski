/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package forms.models;

import domain.Travar;
import java.util.ArrayList;
import java.util.List;
import javax.swing.table.AbstractTableModel;

/**
 *
 * @author SOFIJA
 */
public class TableModelLoggedTravar extends AbstractTableModel
{
    private final String[] columnNames = {"ID", "Ime", "Prezime", "Username"}; 
    private List<Travar> list;

    public TableModelLoggedTravar() 
    {
        this.list = new ArrayList<>();
    }
    
    public List<Travar> getList()
    {
        return list;
    }

    public void setList(List<Travar> list)
    {
        this.list = list;
        fireTableDataChanged();
    }
    
    
    @Override
    public int getRowCount()
    {
        return list.size();
    }
    
    @Override
    public int getColumnCount()
    {
        return columnNames.length;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex)
    {
        Travar t = list.get(rowIndex);
        
        switch (columnIndex)
        {
            case 0:
                return t.getIdTravar();
            case 1:
                return t.getIme();
            case 2:
                return t.getPrezime();
            case 3:
                return t.getUsername();
            default:
                throw new AssertionError();
        }
    }

    @Override
    public String getColumnName(int column)
    {
        return columnNames[column];
    }

   
    
}
