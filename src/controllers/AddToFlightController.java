/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controllers;

import controllers.utils.Response;
import controllers.utils.Status;
import model.flight.Flight;
import model.Passenger.Passenger;
import model.storage.FlightStorage;
import model.storage.PassengerStorage;

/**
 *
 * @author mariafernandaviloriazapata
 */
public class AddToFlightController {
    public static Response AddtoFlight(long idPassenger, String flightiD){
        if (flightiD.equals("Select Flight")){
            return new Response("Fligh id must not be empty.", Status.BAD_REQUEST);
        }
        
        PassengerStorage storage = PassengerStorage.getInstance();
        FlightStorage storagef = FlightStorage.getInstance();
        Passenger passenger = null;
        for (Passenger p : storage.getPassengers()) {
            if (p.getId() == idPassenger) {
                passenger = p;
            }
        }
        Flight flight = null;
        for (Flight f : storagef.getFlights()) {
            if (flightiD.equals(f.getId())) {
                flight = f;
            }
        }
        if (passenger == null) {
            return new Response("Passenger not found.", Status.NOT_FOUND);
        }
        if (flight == null) {
            return new Response("Flight not found.", Status.NOT_FOUND);
        }
        
        flight.addPassenger(passenger);
        passenger.addFlight(flight);
        
        
        return new Response("You have been successfully added to this flight.", Status.OK);

    }
}
