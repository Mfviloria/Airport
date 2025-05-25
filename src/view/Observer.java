/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package view;

import java.util.ArrayList;
import model.Flight;
import model.Location;
import model.Passenger;
import model.Plane;

/**
 *
 * @author mariafernandaviloriazapata
 */
public interface Observer {
    void updateFlight(ArrayList<Flight> flight);
    void updatePassenger(ArrayList<Passenger>  passenger);
    void updatePlane(ArrayList<Plane>  plane);
    void updateLocation(ArrayList<Location>  Location);
    void updateMyFlights(ArrayList<Flight>  flight);
}
