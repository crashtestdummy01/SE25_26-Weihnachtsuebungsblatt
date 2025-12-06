package de.tu_darmstadt.lichterketten_steuerung.view.gui_components;

import de.tu_darmstadt.lichterketten_steuerung.controllers.Controller;

public interface Observer {
    public void update(Controller controller);
}
