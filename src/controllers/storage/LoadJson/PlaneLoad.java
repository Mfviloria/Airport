/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controllers.storage.LoadJson;

import java.nio.file.Files;
import java.nio.file.Paths;
import model.plane.Plane;
import model.storage.IPlaneStorage;
import model.storage.PlaneStorage;
import org.json.JSONArray;
import org.json.JSONObject;

/**
 *
 * @author mariafernandaviloriazapata
 */
public class PlaneLoad {
    public static void loadPlane() {
        try {
            String content = new String(Files.readAllBytes(Paths.get("planes.json")));
            JSONArray array = new JSONArray(content);
            for (int i = 0; i < array.length(); i++) {
                JSONObject obj = array.getJSONObject(i);

                String id = obj.getString("id");
                String brand = obj.getString("brand");
                String model = obj.getString("model");
                int maxCapacity = obj.getInt("maxCapacity");
                String airline = obj.getString("airline");
                IPlaneStorage plane = PlaneStorage.getInstance();
                plane.addPlane(new Plane(id, brand, model, maxCapacity, airline));
            }

        } catch (Exception e) {
            System.err.println("Error al leer el archivo JSON: " + e.getMessage());
        }
    }
}
