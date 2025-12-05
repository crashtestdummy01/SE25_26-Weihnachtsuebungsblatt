package de.tu_darmstadt.lichterketten_steuerung.controllers;


import de.tu_darmstadt.lichterketten_steuerung.models.StringLight;
import de.tu_darmstadt.lichterketten_steuerung.view.Controller;
import de.tu_darmstadt.lichterketten_steuerung.view.Observer;
import de.tu_darmstadt.lichterketten_steuerung.view.StringLightWidget;

import javax.swing.*;
import java.awt.*;
import java.util.*;
import java.util.List;
import java.util.function.Predicate;

public class StringLightList implements Controller {
    private JPanel stringlightListPanel;
    private List<StringLight> stringlightList;
    private List<StringLight> selectedStringLights;

    private List<Observer> observers = new ArrayList<>();

    private int idCounter = 100;

    public StringLightList(JPanel stringlightListPanel) {
        this.stringlightListPanel = stringlightListPanel;
        stringlightList = new ArrayList<StringLight>();
        selectedStringLights = new ArrayList<>();
    }

    public void addStringLight(String areaName) {
        String id = areaName + ":L-" + idCounter++;
        StringLight stringLight = new StringLight(id, false, StringLight.Mode.SOLID, Color.WHITE, areaName);
        StringLightWidget stringLightWidget = new StringLightWidget(stringLight.id(), stringLight.color(), stringLight.isOn());
        stringlightList.add(stringLight);
        stringlightListPanel.add(stringLightWidget, 0);

        stringlightListPanel.updateUI();
        notifyObservers();

        System.out.println(stringLight.area());

    }

    public void switchStringLight(boolean state) {
        selectedStringLights.forEach(stringLight -> {stringLight.setOn(state);});
    }

    public void selectStringLightsWith(Predicate<StringLight> predicate) {
        selectedStringLights = stringlightList.stream().filter(predicate).toList();
        notifyObservers();
    }

    public Set<String> getAreas() {
        Set<String> areas = new HashSet<>();
        for (StringLight stringLight : stringlightList) {
            areas.add(stringLight.area());
        }

        return areas;
    }

    public Set<String> getIDs() {
        Set<String> ids = new HashSet<>();
        for (StringLight stringLight : selectedStringLights) {
            ids.add(stringLight.id());
        }

        return ids;
    }

    public void subscribe(Observer component){
        this.observers.add(component);
    }

    public void unsubscribe(Observer component){
        this.observers.remove(component);
    }

    public void notifyObservers(){
        for(Observer observer : observers){
            observer.update(this);
        }
    }
}
