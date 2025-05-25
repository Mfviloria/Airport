/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model.storage;

import java.util.ArrayList;
import model.observers.Observable;
import model.observers.Observer;

/**
 *
 * @author mariafernandaviloriazapata
 */
public abstract class Storage implements Observable{
    protected ArrayList<Observer> observers = new ArrayList<>();
    
    @Override
    public void addObserver(Observer o) {
        observers.add(o);
    }

    @Override
    public void deleteObserver(Observer o) {
        observers.remove(o);
    }

    @Override
    public void notifyObsevers() {
        for (Observer o : observers) {
            o.update();
        }
    }
    
}
