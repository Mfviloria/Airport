/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controllers.storage;

import java.util.ArrayList;
import java.util.Collections;
import model.flight.Flight;
import model.Location.Location;
import model.Passenger.Passenger;
import model.plane.Plane;

/**
 *
 * @author mariafernandaviloriazapata
 */
public class OrganizeLists {
    // Organiza Lista dependiendo del id de los pasajeros
    public static ArrayList<Passenger> organizeList(ArrayList<Passenger> lista){
        for (int i = 0; i < lista.size() - 1; i++) {
            for (int j = 0; j < lista.size() - i - 1; j++) {
                if (lista.get(j).getId() > lista.get(j + 1).getId()) {
                    // Intercambiar
                    Passenger temp = lista.get(j);
                    lista.set(j, lista.get(j + 1));
                    lista.set(j + 1, temp);
                }
            }
        }
        return lista;
    }
    //Organiza la lista dependiendo del ID de las locaciones
    public static ArrayList<Location> organizeListLoc(ArrayList<Location> lista){
        for (int i = 0; i < lista.size() - 1; i++) {
            for (int j = 0; j < lista.size() - i - 1; j++) {
                if (lista.get(j).getAirportId().compareTo(lista.get(j + 1).getAirportId()) > 0) {
                    // Intercambio
                    Location temp = lista.get(j);
                    lista.set(j, lista.get(j + 1));
                    lista.set(j + 1, temp);
                }
            }
        }
        return lista;
    }
    //Organiza la lista de los aviones dependiendo de su id
    public static ArrayList<Plane> organizeListPlane(ArrayList<Plane> lista){
        for (int i = 0; i < lista.size() - 1; i++) {
            for (int j = 0; j < lista.size() - i - 1; j++) {
                if (lista.get(j).getId().compareTo(lista.get(j + 1).getId()) > 0) {
                    // Intercambio
                    Plane temp = lista.get(j);
                    lista.set(j, lista.get(j + 1));
                    lista.set(j + 1, temp);
                }
            }
        }
        return lista;
    }
    //Organiza la lista de los vuelos dependiendo de la fecha en la que parten.
    public static ArrayList<Flight> organizeListFlight(ArrayList<Flight> lista){
       lista.sort((f1, f2) -> f1.getDepartureDate().compareTo(f2.getDepartureDate()));
        return lista;
    }
}
