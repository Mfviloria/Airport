/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controllers.storage.LoadJson;

import java.nio.file.Files;
import java.nio.file.Paths;
import java.time.LocalDateTime;
import model.flight.Flight;
import model.storage.FlightStorage;
import model.storage.IFlightStorage;
import model.storage.ILocationStorage;
import model.storage.IPlaneStorage;
import model.storage.LocationStorage;
import model.storage.PlaneStorage;
import org.json.JSONArray;
import org.json.JSONObject;

/**
 *
 * @author mariafernandaviloriazapata
 */
public class FlightsLoad {
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

                IFlightStorage flight = FlightStorage.getInstance();
                IPlaneStorage pla = PlaneStorage.getInstance();
                ILocationStorage loc = LocationStorage.getInstance();
                flight.addFlight(new Flight(id, pla.getPlane(plane), loc.getLocation(departureLocation), loc.getLocation(scaleLocation), loc.getLocation(arrivalLocation), departureDate, hoursDurationArrival, minutesDurationArrival, hoursDurationScale, minutesDurationScale));
            }

        } catch (Exception e) {
            System.err.println("Error al leer el archivo JSON: " + e.getMessage());
        }
    }
}
