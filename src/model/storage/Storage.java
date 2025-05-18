/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model.storage;

import java.util.ArrayList;
import model.Passenger;

/**
 *
 * @author edangulo
 */
public class Storage {
    
    // Instancia Singleton
    private static Storage instance;
    
    // Atributos del Storage
    private ArrayList<Passenger> passengers;
    
    private Storage() {
        this.passengers = new ArrayList<>();
    }
    
    public static Storage getInstance() {
        if (instance == null) {
            instance = new Storage();
        }
        return instance;
    }
    
    public boolean addPerson(Passenger passenger) {
        for (Passenger p : this.passengers) {
            if (p.getId() == passenger.getId()) {
                return false;
            }
        }
        this.passengers.add(passenger);
        return true;
    }
    
    public Passenger  getPassenger(int id) {
        for (Passenger pass : this.passengers) {
            if (pass.getId() == id) {
                return pass;
            }
        }
        return null;
    }
    
    public boolean delPassenger(int id) {
        for (Passenger pass : this.passengers) {
            if (pass.getId() == id) {
                this.passengers.remove(pass);
                return true;
            }
        }
        return false;
    }
    
}
