package model.storage;

import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.ArrayList;
import java.util.HashMap;
import model.Location;
import org.json.JSONArray;
import org.json.JSONObject;

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
        this.locations.add(location);
    }

    public Location getLocation(String id) {
        for (Location loc : this.locations) {
            if (loc.getAirportId().equals(id)) {
                return loc;
            }
        }
        return null;
    }

    public HashMap<String, Location> getAllLocationsMap() {
        HashMap<String, Location> map = new HashMap<>();
        for (Location loc : this.locations) {
            map.put(loc.getAirportId(), loc);
        }
        return map;
    }

 
}
