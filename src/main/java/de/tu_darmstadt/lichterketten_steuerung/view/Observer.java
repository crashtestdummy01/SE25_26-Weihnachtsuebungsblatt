package de.tu_darmstadt.lichterketten_steuerung.view;

import de.tu_darmstadt.lichterketten_steuerung.controllers.StringLightList;

public interface Observer {
    public void update(StringLightList lightList);
}
