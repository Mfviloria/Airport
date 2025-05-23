/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model.storage;

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
import org.json.JSONArray;
import org.json.JSONObject;

/**
 *
 * @author mariafernandaviloriazapata
 */
public class LoadJson {
    public static void loadLocations() {
        try {
            System.out.println("Directorio actual: " + System.getProperty("user.dir"));
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
                loc.addLocation(new Location(airportId,airportName,airportCity,airportCountry,airportLatitude,airportLongitude));
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
                String scaleLocation = obj.getString("scaleLocation");
                LocalDateTime departureDate = LocalDateTime.parse(obj.getString("departureDate"));
                int hoursDurationArrival = obj.getInt("hoursDurationArrival");
                int minutesDurationArrival = obj.getInt("minutesDurationArrival");
                int hoursDurationScale = obj.getInt("hoursDurationScale");
                int minutesDurationScale = obj.getInt("minutesDurationScale");
                
                FlightStorage flight = FlightStorage.getInstance();
                PlaneStorage pla = PlaneStorage.getInstance();
                LocationStorage loc = LocationStorage.getInstance();
                flight.addFlight(new Flight(id,pla.getPlane(plane), loc.getLocation(departureLocation), loc.getLocation(arrivalLocation), loc.getLocation(scaleLocation), departureDate,hoursDurationArrival, minutesDurationArrival,hoursDurationScale,minutesDurationScale ));
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

}
