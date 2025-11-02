/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package test;

import com.formdev.flatlaf.FlatDarculaLaf;
import com.formdev.flatlaf.FlatLightLaf;
import forms.DBConfiguration;
import java.awt.Color;
import javax.swing.UIManager;

/**
 *
 * @author Yo
 */
public class Test
{
    public static void main(String[] args)
    {
        try 
        {
            UIManager.setLookAndFeel(new FlatDarculaLaf());
            UIManager.put("Button.background", new Color(0x3D6BFF));
        } 
        catch( Exception ex ) 
        {
            System.err.println( "Failed to initialize LaF" );
        }
        
        DBConfiguration dbc = new DBConfiguration(null, true);
        dbc.setVisible(true);
    }
}
