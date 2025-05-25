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
public abstract class Storage<T> implements Observable{
    protected ArrayList<T> items = new ArrayList<>();
    protected ArrayList<Observer> observers = new ArrayList<>();

    public boolean add(T obj) {
        this.items.add(obj);
        this.notifyObsevers();
        return true;
    }

    public ArrayList<T> getAll() {
        return items;
    }


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
