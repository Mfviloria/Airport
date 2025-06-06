/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controllers;

import controllers.utils.Response;
import controllers.utils.Status;
import model.flight.Flight;
import model.storage.FlightStorage;
import model.storage.IFlightStorage;

/**
 *
 * @author mariafernandaviloriazapata
*/
public class DelayFlightController {
    public static Response delayFlight(String id, String hour, String minutes){
        //Validación de campos vacíos
        if (id.equals("ID")){
            return new Response("Id flight must not be empty.", Status.BAD_REQUEST);
        }else if(hour.equals("Hour")){
             return new Response("Hour must not be empty.", Status.BAD_REQUEST);
        }else if(minutes.equals("Minute")){
             return new Response("Minutes must not be empty.", Status.BAD_REQUEST);
        }
        
        if (hour.equals("0") && minutes.equals("0")){
            return new Response("Delay time must be greater than 00:00", Status.BAD_REQUEST);
        }
        
        IFlightStorage storage = FlightStorage.getInstance();
        Flight flight = storage.getFlight(id);
        flight.setDepartureDate(flight.getDepartureDate().plusHours(Long.parseLong(hour)).plusMinutes(Long.parseLong(minutes)));
        return new Response("Flight delayed succesfully.", Status.OK);
    }
}
