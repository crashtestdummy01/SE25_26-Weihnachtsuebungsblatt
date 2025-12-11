package de.tu_darmstadt.lichterketten_steuerung.view.gui_components.builder;

import de.tu_darmstadt.lichterketten_steuerung.models.StringLight;
import de.tu_darmstadt.lichterketten_steuerung.view.gui_components.StallStringLightWidget;
import de.tu_darmstadt.lichterketten_steuerung.view.gui_components.TreeStringLightWidget;


public class StallStringLightFactory implements StringLightBuilder{

    @Override
    public StringLightDisplay getStringLightWidget(StringLight stringLight) {
        return new StallStringLightWidget(stringLight.id(), stringLight.isOn());
    }
}
