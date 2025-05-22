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
    public static Response createPlaneFlight(String id, String  plane, String departure, String  arrival, String scaleLocation, String year, String month, String days, String hours, String minutes, String hoursDurationArrival, String minutesDurationArrival, String hoursDurationScale, String minutesDurationScale) {
        Storage storage = Storage.getInstance();
        try {
            // Validaciones básicas de campos
            if (id.trim().isEmpty()) {
                return new Response("Flight ID must not be empty", Status.BAD_REQUEST);
            } else if (plane.equals("Plane")){
                return new Response("Plane must not be empty", Status.BAD_REQUEST);
            } else if (departure.equals("Location")){
                return new Response("Departure Location must not be empty", Status.BAD_REQUEST);
            } else if (arrival.equals("Location")){
                return new Response("Arrival Location must not be empty", Status.BAD_REQUEST);
            } else if (scaleLocation.equals("Location")){
                return new Response("Scale Location must not be empty", Status.BAD_REQUEST);
            } else if (year.isEmpty() || month.equals("Month") || days.equals("Day") || hours.equals("Hour") || minutes.equals("Minute")){
                return new Response("Departure date must not be empty", Status.BAD_REQUEST);
            } else if (hoursDurationArrival.equals("Hour") || minutesDurationArrival.equals("Minute")){
                return new Response("Arrival duration must not be empty", Status.BAD_REQUEST);
            } else if (hoursDurationScale.equals("Hour") || minutesDurationScale.equals("Minute")){
                return new Response("Scale duration must not be empty", Status.BAD_REQUEST);
            } 

            if (!id.matches("^[A-Z]{2}\\d{5}$")) {
                return new Response("Flight ID must follow the format: 2 uppercase letters followed by 5 digits (e.g., AB12345)", Status.BAD_REQUEST);
            }

            if (storage.getFlight(id) != null) {
                return new Response("A flight with this ID already exists", Status.BAD_REQUEST);
            }
            
            int hoursArrival = Integer.parseInt(hoursDurationArrival);
            int minutesArrival = Integer.parseInt(minutesDurationArrival);
            int hoursScale = Integer.parseInt(hoursDurationScale);
            int minutesScale = Integer.parseInt(minutesDurationScale);
            

            if (hoursArrival < 0 || minutesArrival < 0 || (hoursArrival == 0 && minutesArrival == 0)) {
                return new Response("Arrival duration must be greater than 00:00", Status.BAD_REQUEST);
            }
            if (hoursScale < 0 || minutesScale< 0 || (hoursScale== 0 && minutesScale == 0)) {
                return new Response("Scale duration must be greater than 00:00", Status.BAD_REQUEST);
            }
            LocalDateTime departureDate = LocalDateTime.of(Integer.parseInt(year), Integer.parseInt(month), Integer.parseInt(days), Integer.parseInt(hours), Integer.parseInt(minutes));
            // Crear y guardar vuelo
            Flight flight = new Flight(id, storage.getPlane(plane), storage.getLocation(departure), storage.getLocation(arrival),  storage.getLocation(arrival), departureDate, hoursArrival, minutesArrival, hoursScale, minutesScale);
            storage.addFlight(flight);
            System.out.println(flight);
            
            return new Response("Flight successfully added (With scale)", Status.OK);

        } catch (NumberFormatException ex) {
            return new Response("Numeric value expected in time fields: " + ex.getMessage(), Status.BAD_REQUEST);
        } catch (Exception ex) {
            return new Response("Unexpected error: " + ex.getMessage(), Status.INTERNAL_SERVER_ERROR);
        }
    }

    public static Response createPlaneFlight(String id, String plane, String departure, String arrival,
            String year, String month, String days, String hours, String minutes, String hoursArrival, String minutesArrival) {
        Storage storage = Storage.getInstance();
        try {
            // Validaciones básicas de campos
            if (id.trim().isEmpty()) {
                return new Response("Flight ID must not be empty", Status.BAD_REQUEST);
            } else if (plane.trim().equals("Plane")){
                return new Response("Plane must not be empty", Status.BAD_REQUEST);
            } else if (departure.trim().equals("Location")){
                return new Response("Departure Location must not be empty", Status.BAD_REQUEST);
            } else if (arrival.trim().equals("Location")){
                return new Response("Arrival Location must not be empty", Status.BAD_REQUEST);
            } else if (year.isEmpty() || month.equals("Month") || days.equals("Day") || hours.equals("Hour") || minutes.equals("Minute")){
                return new Response("Departure date must not be empty", Status.BAD_REQUEST);
            } else if (hoursArrival.equals("Hour") || minutesArrival.equals("Minute")){
                return new Response("Arrival duration must not be empty", Status.BAD_REQUEST);
            } 
            
            if (!id.matches("^[A-Z]{2}\\d{5}$")) {
                return new Response("Flight ID must follow the format: 2 uppercase letters followed by 5 digits (e.g., AB12345)", Status.BAD_REQUEST);
            }

            if (storage.getFlight(id) != null) {
                return new Response("A flight with this ID already exists", Status.BAD_REQUEST);
            }
           
            int hoursa = Integer.parseInt(hoursArrival);
            int minutesa = Integer.parseInt(minutesArrival);

            if (hoursa < 0 || minutesa < 0 || (hoursa == 0 && minutesa == 0)) {
                return new Response("Arrival duration must be greater than 00:00", Status.BAD_REQUEST);
            }
            
            LocalDateTime departureDate = LocalDateTime.of(Integer.parseInt(year), Integer.parseInt(month), Integer.parseInt(days), Integer.parseInt(hours), Integer.parseInt(minutes));
            // Crear y guardar vuelo
            Flight flight = new Flight(id, storage.getPlane(plane), storage.getLocation(departure), storage.getLocation(arrival), departureDate,
                    hoursa, minutesa);
            storage.addFlight(flight);

            return new Response("Flight successfully added (No scale)", Status.OK);

        } catch (NumberFormatException ex) {
            return new Response("Numeric value expected in time fields: " + ex.getMessage(), Status.BAD_REQUEST);
        } catch (Exception ex) {
            return new Response("Unexpected error: " + ex.getMessage(), Status.INTERNAL_SERVER_ERROR);
        }
    }
}
