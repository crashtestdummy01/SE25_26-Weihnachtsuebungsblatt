package de.tu_darmstadt.lichterketten_steuerung.models;

import de.tu_darmstadt.lichterketten_steuerung.controllers.StringLightList;

import java.awt.*;

public record StringLight(String id, boolean isOn, Mode mode, Color color, String area) {
    public StringLight{
        if (area == null || area.isEmpty()) {
            int index = id.lastIndexOf(':');
            area = id.substring(0, index);
        }
    }
    public enum  Mode {
        SOLID,
        BLINKING
    }
}
