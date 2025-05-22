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

        HashMap<String, Plane> planesMap = planes.getAllPlanesMap(); // Asegúrate que exista este método
        HashMap<String, Location> locationsMap = locations.getAllLocationsMap(); // Asegúrate que exista este método

        this.loadFlightsFromJson("data/flights.json", planesMap, locationsMap); // Ruta relativa o absoluta
    }

    public void loadFlightsFromJson(String filePath, HashMap<String, Plane> planesMap, HashMap<String, Location> locationsMap) {
        try {
            StringBuilder jsonText = new StringBuilder();
            BufferedReader br = new BufferedReader(new FileReader(filePath));
            String line;
            while ((line = br.readLine()) != null) {
                jsonText.append(line);
            }
            br.close();

            JSONArray flightArray = new JSONArray(jsonText.toString());
            DateTimeFormatter formatter = DateTimeFormatter.ISO_LOCAL_DATE_TIME;

            for (int i = 0; i < flightArray.length(); i++) {
                JSONObject data = flightArray.getJSONObject(i);

                String id = data.getString("id");
                Plane plane = planesMap.get(data.getString("plane"));
                Location departure = locationsMap.get(data.getString("departureLocation"));
                Location arrival = locationsMap.get(data.getString("arrivalLocation"));

                Location scale = data.isNull("scaleLocation") ? null : locationsMap.get(data.getString("scaleLocation"));

                LocalDateTime departureDate = LocalDateTime.parse(data.getString("departureDate"), formatter);

                int hoursArrival = data.getInt("hoursDurationArrival");
                int minutesArrival = data.getInt("minutesDurationArrival");
                int hoursScale = data.getInt("hoursDurationScale");
                int minutesScale = data.getInt("minutesDurationScale");

                Flight flight;
                if (scale != null) {
                    flight = new Flight(id, plane, departure, scale, arrival, departureDate,
                            hoursArrival, minutesArrival, hoursScale, minutesScale);
                } else {
                    flight = new Flight(id, plane, departure, arrival, departureDate,
                            hoursArrival, minutesArrival);
                }

                this.addFlight(flight);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void saveFlightsToJson(String filePath) {
        JSONArray flightArray = new JSONArray();
        DateTimeFormatter formatter = DateTimeFormatter.ISO_LOCAL_DATE_TIME;

        for (Flight flight : flights) {
            JSONObject data = new JSONObject();
            data.put("id", flight.getId());
            data.put("plane", flight.getPlane().getId());
            data.put("departureLocation", flight.getDepartureLocation().getAirportName());
            data.put("arrivalLocation", flight.getArrivalLocation().getAirportName());
            data.put("scaleLocation",flight.getScaleLocation() == null? JSONObject.NULL: flight.getScaleLocation().getAirportName());
            data.put("departureDate", flight.getDepartureDate().format(formatter));
            data.put("hoursDurationArrival", flight.getHoursDurationArrival());
            data.put("minutesDurationArrival", flight.getMinutesDurationArrival());
            data.put("hoursDurationScale", flight.getHoursDurationScale());
            data.put("minutesDurationScale", flight.getMinutesDurationScale());

            flightArray.put(data);
        }

        try (FileWriter writer = new FileWriter(filePath)) {
            writer.write(flightArray.toString(4)); // Pretty print con 4 espacios
        } catch (Exception e) {
            e.printStackTrace();
        }
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
            saveFlightsToJson("data/flights.json"); // Guardar automáticamente
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
