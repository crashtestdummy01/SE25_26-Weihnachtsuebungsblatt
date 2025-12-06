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

    private List<Observer> observers = new ArrayList<>();

    private int idCounter = 100;

    public StringLightList(JPanel stringlightListPanel) {
        this.stringlightListPanel = stringlightListPanel;
        stringlightList = new ArrayList<>();
    }

    public void addStringLight(String areaName) {
        String id = areaName + ":L-" + idCounter++;
        StringLight stringLight = new StringLight(id, false, StringLight.Mode.SOLID, Color.WHITE, areaName);
        StringLightWidget stringLightWidget = new StringLightWidget(stringLight.id(), stringLight.color(), stringLight.isOn());
        stringlightList.add(stringLight);
        stringlightListPanel.add(stringLightWidget, 0);

        stringlightListPanel.updateUI();

        subscribe(stringLightWidget);
        notifyObservers();
    }

    public void subscribe(Observer observer) {
        this.observers.add(observer);
    }

    public void unsubscribe(Observer observer){
        this.observers.remove(observer);
    }

    public void notifyObservers(){
        for(Observer observer : observers){
            observer.update(this);
        }
    }

    public List<StringLight> getStringlightList() {
        return stringlightList;
    }
}
