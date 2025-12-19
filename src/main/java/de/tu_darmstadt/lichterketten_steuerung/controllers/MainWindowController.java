package de.tu_darmstadt.lichterketten_steuerung.controllers;


import de.tu_darmstadt.lichterketten_steuerung.view.gui_components.ControlPanel;
import de.tu_darmstadt.lichterketten_steuerung.view.MainWindow;
import de.tu_darmstadt.lichterketten_steuerung.view.gui_components.StringLightListPanel;
import de.tu_darmstadt.lichterketten_steuerung.view.gui_components.factories.DefaultStringLightFactory;
import de.tu_darmstadt.lichterketten_steuerung.view.gui_components.factories.StallStringLightFactory;
import de.tu_darmstadt.lichterketten_steuerung.view.gui_components.factories.StringLightFactory;
import de.tu_darmstadt.lichterketten_steuerung.view.gui_components.factories.TreeStringLightFactory;

public class MainWindowController {
    private ControlPanel controlPanel;
    private StringLightListPanel stringLightListPanel;
    private StringLightListController stringLightList;
    private MainWindow mainWindow;

    public MainWindowController(MainWindow mainWindow) {
        this.mainWindow = mainWindow;
        this.controlPanel = mainWindow.controlPanel;
        this.stringLightListPanel = mainWindow.stringlightListPanel;
        this.stringLightList = new StringLightListController(stringLightListPanel.base);

        bindMethods();
    }

    /**
     * Binds action listeners to GUI components
     */
    private void bindMethods() {
        stringLightListPanel.btnNewStringLight.addActionListener( e -> {
            String type = mainWindow.getUserInput("Select Type", "'1' Default; '2' Tree; '3' Stall");

            //TODO: Aufgabe 3:Your code goes here


            //TODO: If factory is implemented, pass it as the second argument to the addStringLightMethod instead of null
            stringLightList.addStringLight(mainWindow.getUserInput("Enter Area", "Name:"), null);
        });
        controlPanel.getAreaSelector().addActionListener( e -> {
            onAreaSelect();
        });

        controlPanel.getLightIdSelector().addActionListener( e -> {
            stringLightList.setSelectedStringLight((String) controlPanel.getLightIdSelector().getSelectedItem());
        });

        controlPanel.getChkOnOff().addActionListener( e -> {
            stringLightList.switchStringLights(controlPanel.getChkOnOff().isSelected());
        });

        controlPanel.getBtnRemove().addActionListener(e -> {
            stringLightList.removeStringLight();

            //Reset selection dropdowns
            controlPanel.getAreaSelector().setSelectedIndex(0);
            controlPanel.getLightIdSelector().setSelectedIndex(0);
        });
    }

    private void onAreaSelect(){
        stringLightList.setSelectedArea((String) controlPanel.getAreaSelector().getSelectedItem());

        //Safety net if selected area is somehow null
        if(stringLightList.getSelectedArea()  == null){stringLightList.setSelectedArea("--None--");}

        //Reset selection
        if(stringLightList.getSelectedArea() .equals("--None--")){stringLightList.setSelectedArea(null);}
        stringLightList.setSelectedStringLight(null);
        controlPanel.getLightIdSelector().setSelectedIndex(0);
    }
}
