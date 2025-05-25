package model.storage;


import java.util.List;
import model.plane.Plane;

public interface IPlaneStorage {
    boolean addPlane(Plane plane);
    Plane getPlane(String id);
    List<Plane> getPlanes();
}