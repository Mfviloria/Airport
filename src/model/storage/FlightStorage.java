/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model.storage;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.HashMap;
import model.Flight;
import model.Location;
import model.Plane;
import org.json.JSONArray;
import org.json.JSONObject;

/**
 *
 * @author edangulo
 */
public class FlightStorage {

    // Instancia Singleton
    private static FlightStorage instance;
    private ArrayList< Flight> flights;

    private FlightStorage() {
        this.flights = new ArrayList<>();

        // Cargar vuelos automáticamente al crear la instancia
        PlaneStorage planes = PlaneStorage.getInstance();
        LocationStorage locations = LocationStorage.getInstance();

   
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

    public ArrayList<Flight> getAllFlights() {
        return flights;
    }

}
