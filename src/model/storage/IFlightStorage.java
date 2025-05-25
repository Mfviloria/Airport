package model.storage;

import java.util.List;
import model.flight.Flight;

public interface IFlightStorage {
    boolean addFlight(Flight flight);
    Flight getFlight(String id);
    List<Flight> getFlights();
}