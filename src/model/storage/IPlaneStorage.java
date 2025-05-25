/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package model.storage;

import java.util.ArrayList;
import model.Plane;

/**
 *
 * @author mariafernandaviloriazapata
 */
public interface IPlaneStorage {
    boolean addPlane(Plane plane);
    Plane getPlane(String id);
    ArrayList<Plane> getPlanes();
}
