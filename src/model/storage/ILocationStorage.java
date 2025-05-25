/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package model.storage;

import java.util.ArrayList;
import model.Location;

/**
 *
 * @author mariafernandaviloriazapata
 */
public interface ILocationStorage {
    boolean addLocation(Location location);
    Location getLocation(String id);
    ArrayList<Location> getLocations();
}
