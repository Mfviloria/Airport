/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controllers;

import controllers.utils.Response;
import controllers.utils.Status;
import java.time.LocalDateTime;
import model.Location;
import model.Plane;
import model.storage.Storage;

/**
 *
 * @author samuel
 */
public class PlaneControllers {

    public static Response createPlane(String id, Plane plane, Location departureLocation, Location arrivalLocation, LocalDateTime departureDate, int hoursDurationArrival, int minutesDurationArrival) {
        Storage storage = Storage.getInstance();

        try {
            if (id == null || id.trim().isEmpty()) {
                return new Response("Plane ID must not be empty", Status.BAD_REQUEST);
            }

            if (!id.matches("^[A-Z]{2}\\d{5}$")) {
                return new Response("Plane ID must follow the format XXYYYYY", Status.BAD_REQUEST);
            }

            if (storage.getPlane(id) != null) {
                return new Response("A plane with that ID already exists", Status.BAD_REQUEST);
            }

            if (plane == null) {
                return new Response("Plane object must not be null", Status.BAD_REQUEST);
            }

            if (departureLocation == null || arrivalLocation == null) {
                return new Response("Departure and arrival locations must not be null", Status.BAD_REQUEST);
            }

            if (departureDate == null) {
                return new Response("Departure date must not be null", Status.BAD_REQUEST);
            }

            if (hoursDurationArrival < 0 || minutesDurationArrival < 0 || (hoursDurationArrival == 0 && minutesDurationArrival == 0)) {
                return new Response("Duration must be greater than 00:00", Status.BAD_REQUEST);
            }

            if (storage.getLocation(departureLocation.getAirportId()) == null) {
                return new Response("Departure location must exist", Status.BAD_REQUEST);
            }

            if (storage.getLocation(arrivalLocation.getAirportId()) == null) {
                return new Response("Arrival location must exist", Status.BAD_REQUEST);
            }

            storage.addPlane(id, plane);
            return new Response("Plane added successfully", Status.OK);

        } catch (Exception ex) {
            return new Response("Unexpected error: " + ex.getMessage(), Status.INTERNAL_SERVER_ERROR);
        }
    }

}
