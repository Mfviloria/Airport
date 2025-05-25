/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package model.storage;

import java.util.ArrayList;
import model.Flight;

/**
 *
 * @author mariafernandaviloriazapata
 */
public interface IFlightStorage {
    boolean addFlight(Flight flight);
    Flight getFlight(String id);
    ArrayList<Flight> getFlights();
}
