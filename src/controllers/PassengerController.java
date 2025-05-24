/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controllers;

import controllers.storage.LoadJson;
import controllers.utils.Response;
import controllers.utils.Status;
import java.time.LocalDate;
import model.Passenger;
import model.storage.PassengerStorage;

/**
 *
 * @author mariafernandaviloriazapata
 */
public class PassengerController {

    public static Response createPassenger(String id, String firstname, String lastname, String year, String month, String day, String phoneCode, String phone, String country) {
        PassengerStorage storage = PassengerStorage.getInstance();
        try {
            if (id.isEmpty()) {
                return new Response("Id must be not empty", Status.BAD_REQUEST);
            } else if (firstname.isEmpty()) {
                return new Response("First name must be not empty", Status.BAD_REQUEST);
            } else if (lastname.isEmpty()) {
                return new Response("Last name must be not empty", Status.BAD_REQUEST);
            } else if (year.isEmpty() || month.trim().equalsIgnoreCase("Month") || day.trim().equalsIgnoreCase("Day")) {
                return new Response("Birth day must be not empty", Status.BAD_REQUEST);
            } else if (phoneCode.isEmpty()) {
                return new Response("Phone code must be not empty", Status.BAD_REQUEST);
            } else if (phone.isEmpty()) {
                return new Response("Phone must be not empty", Status.BAD_REQUEST);
            } else if (country.isEmpty()) {
                return new Response("Country must be not empty", Status.BAD_REQUEST);
            }
            //Id verification
            if (Integer.parseInt(id) < 0) {
                return new Response("Id must be positive", Status.BAD_REQUEST);
            } else if (id.length() > 15) {
                return new Response("Id must have at least 15 digits", Status.BAD_REQUEST);
            } else if (storage.getPassenger(Integer.parseInt(id)) != null) {
                return new Response("A passenger with that id already exists", Status.BAD_REQUEST);
            }
        } catch (NumberFormatException ex) {
            return new Response("Id must be numeric", Status.BAD_REQUEST);
        }
        // Birth day verification
        try {
            int yearInt = Integer.parseInt(year);
            if (yearInt < 1920) {
                return new Response("Year must be more than 1920", Status.BAD_REQUEST);
            } else if (yearInt > 2025) {
                return new Response("Year must be less than 2025", Status.BAD_REQUEST);
            } else if (yearInt < 0) {
                return new Response("Year must be positive", Status.BAD_REQUEST);
            }

        } catch (NumberFormatException ex) {
            return new Response("Birth day must be numeric", Status.BAD_REQUEST);
        }
        try {
            int phoneCodeInt = Integer.parseInt(phoneCode);
            if (phoneCodeInt < 0) {
                return new Response("Phone code must be positive", Status.BAD_REQUEST);
            } else if (phoneCode.length() > 3) {
                return new Response("Phone code must have at least 3 digits", Status.BAD_REQUEST);
            }
        } catch (NumberFormatException ex) {
            return new Response("Phone code must be numeric", Status.BAD_REQUEST);
        }

        try {
            long phoneLong = Long.parseLong(phone);
            if (phoneLong < 0) {
                return new Response("Phone must be positive", Status.BAD_REQUEST);
            } else if (phone.length() > 12) {
                return new Response("Phone must have at least 11 digits", Status.BAD_REQUEST);
            }
        } catch (NumberFormatException ex) {
            return new Response("Phone must be numeric", Status.BAD_REQUEST);
        }

        LocalDate birthDate = LocalDate.of(Integer.parseInt(year), Integer.parseInt(month), Integer.parseInt(day));
        storage.addPerson(new Passenger(Long.parseLong(id), firstname, lastname, birthDate, Integer.parseInt(phoneCode), Long.parseLong(phone), country));
        System.out.println(storage.getPassengers());

        LoadJson.savePassengers();  // Para guardar los pasajeros

        return new Response("Passenger added succesfully", Status.OK);
    }
}
