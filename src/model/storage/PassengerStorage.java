/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model.storage;

import java.util.ArrayList;
import model.Passenger;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import org.json.JSONArray;
import org.json.JSONObject;
import java.time.LocalDate;

/**
 *
 * @author edangulo
 */
public class PassengerStorage {

    // Instancia Singleton
    private static PassengerStorage instance;

    private ArrayList<Passenger> passengers;

    private PassengerStorage() {
        this.passengers = new ArrayList<>();
        loadFromJson("C:\\Users\\samuel\\Documents\\GitHub\\Airport\\json\\passengers.json");
    }

    public static PassengerStorage getInstance() {
        if (instance == null) {
            instance = new PassengerStorage();
        }

        return instance;
    }

    public boolean addPerson(Passenger passenger) {
        for (Passenger p : this.passengers) {
            if (p.getId() == passenger.getId()) {
                return false;
            }
        }
        this.passengers.add(passenger);
        return true;
    }

    public Passenger getPassenger(long id) {
        for (Passenger pass : this.passengers) {
            if (pass.getId() == id) {
                return pass;
            }
        }
        return null;
    }

    public boolean delPassenger(long id) {
        for (Passenger pass : this.passengers) {
            if (pass.getId() == id) {
                this.passengers.remove(pass);
                return true;
            }
        }
        return false;
    }

    public ArrayList<Passenger> getPassengers() {
        return passengers;
    }

    public void loadFromJson(String filename) {
        try {
            String content = new String(Files.readAllBytes(Paths.get(filename)));
            JSONArray passengersArray = new JSONArray(content);

            for (int i = 0; i < passengersArray.length(); i++) {
                JSONObject obj = passengersArray.getJSONObject(i);

                long id = obj.getLong("id");
                String firstname = obj.getString("firstname");
                String lastname = obj.getString("lastname");

                String[] dateParts = obj.getString("birthDate").split("-");
                LocalDate birthDate = LocalDate.of(
                        Integer.parseInt(dateParts[0]),
                        Integer.parseInt(dateParts[1]),
                        Integer.parseInt(dateParts[2])
                );

                int countryPhoneCode = obj.getInt("countryPhoneCode");
                long phone = obj.getLong("phone");
                String country = obj.getString("country");

                Passenger passenger = new Passenger(id, firstname, lastname, birthDate, countryPhoneCode, phone, country);
                this.addPerson(passenger); // Agrega al ArrayList si no está repetido
            }

        } catch (IOException e) {
            System.err.println("Error reading file: " + e.getMessage());
        } catch (Exception e) {
            System.err.println("Error parsing JSON: " + e.getMessage());
        }
    }

}
