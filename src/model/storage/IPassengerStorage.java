// IPassengerStorage.java
package model.storage;


import java.util.List;
import model.Passenger.Passenger;

public interface IPassengerStorage {
    boolean addPassenger(Passenger passenger);
    Passenger getPassenger(long id);
    List<Passenger> getPassengers();
}