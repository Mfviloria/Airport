package model.storage;

import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.ArrayList;
import java.util.HashMap;

import org.json.JSONArray;
import org.json.JSONObject;

import model.Plane;

public class PlaneStorage {

    // Instancia Singleton
    private static PlaneStorage instance;

    private ArrayList<Plane> planes;
    
    private final String FILE_PATH = "planes.json"; // Archivo JSON para guardar aviones

    private PlaneStorage() {
        this.planes = new ArrayList<>();
        loadFromFile(); // cargar al crear instancia
    }

    public static PlaneStorage getInstance() {
        if (instance == null) {
            instance = new PlaneStorage();
        }
        return instance;
    }

    public ArrayList<Plane> getPlanes() {
        return planes;
    }

    public boolean addPlane(Plane plane) {
        if (this.getPlane(plane.getId()) != null) {
            return false; // ya existe
        }
        this.planes.add(plane);
        saveToFile();
        return true;
    }

    public Plane getPlane(String id) {
        for (Plane plane : this.planes) {
            if (plane.getId().equals(id)) {
                return plane;
            }
        }
        return null;
    }

    public HashMap<String, Plane> getAllPlanesMap() {
        HashMap<String, Plane> map = new HashMap<>();
        for (Plane plane : this.planes) {
            map.put(plane.getId(), plane);
        }
        return map;
    }

    // Carga desde archivo JSON
    private void loadFromFile() {
        try {
            String content = new String(Files.readAllBytes(Paths.get(FILE_PATH)));
            JSONArray array = new JSONArray(content);
            for (int i = 0; i < array.length(); i++) {
                JSONObject obj = array.getJSONObject(i);

                Plane plane = new Plane(
                    obj.getString("id"),
                    obj.getString("brand"),
                    obj.getString("model"),
                    obj.getInt("maxCapacity"),
                    obj.getString("airline")
                );
                this.planes.add(plane);
            }
        } catch (Exception e) {
            System.out.println("No se pudo cargar archivo JSON de planes o archivo no existe, se inicia vacío.");
            this.planes = new ArrayList<>();
        }
    }

    // Guarda lista de aviones a archivo JSON (sobrescribe)
    private void saveToFile() {
        try {
            JSONArray array = new JSONArray();
            for (Plane plane : this.planes) {
                JSONObject obj = new JSONObject();
                obj.put("id", plane.getId());
                obj.put("brand", plane.getBrand());
                obj.put("model", plane.getModel());
                obj.put("maxCapacity", plane.getMaxCapacity());
                obj.put("airline", plane.getAirline());
                array.put(obj);
            }
            Files.write(Paths.get(FILE_PATH), array.toString(4).getBytes(), StandardOpenOption.CREATE, StandardOpenOption.TRUNCATE_EXISTING);
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("Error al guardar planes en JSON.");
        }
    }
}
