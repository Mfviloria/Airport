package model.storage;

import java.util.ArrayList;
import java.util.HashMap;
import model.Location.Location;
import model.observers.Observable;
import model.observers.Observer;


public class LocationStorage extends Storage {

    // Instancia Singleton
    private ArrayList<Observer> observers = new ArrayList<>();
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

    

    public Location getLocation(String id) {
        for (Location loc : this.locations) {
            if (loc.getAirportId().equals(id)) {
                return loc;
            }
        }
        return null;
    }

    public boolean addLocation(Location location){
        this.locations.add(location);
       this.notifyObsevers();
       return true;
    }



 
}
