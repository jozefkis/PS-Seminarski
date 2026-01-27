/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package models;

import domain.Caj;
import domain.Racun;
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

    private final String[] columnNames =
    {
        "rb", "Čaj", "Cena", "Količina", "Iznos"
    };
    private final Racun racun;
    private int rb;

    public TableModelStavkeRacuna()
    {
        racun = new Racun();
        racun.setStavkeRacuna(new ArrayList<>());
        rb = 0;
    }

    public TableModelStavkeRacuna(Racun racun, List<StavkaRacuna> stavkeRacuna)
    {
        this.racun = racun;
        this.racun.setStavkeRacuna(stavkeRacuna);
        rb = stavkeRacuna.size();
    }

    public TableModelStavkeRacuna(Racun racun)
    {
        this.racun = racun;
    }

    @Override
    public int getRowCount()
    {
        return racun.getStavkeRacuna().size();
    }

    @Override
    public int getColumnCount()
    {
        return columnNames.length;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex)
    {
        StavkaRacuna sr = racun.getStavkeRacuna().get(rowIndex);

        switch (columnIndex)
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

    public Racun getRacun()
    {
        return racun;
    }

    public boolean addStavka(StavkaRacuna sr)
    {
        if (racun.getStavkeRacuna().contains(sr))
        {
            return false;
        }

        racun.addStavka(sr);
        fireTableDataChanged();

        return true;
    }

    public boolean removeStavka(StavkaRacuna sr)
    {
        if (racun.getStavkeRacuna().contains(sr))
        {
            racun.removeStavka(sr);

            fireTableDataChanged();
            return true;
        }

        return false;
    }

    public boolean deleteStavka(StavkaRacuna sr)
    {
        if (racun.getStavkeRacuna().contains(sr))
        {
            racun.deleteStavka(sr);

            fireTableDataChanged();
            return true;
        }

        return false;
    }

    public void clearStavke()
    {
        racun.getStavkeRacuna().clear();
        racun.setUkupanIznos(0);
        fireTableDataChanged();
    }

    @Override
    public boolean isCellEditable(int rowIndex, int columnIndex)
    {
        return columnIndex == 3;
    }

    public Class<?> getColumnClass(int columnIndex)
    {
        switch (columnIndex)
        {
            case 0:
                return Integer.class; // rb
            case 1:
                return Caj.class;
            case 2:
                return Double.class;  // cena
            case 3:
                return Integer.class; // količina
            case 4:
                return Double.class;  // iznos
            default:
                return Object.class;
        }
    }

    @Override
    public void setValueAt(Object aValue, int rowIndex, int columnIndex)
    {
        if (columnIndex != 3)
        {
            return;
        }

        StavkaRacuna sr = racun.getStavkeRacuna().get(rowIndex);

        try
        {
            int novaKolicina = Integer.parseInt(aValue.toString());

            // VALIDACIJA
            if (novaKolicina <= 0)
            {
                return;
            }

            racun.updateStavka(sr, novaKolicina);
            fireTableRowsUpdated(rowIndex, rowIndex);
        }
        catch (NumberFormatException e)
        {
        }
    }

    public StavkaRacuna getStavkaAt(int modelRow)
    {
        return racun.getStavkeRacuna().get(modelRow);
    }

}
