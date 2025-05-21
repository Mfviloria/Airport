/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model.storage;

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
    private HashMap<String, Plane> planes;
    private HashMap<String, Location> locations;
    private HashMap<String, Flight> flights;

    private Storage() {
        this.passengers = new ArrayList<>();
        this.planes = new HashMap<>();
        this.locations = new HashMap<>();
        this.flights = new HashMap<>();

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

    public boolean addPlane(String id, Plane plane) {
        if (planes.containsKey(id)) {
            return false;
        }
        planes.put(id, plane);
        return true;
    }

    public Plane getPlane(String id) {
        return planes.get(id);
    }

    public boolean addLocation(Location location) {
        if (locations.containsKey(location.getAirportId())) {
            return false;
        }
        locations.put(location.getAirportId(), location);
        return true;
    }

    public Location getLocation(String id) {
        return locations.get(id);
    }

    public boolean addFlight(String id, Flight flight) {
        if (flights.containsKey(id)) {
            return false;
        }
        flights.put(id, flight);
        return true;
    }

    public Flight getFlight(String id) {
        return flights.get(id);
    }

    public ArrayList<Flight> getAllFlights() {
        return new ArrayList<>(flights.values());
    }

}
