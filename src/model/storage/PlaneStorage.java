/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model.storage;

import java.awt.List;
import java.util.ArrayList;
import java.util.HashMap;
import model.Flight;
import model.Location;
import model.Passenger;
import model.Plane;

/**
 *
 * @author edangulo
 */
public class PlaneStorage {

    // Instancia Singleton
    private static PlaneStorage instance;

    private ArrayList< Plane> planes;

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
        return  planes;
    }    

    public boolean addPlane(Plane plane) {
        if (this.getPlane(plane.getId()) != null){
            return false;
        } else{
            this.planes.add(plane);
        }
        return true;
    }

    public Plane getPlane(String id) {
        for (Plane plane : this.planes){
            if (plane.getId().equals(id)){
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


    
}
