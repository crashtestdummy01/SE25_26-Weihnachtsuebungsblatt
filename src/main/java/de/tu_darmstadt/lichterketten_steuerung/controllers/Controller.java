package de.tu_darmstadt.lichterketten_steuerung.controllers;

import de.tu_darmstadt.lichterketten_steuerung.view.gui_components.Observer;

public interface Controller {
    public void subscribe(Observer observer);
    public void unsubscribe(Observer observer);
    public void notifyObservers();
}
