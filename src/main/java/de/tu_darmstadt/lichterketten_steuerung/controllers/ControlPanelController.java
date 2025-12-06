package de.tu_darmstadt.lichterketten_steuerung.controllers;

import de.tu_darmstadt.lichterketten_steuerung.view.Controller;
import de.tu_darmstadt.lichterketten_steuerung.view.Observer;

import java.util.*;

public class ControlPanelController implements Controller {

    private List<Observer> observers;
    private Set<String> areas;
    private Set<String> lightIDs;

    public ControlPanelController() {
        observers = new ArrayList<>();
        areas = new TreeSet<>();
        lightIDs = new TreeSet<>();
    }

    public void onAreaSelect(String areaName) {

    }
    public void onLightIdSelect(){}
    public void onPowerChanged() {}
    public void onModeChanged() {}
    public void onSetColor() {}
    public void onLightDelete() {}

    @Override
    public void subscribe(Observer observer) {
        observers.add(observer);
    }

    @Override
    public void unsubscribe(Observer observer) {
        observers.remove(observer);
    }

    @Override
    public void notifyObservers() {
        for (Observer observer : observers) {
            observer.update(this);
        }
    }
}
