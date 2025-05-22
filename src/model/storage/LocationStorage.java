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
public class LocationStorage {

    // Instancia Singleton
    private static LocationStorage instance;
    private ArrayList<Location> locations;

    private LocationStorage() {

        this.locations = new ArrayList<>();

    }

    public static LocationStorage getInstance() {
        if (instance == null) {
            instance = new LocationStorage();
        }

        return instance;
    }

    public ArrayList<Location> getLocations() {
        return locations;
    }

    public void addLocation(Location location) {
            System.out.println(location.toString());
            this.locations.add(location);
    }

    public Location getLocation(String id) {
        for (Location loc: this.locations){
            if(loc.getAirportId().equals(id)){
                return loc;
            }
        }
        return null;
    }

    
}
