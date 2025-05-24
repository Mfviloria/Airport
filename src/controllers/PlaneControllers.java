package controllers;

import controllers.storage.LoadJson;
import controllers.utils.Response;
import controllers.utils.Status;
import java.time.LocalDateTime;
import model.Flight;
import model.Location;
import model.Plane;
import model.storage.PlaneStorage;

public class PlaneControllers {

    public static Response createPlane(String id, String brand, String model, String maxCapacity, String airline) {
        PlaneStorage storage = PlaneStorage.getInstance();

        if (id.isEmpty() || brand.isEmpty() || model.isEmpty() || maxCapacity.isEmpty() || airline.isEmpty()) {
            return new Response("Por favor, completa todos los campos.", Status.BAD_REQUEST);

        }
        int maxCapacityInt;
        try {
            maxCapacityInt = Integer.parseInt(maxCapacity);
        } catch (NumberFormatException e) {
            return new Response("La capacidad máxima debe ser un número válido.", Status.BAD_REQUEST);
        }

        if (!id.matches("^[A-Z]{2}[0-9]{5}$")) {
            return new Response("Plane id must match the format XXYYYYY (2 letters + 5 digits)", Status.BAD_REQUEST);
        }
        if (storage.getPlane(id) != null) {
            return new Response("A plane with that id already exists", Status.BAD_REQUEST);
        }

        if (brand.isEmpty()) {
            return new Response("Brand must not be empty", Status.BAD_REQUEST);
        }
        if (model.isEmpty()) {
            return new Response("Model must not be empty", Status.BAD_REQUEST);
        }
        if (airline.isEmpty()) {
            return new Response("Airline must not be empty", Status.BAD_REQUEST);
        }

        if (maxCapacityInt <= 0) {
            return new Response("Max capacity must be positive", Status.BAD_REQUEST);
        }

        Plane newPlane = new Plane(id, brand, model, maxCapacityInt, airline);
        storage.addPlane(newPlane);

        LoadJson.savePlanes();      // Para guardar los aviones

        return new Response("Plane added successfully", Status.OK);
    }

}
