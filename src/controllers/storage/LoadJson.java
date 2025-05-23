/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controllers.storage;

import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import model.Flight;
import model.Location;
import model.Passenger;
import model.Plane;
import model.storage.FlightStorage;
import model.storage.LocationStorage;
import model.storage.PassengerStorage;
import model.storage.PlaneStorage;
import org.json.JSONArray;
import org.json.JSONObject;

/**
 *
 * @author mariafernandaviloriazapata
 */
public class LoadJson {

    public static void loadLocations() {
        try {
            String content = new String(Files.readAllBytes(Paths.get("locations.json")));
            JSONArray array = new JSONArray(content);
            for (int i = 0; i < array.length(); i++) {
                JSONObject obj = array.getJSONObject(i);

                String airportId = obj.getString("airportId");
                String airportName = obj.getString("airportName");
                String airportCity = obj.getString("airportCity");
                String airportCountry = obj.getString("airportCountry");
                double airportLatitude = obj.getDouble("airportLatitude");
                double airportLongitude = obj.getDouble("airportLongitude");
                LocationStorage loc = LocationStorage.getInstance();
                loc.addLocation(new Location(airportId, airportName, airportCity, airportCountry, airportLatitude, airportLongitude));
            }

        } catch (Exception e) {
            System.err.println("Error al leer el archivo JSON: " + e.getMessage());
        }
    }

    public static void loadFlight() {
        try {
            String content = new String(Files.readAllBytes(Paths.get("flights.json")));
            JSONArray array = new JSONArray(content);
            for (int i = 0; i < array.length(); i++) {
                JSONObject obj = array.getJSONObject(i);

                String id = obj.getString("id");
                String plane = obj.getString("plane");
                String departureLocation = obj.getString("departureLocation");
                String arrivalLocation = obj.getString("arrivalLocation");

                String scaleLocation = obj.isNull("scaleLocation") ? null : obj.getString("scaleLocation");
                LocalDateTime departureDate = LocalDateTime.parse(obj.getString("departureDate"));
                int hoursDurationArrival = obj.getInt("hoursDurationArrival");
                int minutesDurationArrival = obj.getInt("minutesDurationArrival");
                int hoursDurationScale = obj.getInt("hoursDurationScale");
                int minutesDurationScale = obj.getInt("minutesDurationScale");

                FlightStorage flight = FlightStorage.getInstance();
                PlaneStorage pla = PlaneStorage.getInstance();
                LocationStorage loc = LocationStorage.getInstance();
                flight.addFlight(new Flight(id, pla.getPlane(plane), loc.getLocation(departureLocation), loc.getLocation(scaleLocation), loc.getLocation(arrivalLocation), departureDate, hoursDurationArrival, minutesDurationArrival, hoursDurationScale, minutesDurationScale));
            }

        } catch (Exception e) {
            System.err.println("Error al leer el archivo JSON: " + e.getMessage());
        }
    }

    public static void loadPlane() {
        try {
            String content = new String(Files.readAllBytes(Paths.get("planes.json")));
            JSONArray array = new JSONArray(content);
            for (int i = 0; i < array.length(); i++) {
                JSONObject obj = array.getJSONObject(i);

                String id = obj.getString("id");
                String brand = obj.getString("brand");
                String model = obj.getString("model");
                int maxCapacity = obj.getInt("maxCapacity");
                String airline = obj.getString("airline");
                PlaneStorage plane = PlaneStorage.getInstance();
                plane.addPlane(new Plane(id, brand, model, maxCapacity, airline));
            }

        } catch (Exception e) {
            System.err.println("Error al leer el archivo JSON: " + e.getMessage());
        }
    }

    public static void loadPassenger() {
        try {
            String content = new String(Files.readAllBytes(Paths.get("passengers.json")));
            JSONArray array = new JSONArray(content);

            for (int i = 0; i < array.length(); i++) {
                JSONObject obj = array.getJSONObject(i);

                int id = obj.getInt("id");
                String firstname = obj.getString("firstname");
                String lastname = obj.getString("lastname");
                LocalDate birthDate = LocalDate.parse(obj.getString("birthDate"));
                int countryPhoneCode = obj.getInt("countryPhoneCode");
                int phone = obj.getInt("phone");
                String country = obj.getString("country");

                PassengerStorage pass = PassengerStorage.getInstance();
                pass.addPerson(new Passenger(
                        id, firstname, lastname, birthDate, countryPhoneCode, phone, country
                ));
            }

        } catch (Exception e) {
            System.err.println("Error al leer el archivo JSON: " + e.getMessage());
        }
    }

    public static void savePassengers() {
        JSONArray array = new JSONArray();
        for (Passenger p : PassengerStorage.getInstance().getPassengers()) {
            JSONObject obj = new JSONObject();
            obj.put("id", p.getId());
            obj.put("firstname", p.getFirstname());
            obj.put("lastname", p.getLastname());
            obj.put("birthDate", p.getBirthDate().toString());
            obj.put("countryPhoneCode", p.getCountryPhoneCode());
            obj.put("phone", p.getPhone());
            obj.put("country", p.getCountry());
            array.put(obj);
        }

        try {
            Files.write(Paths.get("passengers.json"), array.toString(4).getBytes());
        } catch (Exception e) {
            System.err.println("Error al guardar passengers.json: " + e.getMessage());
        }
    }

    public static void savePlanes() {
        JSONArray array = new JSONArray();
        for (Plane p : PlaneStorage.getInstance().getPlanes()) {
            JSONObject obj = new JSONObject();
            obj.put("id", p.getId());
            obj.put("brand", p.getBrand());
            obj.put("model", p.getModel());
            obj.put("maxCapacity", p.getMaxCapacity());
            obj.put("airline", p.getAirline());
            array.put(obj);
        }

        try {
            Files.write(Paths.get("planes.json"), array.toString(4).getBytes());
        } catch (Exception e) {
            System.err.println("Error al guardar planes.json: " + e.getMessage());
        }
    }

    public static void saveLocations() {
        JSONArray array = new JSONArray();
        for (Location l : LocationStorage.getInstance().getLocations()) {
            JSONObject obj = new JSONObject();
            obj.put("airportId", l.getAirportId());
            obj.put("airportName", l.getAirportName());
            obj.put("airportCity", l.getAirportCity());
            obj.put("airportCountry", l.getAirportCountry());
            obj.put("airportLatitude", l.getAirportLatitude());
            obj.put("airportLongitude", l.getAirportLongitude());
            array.put(obj);
        }

        try {
            Files.write(Paths.get("locations.json"), array.toString(4).getBytes());
        } catch (Exception e) {
            System.err.println("Error al guardar locations.json: " + e.getMessage());
        }
    }

    public static void saveFlights() {
        JSONArray array = new JSONArray();
        for (Flight f : FlightStorage.getInstance().getAllFlights()) {
            JSONObject obj = new JSONObject();
            obj.put("id", f.getId());
            obj.put("plane", f.getPlane().getId());
            obj.put("departureLocation", f.getDepartureLocation().getAirportId());
            obj.put("arrivalLocation", f.getArrivalLocation().getAirportId());
            obj.put("scaleLocation", f.getScaleLocation() != null ? f.getScaleLocation().getAirportId() : JSONObject.NULL);
            obj.put("departureDate", f.getDepartureDate().toString());
            obj.put("hoursDurationArrival", f.getHoursDurationArrival());
            obj.put("minutesDurationArrival", f.getMinutesDurationArrival());
            obj.put("hoursDurationScale", f.getHoursDurationScale());
            obj.put("minutesDurationScale", f.getMinutesDurationScale());
            array.put(obj);
        }

        try {
            Files.write(Paths.get("flights.json"), array.toString(4).getBytes());
        } catch (Exception e) {
            System.err.println("Error al guardar flights.json: " + e.getMessage());
        }
    }

}
