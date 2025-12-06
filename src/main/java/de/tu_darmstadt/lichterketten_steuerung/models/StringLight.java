package de.tu_darmstadt.lichterketten_steuerung.models;

import java.awt.*;

/**
 * Mutable record to hold information about each string light in the system
 */
public class  StringLight{

    private String id;
    private boolean isOn;
    private Mode mode;
    private Color color;
    private String area;

    public StringLight(String id, boolean isOn, Mode mode, Color color, String area) {
        if (area == null || area.isEmpty()) {
            int index = id.lastIndexOf(':');
            area = id.substring(0, index);
        }

        this.id =  id;
        this.isOn = isOn;
        this.mode = mode;
        this.color = color;
        this.area = area;
    }

    public boolean isOn() {
        return isOn;
    }

    public Mode mode() {
        return mode;
    }

    public Color color() {
        return color;
    }

    public String area() {
        return area;
    }

    public String id() {
        return id;
    }

    public void setOn(boolean on) {
        isOn = on;
    }

    public void setMode(Mode mode) {
        this.mode = mode;
    }

    public void setColor(Color color) {
        this.color = color;
    }

    public enum  Mode {
        SOLID,
        BLINKING
    }
}
