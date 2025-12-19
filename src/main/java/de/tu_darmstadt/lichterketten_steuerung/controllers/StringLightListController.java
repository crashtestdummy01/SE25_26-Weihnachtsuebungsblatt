package de.tu_darmstadt.lichterketten_steuerung.controllers;


import de.tu_darmstadt.lichterketten_steuerung.models.StringLight;
import de.tu_darmstadt.lichterketten_steuerung.view.gui_components.Observer;
import de.tu_darmstadt.lichterketten_steuerung.view.gui_components.stringlightwidgets.StringLightWidget;
import de.tu_darmstadt.lichterketten_steuerung.view.gui_components.builder.StringLightBuilder;
import de.tu_darmstadt.lichterketten_steuerung.view.gui_components.stringlightwidgets.Product;


import javax.swing.*;
import java.awt.*;
import java.util.*;
import java.util.List;

public class StringLightListController {
    private JPanel stringlightListPanel;
    private List<StringLight> stringlightList;

    private List<Observer> observers = new ArrayList<>();
    private String selectedArea;
    private StringLight selectedStringLight;

    private int idCounter = 100;

    public StringLightListController(JPanel stringlightListPanel) {
        this.stringlightListPanel = stringlightListPanel;
        stringlightList = new ArrayList<>();
    }

    public void addStringLight(String areaName, StringLightBuilder factory) {
        if (areaName == null || areaName.isEmpty()) {return;}
        String id = areaName + ":L-" + idCounter++;
        StringLight stringLight = new StringLight(id, false, StringLight.Mode.SOLID, Color.WHITE, areaName);
        stringlightList.add(stringLight);

        // If a factory is not yet available use the default widget
        if(factory != null) {
            Product stringLightWidget = factory.getStringLightWidget(stringLight);
            stringlightListPanel.add(stringLightWidget.getComponent(), stringlightListPanel.getComponentCount() - 2);

            subscribe(stringLightWidget);
        }else {
            StringLightWidget stringLightWidget = new StringLightWidget(stringLight.id(), stringLight.isOn());
            stringlightListPanel.add(stringLightWidget, stringlightListPanel.getComponentCount() - 2);

            subscribe(stringLightWidget);
        }
        notifyObservers();
    }

    public void removeStringLight() {
        if(selectedStringLight == null){
            System.out.println("No StringLight selected");
            return;
        }
        for(Component widget:stringlightListPanel.getComponents()){
            // Removal if the factory is not yet implemented
            if(widget instanceof StringLightWidget stringLightWidget) {
                if (stringLightWidget.getId().equals(selectedStringLight.id())) {
                    stringlightListPanel.remove(stringLightWidget.getComponent());
                    stringlightList.remove(selectedStringLight);

                    unsubscribe(stringLightWidget);
                }
            }

            // Removal if a factory created the widget
            if(widget instanceof Product stringLightWidget) {
                if (stringLightWidget.getId().equals(selectedStringLight.id())) {
                    stringlightListPanel.remove(stringLightWidget.getComponent());
                    stringlightList.remove(selectedStringLight);

                    unsubscribe(stringLightWidget);
                }
            }
        }
        notifyObservers();
    }

    public void switchStringLights(boolean state){
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

    private List<StringLight> getStringLightsInArea(String areaName) {
        return stringlightList.stream().filter(light -> light.area().equals(areaName)).toList();
    }

    public void subscribe(Observer observer) {
        if(observer == null || this.observers.contains(observer)) {return;}
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


    // Public getters and setters
    public List<StringLight> getList() {
        return stringlightList;
    }
    public List<String> getAreas() {
        return stringlightList.stream().map(StringLight::area).toList();
    }
    public String getSelectedArea() {return selectedArea;}

    public List<String> getIDsInArea(String areaName) {
        return stringlightList.stream().filter(light -> light.area().equals(areaName))
                .map(StringLight::id)
                .toList();
    }

    public void setSelectedArea(String selectedArea) {this.selectedArea = selectedArea;}
    public void setSelectedStringLight(String id) {
        this.selectedStringLight = stringlightList.stream()
                .filter(light -> light.id().equals(id))
                .findFirst()
                .orElse(null);
    }
}
