package model.storage;

import java.util.ArrayList;
import model.plane.Plane;
import model.observers.Observable;
import model.observers.Observer;

public class PlaneStorage extends Storage{

    // Instancia Singleton
    private static PlaneStorage instance;
    private ArrayList<Observer> observers = new ArrayList<>();
    private ArrayList<Plane> planes;

    private PlaneStorage() {
        this.planes = new ArrayList<>();

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
        this.notifyObsevers();
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
 
}
