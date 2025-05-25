package model.storage;

import java.util.ArrayList;
import model.Plane;
import model.observers.Observable;
import model.observers.Observer;

public class PlaneStorage implements Observable{

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

    @Override
    public void addObserver(Observer o) {
        this.observers.add(o);
    }

    @Override
    public void deleteObserver(Observer o) {
       this.observers.remove(o);
    }

    @Override
    public void notifyObsevers() {
        for (Observer o : observers) {
            o.update();
        }
    }
    

 
}
