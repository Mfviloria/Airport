/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controllers;

import controllers.utils.Response;
import controllers.utils.Status;
import model.Flight;
import model.Passenger;
import model.storage.FlightStorage;
import model.storage.PassengerStorage;

/**
 *
 * @author mariafernandaviloriazapata
 */
public class AddToFlightController {
    public static Response AddtoFlight(long idPassenger, String flightiD){
        if (flightiD.equals("Select Flight")){
            return new Response("Fligh id must not be empty.", Status.OK);
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

        passenger.addFlight(flight);
        flight.addPassenger(passenger);
        
        return new Response("You have been successfully added to this flight.", Status.OK);

    }
}
