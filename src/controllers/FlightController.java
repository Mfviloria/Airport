/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controllers;

import controllers.utils.Response;
import controllers.utils.Status;
import java.time.LocalDateTime;
import model.Flight;
import model.Location;
import model.Plane;
import model.storage.Storage;

/**
 *
 * @author mariafernandaviloriazapata
 */
public class FlightController {
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
            storage.addFlight(flight);

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
            storage.addFlight(flight);

            return new Response("Flight successfully added (with scale)", Status.OK);

        } catch (NumberFormatException ex) {
            return new Response("Numeric value expected in time fields: " + ex.getMessage(), Status.BAD_REQUEST);
        } catch (Exception ex) {
            return new Response("Unexpected error: " + ex.getMessage(), Status.INTERNAL_SERVER_ERROR);
        }
    }
}
