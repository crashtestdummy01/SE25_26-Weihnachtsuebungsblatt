package de.tu_darmstadt.lichterketten_steuerung.view.gui_components;

import de.tu_darmstadt.lichterketten_steuerung.controllers.Observable;

public interface Observer {
    public void update(Observable controller);
}
