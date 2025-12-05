package de.tu_darmstadt.lichterketten_steuerung.controllers;


import de.tu_darmstadt.lichterketten_steuerung.view.MainWindow;
import java.awt.*;

public class MainWindowController {

    private MainWindow mainWindow;

    public MainWindowController(MainWindow mainWindow) {
        this.mainWindow = mainWindow;

        StringLightList stringLightList = new StringLightList(this.mainWindow.stringlightListPanel);
        this.mainWindow.btnNewStringLight.addActionListener(e -> {
            String area = mainWindow.getAreaName("Enter Area", "Area name:");
            stringLightList.addStringLight(area);
        });
    }
}
