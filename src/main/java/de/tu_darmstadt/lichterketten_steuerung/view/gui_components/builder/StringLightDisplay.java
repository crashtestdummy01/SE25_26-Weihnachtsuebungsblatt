package de.tu_darmstadt.lichterketten_steuerung.view.gui_components.builder;

import de.tu_darmstadt.lichterketten_steuerung.view.gui_components.Observer;

import java.awt.*;

public interface StringLightDisplay extends Observer {
    public String getId();
    public void setStatus(boolean isOn);
    public Component getComponent();
}
