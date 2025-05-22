/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model.storage;

import java.awt.List;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.ArrayList;
import java.util.HashMap;
import model.Flight;
import model.Location;
import model.Passenger;
import model.Plane;
import org.json.JSONArray;
import org.json.JSONObject;

/**
 *
 * @author edangulo
 */
public class LocationStorage {

    // Instancia Singleton
    private static LocationStorage instance;
    private ArrayList<Location> locations;
    private final String FILE_PATH = "locations.json"; // Ruta del archivo JSON

    private LocationStorage() {
        this.locations = new ArrayList<>();
        loadFromFile();  // Cargar locations al crear la instancia
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
        // Agrega y guarda en archivo
        this.locations.add(location);
        saveToFile();
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

    // Leer JSON desde archivo y cargar a la lista locations
    private void loadFromFile() {
        try {
            String content = new String(Files.readAllBytes(Paths.get(FILE_PATH)));
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

                this.locations.add(loc);
            }

        } catch (Exception e) {
            System.out.println("No se pudo cargar archivo JSON de locations o archivo no existe, se inicia vacío.");
            // Si no existe archivo o error, inicia lista vacía
            this.locations = new ArrayList<>();
        }
    }

    // Guardar lista locations en archivo JSON (sobrescribir)
    private void saveToFile() {
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

            Files.write(Paths.get(FILE_PATH), array.toString(4).getBytes(), StandardOpenOption.CREATE, StandardOpenOption.TRUNCATE_EXISTING);
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("Error al guardar locations en JSON.");
        }

    }

}
