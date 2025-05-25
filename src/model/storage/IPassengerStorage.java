/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package model.storage;

import java.util.ArrayList;
import model.Passenger;

/**
 *
 * @author mariafernandaviloriazapata
 */
public interface IPassengerStorage {
    boolean addPassenger(Passenger passenger);
    Passenger getPassenger(long id);
    ArrayList<Passenger> getPassengers();
}
