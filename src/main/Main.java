/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package main;

import com.formdev.flatlaf.FlatDarkLaf;
import javax.swing.UIManager;
import model.storage.LoadJson;
import view.AirportFrame;

/**
 *
 * @author mariafernandaviloriazapata
 */
public class Main {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {

        LoadJson.loadFlight();
        LoadJson.loadLocations();
        LoadJson.loadPassenger();
        LoadJson.loadPlane();
        
        System.setProperty("flatlaf.useNativeLibrary", "false");

        try {
            UIManager.setLookAndFeel(new FlatDarkLaf());
        } catch (Exception ex) {
            System.err.println("Failed to initialize LaF");
        }
        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new AirportFrame().setVisible(true);
            }
        });
    }
    
}
