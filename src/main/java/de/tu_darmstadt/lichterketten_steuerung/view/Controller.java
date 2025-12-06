package de.tu_darmstadt.lichterketten_steuerung.view;

public interface Controller {
    public void subscribe(Observer observer);
    public void unsubscribe(Observer observer);
    public void notifyObservers();
}
