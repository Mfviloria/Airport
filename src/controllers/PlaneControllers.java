package controllers;

import controllers.utils.Response;
import controllers.utils.Status;
import java.time.LocalDateTime;
import model.Flight;
import model.Location;
import model.Plane;
import model.storage.Storage;

public class PlaneControllers {

    public static Response createPlaneFlight(String id, Plane plane, Location departure, Location arrival,
            LocalDateTime departureDate, int hoursArrival, int minutesArrival) {
        Storage storage = Storage.getInstance();
        try {
            // Validaciones básicas de campos
            if (id == null || id.trim().isEmpty()) {
                return new Response("Flight ID must not be empty", Status.BAD_REQUEST);
            }

            if (!id.matches("^[A-Z]{2}\\d{5}$")) {
                return new Response("Flight ID must follow the format: 2 uppercase letters followed by 5 digits (e.g., AB12345)", Status.BAD_REQUEST);
            }

            if (storage.getFlight(id) != null) {
                return new Response("A flight with this ID already exists", Status.BAD_REQUEST);
            }

            if (plane == null) {
                return new Response("Plane must not be null", Status.BAD_REQUEST);
            }

            if (departure == null || arrival == null) {
                return new Response("Both departure and arrival locations must be provided", Status.BAD_REQUEST);
            }

            if (departureDate == null) {
                return new Response("Departure date must not be null", Status.BAD_REQUEST);
            }

            if (hoursArrival < 0 || minutesArrival < 0 || (hoursArrival == 0 && minutesArrival == 0)) {
                return new Response("Arrival duration must be greater than 00:00", Status.BAD_REQUEST);
            }

            // Verificar que las ubicaciones existan en almacenamiento
            if (storage.getLocation(departure.getAirportId()) == null) {
                return new Response("Departure location does not exist in system", Status.BAD_REQUEST);
            }

            if (storage.getLocation(arrival.getAirportId()) == null) {
                return new Response("Arrival location does not exist in system", Status.BAD_REQUEST);
            }

            // Crear y guardar vuelo
            Flight flight = new Flight(id, plane, departure, arrival, departureDate, hoursArrival, minutesArrival);
            storage.addFlight(id, flight);

            return new Response("Flight successfully added (no scale)", Status.OK);

        } catch (NumberFormatException ex) {
            return new Response("Numeric value expected in time fields: " + ex.getMessage(), Status.BAD_REQUEST);
        } catch (Exception ex) {
            return new Response("Unexpected error: " + ex.getMessage(), Status.INTERNAL_SERVER_ERROR);
        }
    }

    public static Response createPlaneFlightWithScale(String id, Plane plane, Location departure, Location scale, Location arrival,
            LocalDateTime departureDate, int hoursArrival, int minutesArrival,
            int hoursScale, int minutesScale) {
        Storage storage = Storage.getInstance();
        try {
            // Validaciones básicas de campos
            if (id == null || id.trim().isEmpty()) {
                return new Response("Flight ID must not be empty", Status.BAD_REQUEST);
            }

            if (!id.matches("^[A-Z]{2}\\d{5}$")) {
                return new Response("Flight ID must follow the format: 2 uppercase letters followed by 5 digits (e.g., AB12345)", Status.BAD_REQUEST);
            }

            if (storage.getFlight(id) != null) {
                return new Response("A flight with this ID already exists", Status.BAD_REQUEST);
            }

            if (plane == null) {
                return new Response("Plane must not be null", Status.BAD_REQUEST);
            }

            if (departure == null || scale == null || arrival == null) {
                return new Response("Departure, scale, and arrival locations must be provided", Status.BAD_REQUEST);
            }

            if (departureDate == null) {
                return new Response("Departure date must not be null", Status.BAD_REQUEST);
            }

            if ((hoursArrival < 0 || minutesArrival < 0 || (hoursArrival == 0 && minutesArrival == 0))
                    || (hoursScale < 0 || minutesScale < 0 || (hoursScale == 0 && minutesScale == 0))) {
                return new Response("Both arrival and scale durations must be greater than 00:00", Status.BAD_REQUEST);
            }

            // Verificar existencia de ubicaciones
            if (storage.getLocation(departure.getAirportId()) == null) {
                return new Response("Departure location does not exist in system", Status.BAD_REQUEST);
            }

            if (storage.getLocation(scale.getAirportId()) == null) {
                return new Response("Scale location does not exist in system", Status.BAD_REQUEST);
            }

            if (storage.getLocation(arrival.getAirportId()) == null) {
                return new Response("Arrival location does not exist in system", Status.BAD_REQUEST);
            }

            // Crear y guardar vuelo con escala
            Flight flight = new Flight(id, plane, departure, scale, arrival, departureDate,
                    hoursArrival, minutesArrival, hoursScale, minutesScale);
            storage.addFlight(id, flight);

            return new Response("Flight successfully added (with scale)", Status.OK);

        } catch (NumberFormatException ex) {
            return new Response("Numeric value expected in time fields: " + ex.getMessage(), Status.BAD_REQUEST);
        } catch (Exception ex) {
            return new Response("Unexpected error: " + ex.getMessage(), Status.INTERNAL_SERVER_ERROR);
        }
    }

    public static Response createPlane(String id, String brand, String model, String maxCapacity, String airline) {
        Storage storage = Storage.getInstance();

         if (id.isEmpty() || brand.isEmpty() || model.isEmpty() || maxCapacity.isEmpty() || airline.isEmpty()) {
            return new Response("Por favor, completa todos los campos.", Status.BAD_REQUEST);
           
        }
            int maxCapacityInt;
        try {
             maxCapacityInt = Integer.parseInt(maxCapacity);
        } catch (NumberFormatException e) {
            return new Response("La capacidad máxima debe ser un número válido.", Status.BAD_REQUEST);
        }

        if (!id.matches("^[A-Z]{2}[0-9]{5}$")) {
            return new Response("Plane id must match the format XXYYYYY (2 letters + 5 digits)", Status.BAD_REQUEST);
        }
        if (storage.getPlane(id) != null) {
            return new Response("A plane with that id already exists", Status.BAD_REQUEST);
        }

        if (brand.isEmpty()) {
            return new Response("Brand must not be empty", Status.BAD_REQUEST);
        }
        if (model.isEmpty()) {
            return new Response("Model must not be empty", Status.BAD_REQUEST);
        }
        if (airline.isEmpty()) {
            return new Response("Airline must not be empty", Status.BAD_REQUEST);
        }

        if (maxCapacityInt <= 0) {
            return new Response("Max capacity must be positive", Status.BAD_REQUEST);
        }

        Plane newPlane = new Plane(id, brand, model, maxCapacityInt, airline);
        storage.addPlane(id, newPlane);
        return new Response("Plane added successfully", Status.OK);
    }

}
