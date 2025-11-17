/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package domain;

import java.io.Serializable;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

/**
 *
 * @author Yo
 */
public interface AbstractDomainObject extends Serializable
{
    public String nazivTabele();
    public String alijas();
    public String join();
    public List<AbstractDomainObject> vratiListu(ResultSet rs) throws SQLException;
    public String koloneZaInsert();
    public String vrednostiZaInsert();
    public String vrednostiZaUpdate();
    public String uslov();
    public String uslovZaSelect();
    public String uslovZaFilter();
}
