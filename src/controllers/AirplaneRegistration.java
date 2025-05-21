package controllers;

import controllers.utils.Response;
import controllers.utils.Status;
import model.Location;
import model.storage.Storage;

public class AirportController {

    public static Response createAirport(String id, String name, String city, String country, long latitude, long longitude) {
        Storage storage = Storage.getInstance();

        // Validar campos vacíos
        if (id == null || id.trim().isEmpty()) {
            return new Response("Airport ID must not be empty", Status.BAD_REQUEST);
        } else if (name == null || name.trim().isEmpty()) {
            return new Response("Airport name must not be empty", Status.BAD_REQUEST);
        } else if (city == null || city.trim().isEmpty()) {
            return new Response("City must not be empty", Status.BAD_REQUEST);
        } else if (country == null || country.trim().isEmpty()) {
            return new Response("Country must not be empty", Status.BAD_REQUEST);
        }

        id = id.trim();

        // Validar formato del ID (3 letras mayúsculas)
        if (!id.matches("^[A-Z]{3}$")) {
            return new Response("Airport ID must be exactly 3 uppercase letters", Status.BAD_REQUEST);
        }

        // Verificar unicidad del ID
        if (storage.getLocation(id) != null) {
            return new Response("An airport with this ID already exists", Status.BAD_REQUEST);
        }

        // Validar latitud
        if (latitude < -90 || latitude > 90) {
            return new Response("Latitude must be between -90 and 90", Status.BAD_REQUEST);
        }

        // Validar longitud
        if (longitude < -180 || longitude > 180) {
            return new Response("Longitude must be between -180 and 180", Status.BAD_REQUEST);
        }

        // Validar decimales (hasta 4 cifras decimales)
        if (!String.valueOf(latitude).matches("^-?\\d+(\\.\\d{1,4})?$")) {
            return new Response("Latitude must have at most 4 decimal places", Status.BAD_REQUEST);
        }

        if (!String.valueOf(longitude).matches("^-?\\d+(\\.\\d{1,4})?$")) {
            return new Response("Longitude must have at most 4 decimal places", Status.BAD_REQUEST);
        }

        // Crear aeropuerto y agregar al storage
        Location airport = new Location(id, name, city, country, latitude, longitude);
        storage.addLocation(airport);

        return new Response("Airport added successfully", Status.OK);
    }
}
