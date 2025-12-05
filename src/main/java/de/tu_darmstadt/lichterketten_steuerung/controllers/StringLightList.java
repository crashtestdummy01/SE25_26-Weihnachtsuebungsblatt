package de.tu_darmstadt.lichterketten_steuerung.controllers;

import de.tu_darmstadt.lichterketten_steuerung.models.Area;
import de.tu_darmstadt.lichterketten_steuerung.models.StringLight;
import de.tu_darmstadt.lichterketten_steuerung.view.StringLightWidget;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class StringLightList {
    private JPanel stringlightListPanel;
    private List<StringLight> stringlightList;

    private int idCounter = 100;

    public StringLightList(JPanel stringlightListPanel) {
        this.stringlightListPanel = stringlightListPanel;
        stringlightList = new ArrayList<StringLight>();
    }

    public void addStringLight(String areaName) {
        String id = areaName + ":L-" + idCounter++;
        StringLight stringLight = new StringLight(id, false, StringLight.Mode.SOLID, Color.WHITE, areaName);
        StringLightWidget stringLightWidget = new StringLightWidget(stringLight.id(), stringLight.color(), stringLight.isOn());
        stringlightList.add(stringLight);
        stringlightListPanel.add(stringLightWidget, 0);

        stringlightListPanel.updateUI();

        System.out.println(stringLight.area());

    }
}
