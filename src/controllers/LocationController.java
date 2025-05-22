package controllers;

import controllers.utils.Response;
import controllers.utils.Status;
import model.Location;
import model.storage.LocationStorage;


/**
 *
 * @author edangulo
 */
public class LocationController {

    public static Response createLocation(String id, String name, String city, String country, String latitudeStr, String longitudeStr) {
        LocationStorage storage = LocationStorage.getInstance();

        // Validaciones de campos vacíos
        if (id == null || id.trim().isEmpty()) {
            return new Response("Airport ID must not be empty", Status.BAD_REQUEST);
        }
        if (name == null || name.trim().isEmpty()) {
            return new Response("Airport name must not be empty", Status.BAD_REQUEST);
        }
        if (city == null || city.trim().isEmpty()) {
            return new Response("Airport city must not be empty", Status.BAD_REQUEST);
        }
        if (country == null || country.trim().isEmpty()) {
            return new Response("Airport country must not be empty", Status.BAD_REQUEST);
        }
        if (latitudeStr == null || latitudeStr.trim().isEmpty()) {
            return new Response("Airport latitude must not be empty", Status.BAD_REQUEST);
        }
        if (longitudeStr == null || longitudeStr.trim().isEmpty()) {
            return new Response("Airport longitude must not be empty", Status.BAD_REQUEST);
        }

        // Validar existencia de ID duplicado
        if (storage.getLocation(id) != null) {
            return new Response("A location with that ID already exists", Status.BAD_REQUEST);
        }

        // Validación de latitud y longitud
        double latitude;
        double longitude;
        try {
            latitude = Double.parseDouble(latitudeStr);
            if (latitude < -90 || latitude > 90) {
                return new Response("Latitude must be between -90 and 90", Status.BAD_REQUEST);
            }
        } catch (NumberFormatException ex) {
            return new Response("Latitude must be numeric", Status.BAD_REQUEST);
        }

        try {
            longitude = Double.parseDouble(longitudeStr);
            if (longitude < -180 || longitude > 180) {
                return new Response("Longitude must be between -180 and 180", Status.BAD_REQUEST);
            }
        } catch (NumberFormatException ex) {
            return new Response("Longitude must be numeric", Status.BAD_REQUEST);
        }

        // Crear y almacenar el objeto Location
        Location location = new Location(id, name, city, country, latitude, longitude);
        storage.addLocation(location);

        return new Response("Location added successfully", Status.OK);
    }
}
