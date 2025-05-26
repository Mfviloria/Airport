package model.flight;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */


import java.time.LocalDateTime;
import java.util.ArrayList;
import model.Location.Location;
import model.Passenger.Passenger;
import model.plane.Plane;

/**
 *
 * @author edangulo
 */
public class Flight implements Cloneable{
    
    private final String id;
    private ArrayList<Passenger> passengers;
    private Plane plane;
    private Location departureLocation;
    private Location scaleLocation;
    private Location arrivalLocation;
    private LocalDateTime departureDate;
    private int hoursDurationArrival;
    private int minutesDurationArrival;
    private int hoursDurationScale;
    private int minutesDurationScale;
    

    public Flight(String id, Plane plane, Location departureLocation, Location arrivalLocation, LocalDateTime departureDate, int hoursDurationArrival, int minutesDurationArrival) {
        this.id = id;
        this.passengers = new ArrayList<>();
        this.plane = plane;
        this.departureLocation = departureLocation;
        this.arrivalLocation = arrivalLocation;
        this.departureDate = departureDate;
        this.hoursDurationArrival = hoursDurationArrival;
        this.minutesDurationArrival = minutesDurationArrival;
        
        this.plane.addFlight(this);
    }

    public Flight(String id, Plane plane, Location departureLocation, Location scaleLocation, Location arrivalLocation, LocalDateTime departureDate, int hoursDurationArrival, int minutesDurationArrival, int hoursDurationScale, int minutesDurationScale) {
        this.id = id;
        this.passengers = new ArrayList<>();
        this.plane = plane;
        this.departureLocation = departureLocation;
        this.scaleLocation = scaleLocation;
        this.arrivalLocation = arrivalLocation;
        this.departureDate = departureDate;
        this.hoursDurationArrival = hoursDurationArrival;
        this.minutesDurationArrival = minutesDurationArrival;
        this.hoursDurationScale = hoursDurationScale;
        this.minutesDurationScale = minutesDurationScale;
        
        this.plane.addFlight(this);
    }
    
    @Override
    public Flight clone() {
        try {
            Flight copy = (Flight) super.clone();

            // Clonar lista de pasajeros (si necesitas que no sea la misma referencia)
            ArrayList<Passenger> clonedPassengers = new ArrayList<>();
            for (Passenger p : this.passengers) {
                clonedPassengers.add(p.clone()); // Necesita que Passenger implemente Cloneable
            }
            copy.passengers = clonedPassengers;

            // IMPORTANTE: Si Plane o Location son mutables y compartidas, clónalas también
            copy.plane = this.plane.clone(); // si Plane implementa Cloneable
            copy.departureLocation = this.departureLocation.clone(); // si Location también lo hace
            if (this.scaleLocation != null)
                copy.scaleLocation = this.scaleLocation.clone();
            copy.arrivalLocation = this.arrivalLocation.clone();

            return copy;
        } catch (CloneNotSupportedException e) {
            throw new RuntimeException(e);
        }
    }
    
    
    public void addPassenger(Passenger passenger) {
        this.passengers.add(passenger);
    }
    
    public String getId() {
        return id;
    }

    public Location getDepartureLocation() {
        return departureLocation;
    }

    public Location getScaleLocation() {
        return scaleLocation;
    }

    public Location getArrivalLocation() {
        return arrivalLocation;
    }

    public LocalDateTime getDepartureDate() {
        return departureDate;
    }

    public int getHoursDurationArrival() {
        return hoursDurationArrival;
    }

    public int getMinutesDurationArrival() {
        return minutesDurationArrival;
    }

    public int getHoursDurationScale() {
        return hoursDurationScale;
    }

    public int getMinutesDurationScale() {
        return minutesDurationScale;
    }

    public Plane getPlane() {
        return plane;
    }

    public void setDepartureDate(LocalDateTime departureDate) {
        this.departureDate = departureDate;
    }
   
    public int getNumPassengers() {
        return passengers.size();
    }

    @Override
    public String toString() {
        return "Flight{" + "id=" + id + ", passengers=" + passengers + ", plane=" + plane + ", departureLocation=" + departureLocation + ", scaleLocation=" + scaleLocation + ", arrivalLocation=" + arrivalLocation + ", departureDate=" + departureDate + ", hoursDurationArrival=" + hoursDurationArrival + ", minutesDurationArrival=" + minutesDurationArrival + ", hoursDurationScale=" + hoursDurationScale + ", minutesDurationScale=" + minutesDurationScale + '}';
    }
    
    
}
