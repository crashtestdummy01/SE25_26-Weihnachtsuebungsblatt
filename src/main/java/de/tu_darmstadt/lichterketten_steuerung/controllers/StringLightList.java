package de.tu_darmstadt.lichterketten_steuerung.controllers;


import de.tu_darmstadt.lichterketten_steuerung.models.StringLight;
import de.tu_darmstadt.lichterketten_steuerung.view.Controller;
import de.tu_darmstadt.lichterketten_steuerung.view.Observer;
import de.tu_darmstadt.lichterketten_steuerung.view.StringLightListPanel;
import de.tu_darmstadt.lichterketten_steuerung.view.StringLightWidget;

import javax.swing.*;
import java.awt.*;
import java.util.*;
import java.util.List;

public class StringLightList implements Controller {
    private JPanel stringlightListPanel;
    private List<StringLight> stringlightList;

    private List<Observer> observers = new ArrayList<>();
    public String selectedArea;
    public StringLight selectedStringLight;

    private int idCounter = 100;

    public StringLightList(JPanel stringlightListPanel) {
        this.stringlightListPanel = stringlightListPanel;
        stringlightList = new ArrayList<>();
    }

    public void onAddButton(String areaName) {
        String id = areaName + ":L-" + idCounter++;
        StringLight stringLight = new StringLight(id, false, StringLight.Mode.SOLID, Color.WHITE, areaName);
        StringLightWidget stringLightWidget = new StringLightWidget(stringLight.id(), stringLight.color(), stringLight.isOn());
        stringlightList.add(stringLight);
        stringlightListPanel.add(stringLightWidget, stringlightListPanel.getComponentCount()-2);

        subscribe(stringLightWidget);
        notifyObservers();
    }

    public void onRemoveButton() {
        if(selectedStringLight == null){
            System.out.println("No StringLight selected");
            return;
        }
        for(Component widget:stringlightListPanel.getComponents()){
            if(!(widget instanceof StringLightWidget stringLightWidget)){continue;}
            if(stringLightWidget.getId().equals(selectedStringLight.id())){
                stringlightListPanel.remove(stringLightWidget);
                stringlightList.remove(selectedStringLight);

                unsubscribe(stringLightWidget);
            }
        }
        notifyObservers();
    }

    public void onLightSwitch(boolean state){
        if(selectedArea == null){return;}
        if(selectedStringLight == null){
            for (StringLight stringLight: getStringLightsInArea(selectedArea)){
                stringLight.setOn(state);
            }
        }else{
            selectedStringLight.setOn(state);
        }

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

    public List<StringLight> getList() {
        return stringlightList;
    }
    public List<String> getAreas() {
        return stringlightList.stream().map(StringLight::area).toList();
    }

    public List<String> getIDsInArea(String areaName) {
        return stringlightList.stream().filter(light -> light.area().equals(areaName)).map(StringLight::id).toList();
    }

    private List<StringLight> getStringLightsInArea(String areaName) {
        return stringlightList.stream().filter(light -> light.area().equals(areaName)).toList();
    }

    public void setSelectedStringLight(String id) {
        this.selectedStringLight = stringlightList.stream()
                .filter(light -> light.id().equals(id))
                .findFirst()
                .orElse(null);
    }
}
