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

    private PlaneStorage() {
        this.planes = new ArrayList<>();
        loadFromJson("C:\\Users\\samuel\\Documents\\GitHub\\Airport\\json\\planes.json");
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
        if (getPlane(plane.getId()) != null) {
            return false;
        }
        this.planes.add(plane);
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

    // ✅ Carga desde archivo JSON con path recibido por parámetro
    public void loadFromJson(String filename) {
        try {
            String content = new String(Files.readAllBytes(Paths.get(filename)));
            JSONArray array = new JSONArray(content);

            for (int i = 0; i < array.length(); i++) {
                JSONObject obj = array.getJSONObject(i);

                String id = obj.getString("id");
                String brand = obj.getString("brand");
                String model = obj.getString("model");
                int maxCapacity = obj.getInt("maxCapacity");
                String airline = obj.getString("airline");

                Plane plane = new Plane(id, brand, model, maxCapacity, airline);
                this.addPlane(plane); // Usa el método addPlane que evita duplicados
            }

        } catch (Exception e) {
            System.err.println("Error al cargar archivo JSON de aviones: " + e.getMessage());
            this.planes = new ArrayList<>();
        }
    }

    // ✅ Guardar al archivo que se le pase por parámetro
    public void saveToJson(String filename) {
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

            Files.write(
                Paths.get(filename),
                array.toString(4).getBytes(),
                StandardOpenOption.CREATE,
                StandardOpenOption.TRUNCATE_EXISTING
            );

        } catch (Exception e) {
            System.err.println("Error al guardar planes en JSON: " + e.getMessage());
        }
    }
}
