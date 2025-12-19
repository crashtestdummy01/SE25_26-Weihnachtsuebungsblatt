package de.tu_darmstadt.lichterketten_steuerung.view.gui_components.stringlightwidgets;

import de.tu_darmstadt.lichterketten_steuerung.view.gui_components.Observer;

import java.awt.*;

public interface Widget extends Observer {
    public String getId();
    public void setStatus(boolean isOn);
    public Component getComponent();
}
