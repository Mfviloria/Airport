/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model.storage;

import java.util.ArrayList;
import model.Passenger;
import model.observers.Observable;
import model.observers.Observer;


/**
 *
 * @author edangulo
 */
public class PassengerStorage  extends Storage{
        
    private ArrayList<Observer> observers = new ArrayList<>();

    // Instancia Singleton
    private static PassengerStorage instance;

    private ArrayList<Passenger> passengers;

    private PassengerStorage() {
        this.passengers = new ArrayList<>();
    }

    public static PassengerStorage getInstance() {
        if (instance == null) {
            instance = new PassengerStorage();
        }

        return instance;
    }

    public boolean addPassenger(Passenger passenger) {
        for (Passenger p : this.passengers) {
            if (p.getId() == passenger.getId()) {
                return false;
            }
        }
        this.passengers.add(passenger);
        this.notifyObsevers();
        return true;
    }

    public Passenger getPassenger(long id) {
        for (Passenger pass : this.passengers) {
            if (pass.getId() == id) {
                return pass;
            }
        }
        return null;
    }

    public boolean delPassenger(long id) {
        for (Passenger pass : this.passengers) {
            if (pass.getId() == id) {
                this.passengers.remove(pass);
                return true;
            }
        }
        return false;
    }

    public ArrayList<Passenger> getPassengers() {
        return passengers;
    }

 
}
