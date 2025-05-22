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
        loadFromJson("C:\\Users\\samuel\\Documents\\GitHub\\Airport\\json\\locations.json");
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

    // ✅ Carga desde archivo JSON con path recibido por parámetro
    public void loadFromJson(String filename) {
        try {
            String content = new String(Files.readAllBytes(Paths.get(filename)));
            JSONArray array = new JSONArray(content);

            for (int i = 0; i < array.length(); i++) {
                JSONObject obj = array.getJSONObject(i);

                Location loc = new Location(
                        obj.getString("airportId"),
                        obj.getString("airportName"),
                        obj.getString("airportCity"),
                        obj.getString("airportCountry"),
                        obj.getDouble("airportLatitude"),
                        obj.getDouble("airportLongitude")
                );

                this.addLocation(loc);
            }

        } catch (Exception e) {
            System.err.println("Error al cargar archivo JSON de locations: " + e.getMessage());
            this.locations = new ArrayList<>();
        }
    }

    // ✅ Guardar lista de locations a un archivo JSON
    public void saveToJson(String filename) {
        try {
            JSONArray array = new JSONArray();

            for (Location loc : this.locations) {
                JSONObject obj = new JSONObject();
                obj.put("airportId", loc.getAirportId());
                obj.put("airportName", loc.getAirportName());
                obj.put("airportCity", loc.getAirportCity());
                obj.put("airportCountry", loc.getAirportCountry());
                obj.put("airportLatitude", loc.getAirportLatitude());
                obj.put("airportLongitude", loc.getAirportLongitude());
                array.put(obj);
            }

            Files.write(Paths.get(filename), array.toString(4).getBytes(), StandardOpenOption.CREATE, StandardOpenOption.TRUNCATE_EXISTING);
        } catch (Exception e) {
            System.err.println("Error al guardar locations en JSON: " + e.getMessage());
        }
    }
}
