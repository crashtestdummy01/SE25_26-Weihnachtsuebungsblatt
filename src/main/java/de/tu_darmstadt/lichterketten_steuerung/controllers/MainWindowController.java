package de.tu_darmstadt.lichterketten_steuerung.controllers;


import de.tu_darmstadt.lichterketten_steuerung.view.Controller;
import de.tu_darmstadt.lichterketten_steuerung.view.MacroEditor;
import de.tu_darmstadt.lichterketten_steuerung.view.MainWindow;
import java.awt.*;
import java.util.ArrayList;

public class MainWindowController {

    private MainWindow mainWindow;
    private ArrayList<Component> observers = new ArrayList<>();

    public MainWindowController(MainWindow mainWindow) {
        this.mainWindow = mainWindow;

        StringLightList stringLightList = new StringLightList(this.mainWindow.stringlightListPanel);

        this.mainWindow.btnNewStringLight.addActionListener(e -> {
            String area = mainWindow.getAreaName("Enter Area", "Area name:");
            stringLightList.addStringLight(area);
        });

        stringLightList.subscribe(this.mainWindow.controlPanel);

        this.mainWindow.controlPanel.areaSelector.addActionListener(e -> {
            String selectedArea = (String) this.mainWindow.controlPanel.areaSelector.getSelectedItem();
            stringLightList.selectStringLightsWith(stringLight -> stringLight.area().equals(selectedArea));
        });

        this.mainWindow.btnOpenMacroEditor.addActionListener(e -> {
            MacroEditor editor = new MacroEditor(this.mainWindow.frame, stringLightList);
            editor.show();
        });
    }

    public void subscribe(Component component){
        this.observers.add(component);
    }

    public void unsubscribe(Component component){
        this.observers.remove(component);
    }

    public void notifyObservers(){
        for(Component component : this.observers){

        }
    }
}
