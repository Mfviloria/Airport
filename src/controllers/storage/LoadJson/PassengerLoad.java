/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controllers.storage.LoadJson;

import java.nio.file.Files;
import java.nio.file.Paths;
import java.time.LocalDate;
import model.Passenger.Passenger;
import model.storage.IPassengerStorage;
import model.storage.PassengerStorage;
import org.json.JSONArray;
import org.json.JSONObject;

/**
 *
 * @author mariafernandaviloriazapata
 */
public class PassengerLoad {
    public static void loadPassenger() {
        try {
            String content = new String(Files.readAllBytes(Paths.get("passengers.json")));
            JSONArray array = new JSONArray(content);

            for (int i = 0; i < array.length(); i++) {
                JSONObject obj = array.getJSONObject(i);

                int id = obj.getInt("id");
                String firstname = obj.getString("firstname");
                String lastname = obj.getString("lastname");
                LocalDate birthDate = LocalDate.parse(obj.getString("birthDate"));
                int countryPhoneCode = obj.getInt("countryPhoneCode");
                int phone = obj.getInt("phone");
                String country = obj.getString("country");

                IPassengerStorage pass = PassengerStorage.getInstance();
                pass.addPassenger(new Passenger(
                        id, firstname, lastname, birthDate, countryPhoneCode, phone, country
                ));
            }

        } catch (Exception e) {
            System.err.println("Error al leer el archivo JSON: " + e.getMessage());
        }
    }
}
