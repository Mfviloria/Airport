/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controllers;

import controllers.utils.Response;
import controllers.utils.Status;
import model.Flight;
import model.storage.FlightStorage;

/**
 *
 * @author mariafernandaviloriazapata
*/
public class DelayFlightController {
    public static Response delayFlight(String id, String hour, String minutes){
        if (id.equals("ID")){
            return new Response("Id flight must not be empty.", Status.BAD_REQUEST);
        }else if(hour.equals("Hour")){
             return new Response("Hour must not be empty.", Status.BAD_REQUEST);
        }else if(minutes.equals("Minute")){
             return new Response("Minutes must not be empty.", Status.BAD_REQUEST);
        }
        FlightStorage storage = FlightStorage.getInstance();
        Flight flight = storage.getFlight(id);
        flight.setDepartureDate(flight.getDepartureDate().plusHours(Long.parseLong(hour)).plusMinutes(Long.parseLong(minutes)));
        return new Response("Flight delayed succesfully.", Status.OK);
    }
}
