/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model.storage;

import java.util.ArrayList;
import model.Flight;

/**
 *
 * @author edangulo
 */
public class FlightStorage implements IFlightStorage{

    // Instancia Singleton
    private static FlightStorage instance;
    private ArrayList< Flight> flights;

    private FlightStorage() {
        this.flights = new ArrayList<>();
    }


    public static FlightStorage getInstance() {
        if (instance == null) {
            instance = new FlightStorage();
        }

        return instance;
    }

    public boolean addFlight(Flight flight) {
        if (this.getFlight(flight.getId()) != null) {
            return false;
        } else {
            this.flights.add(flight);
        }
        return true;
    }

    public Flight getFlight(String id) {
        for (Flight flight : this.flights) {
            if (flight.getId().equals(id)) {
                return flight;
            }
        }
        return null;

    }
    @Override
    public ArrayList<Flight> getFlights() {
            return flights;
    }

}
