/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controllers.storage.LoadJson;

import java.nio.file.Files;
import java.nio.file.Paths;
import model.Location.Location;
import model.storage.ILocationStorage;
import model.storage.LocationStorage;
import org.json.JSONArray;
import org.json.JSONObject;

/**
 *
 * @author mariafernandaviloriazapata
 */
public class LocationLoad {
    public static void loadLocations() {
        try {
            String content = new String(Files.readAllBytes(Paths.get("locations.json")));
            JSONArray array = new JSONArray(content);
            for (int i = 0; i < array.length(); i++) {
                JSONObject obj = array.getJSONObject(i);

                String airportId = obj.getString("airportId");
                String airportName = obj.getString("airportName");
                String airportCity = obj.getString("airportCity");
                String airportCountry = obj.getString("airportCountry");
                double airportLatitude = obj.getDouble("airportLatitude");
                double airportLongitude = obj.getDouble("airportLongitude");
                ILocationStorage loc = LocationStorage.getInstance();
                loc.addLocation(new Location(airportId, airportName, airportCity, airportCountry, airportLatitude, airportLongitude));
            }

        } catch (Exception e) {
            System.err.println("Error al leer el archivo JSON: " + e.getMessage());
        }
    }

}
