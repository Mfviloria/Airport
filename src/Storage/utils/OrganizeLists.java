/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Storage.utils;

import java.util.ArrayList;
import java.util.Collections;
import model.Flight;
import model.Location;
import model.Passenger;
import model.Plane;

/**
 *
 * @author mariafernandaviloriazapata
 */
public class OrganizeLists {
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
    public static ArrayList<Flight> organizeListFlight(ArrayList<Flight> lista){
       lista.sort((f1, f2) -> f2.getDepartureDate().compareTo(f1.getDepartureDate()));


        return lista;
    }
}
