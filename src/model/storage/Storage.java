/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model.storage;

import java.awt.List;
import java.util.ArrayList;
import java.util.HashMap;
import model.Flight;
import model.Location;
import model.Passenger;
import model.Plane;

/**
 *
 * @author edangulo
 */
public class Storage {

    // Instancia Singleton
    private static Storage instance;

    private ArrayList<Passenger> passengers;
    private ArrayList< Plane> planes;
    private ArrayList<Location> locations;
    private ArrayList< Flight> flights;

    private Storage() {
        this.passengers = new ArrayList<>();
        this.planes = new ArrayList<>();
        this.locations = new ArrayList<>();
        this.flights = new ArrayList<>();

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


    public ArrayList<Plane> getPlanes() {
        return new ArrayList<>(planes.values());
    }    
    public ArrayList<Location> getLocations() {
        return new ArrayList<>(locations.values());
    }

    public boolean addPlane(Plane plane) {
        if (this.getPlane(plane.getId()) != null){
            return false;
        } else{
            this.planes.add(plane);
        }
        return true;
    }

    public Plane getPlane(String id) {
        for (Plane plane : this.planes){
            if (plane.getId().equals(id)){
                return plane;
            }
        }
        return null;
    }

    public boolean addLocation(Location location) {
        if (this.getLocation(location.getAirportId() )!= null){
            return false;
        }else{
            this.locations.add(location);
        }
        return true;
    }

    public Location getLocation(String id) {
        for (Location loc: this.locations){
            if(loc.getAirportId().equals(id)){
                return loc;
            }
        }
        return null;
    }

    public boolean addFlight(Flight flight) {
        if (this.getLocation(flight.getId() )!= null){
            return false;
        }else{
            this.flights.add(flight);
        }
        return true;
    }

    public Flight getFlight(String id) {
        for (Flight flight: this.flights){
            if(flight.getId().equals(id)){
                return flight;
            }
        }
        return null;

    }

    public ArrayList<Flight> getAllFlights() {
        return flights;
    }

}
