package de.tu_darmstadt.lichterketten_steuerung.controllers;


import de.tu_darmstadt.lichterketten_steuerung.view.ControlPanel;
import de.tu_darmstadt.lichterketten_steuerung.view.MainWindow;
import de.tu_darmstadt.lichterketten_steuerung.view.StringLightListPanel;

import java.awt.*;

public class MainWindowController {
    private ControlPanel controlPanel;
    private StringLightListPanel stringLightListPanel;
    private StringLightList stringLightList;
    private MainWindow mainWindow;

    public MainWindowController(MainWindow mainWindow) {
        this.mainWindow = mainWindow;
        this.controlPanel = mainWindow.controlPanel;
        this.stringLightListPanel = mainWindow.stringlightListPanel;
        this.stringLightList = new StringLightList(stringLightListPanel.base);

        stringLightList.subscribe(controlPanel);
        stringLightList.subscribe(stringLightListPanel);

        bindMethods();
    }

    private void bindMethods() {
        stringLightListPanel.btnNewStringLight.addActionListener( e -> {
            stringLightList.onAddButton(mainWindow.getAreaName("Enter Area", "Name:"));
        });

        controlPanel.getAreaSelector().addActionListener( e -> {
            stringLightList.selectedArea = (String) controlPanel.getAreaSelector().getSelectedItem();
            if(stringLightList.selectedArea == null){stringLightList.selectedArea = "--None--";}
            if(stringLightList.selectedArea.equals("--None--")){stringLightList.selectedArea = null;}
            stringLightList.setSelectedStringLight(null);
            controlPanel.getLightIdSelector().setSelectedIndex(0);
            stringLightList.notifyObservers();
        });

        controlPanel.getLightIdSelector().addActionListener( e -> {
            stringLightList.setSelectedStringLight((String) controlPanel.getLightIdSelector().getSelectedItem());
            stringLightList.notifyObservers();
        });

        controlPanel.getChkOnOff().addActionListener( e -> {
            stringLightList.onLightSwitch(controlPanel.getChkOnOff().isSelected());
        });

        controlPanel.getBtnRemove().addActionListener(e -> {
            stringLightList.onRemoveButton();
            controlPanel.getAreaSelector().setSelectedIndex(0);
            controlPanel.getLightIdSelector().setSelectedIndex(0);
        });
    }
}
