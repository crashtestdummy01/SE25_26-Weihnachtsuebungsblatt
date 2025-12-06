package de.tu_darmstadt.lichterketten_steuerung.controllers;


import de.tu_darmstadt.lichterketten_steuerung.view.gui_components.ControlPanel;
import de.tu_darmstadt.lichterketten_steuerung.view.MainWindow;
import de.tu_darmstadt.lichterketten_steuerung.view.gui_components.StringLightListPanel;

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

        stringLightList.subscribe(controlPanel);
        stringLightList.subscribe(stringLightListPanel);

        bindMethods();
    }

    /**
     * Binds action listeners to GUI components
     */
    private void bindMethods() {
        stringLightListPanel.btnNewStringLight.addActionListener( e -> {
            stringLightList.addStringLight(mainWindow.getAreaName("Enter Area", "Name:"));
        });

        controlPanel.getAreaSelector().addActionListener( e -> {
            onAreaSelect();
        });

        controlPanel.getLightIdSelector().addActionListener( e -> {
            stringLightList.setSelectedStringLight((String) controlPanel.getLightIdSelector().getSelectedItem());
            stringLightList.notifyObservers();
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


        stringLightList.notifyObservers();
    }
}
