package model.storage;


import java.util.List;
import model.Location.Location;

public interface ILocationStorage {
    boolean addLocation(Location location);
    Location getLocation(String id);
    List<Location> getLocations();
}
