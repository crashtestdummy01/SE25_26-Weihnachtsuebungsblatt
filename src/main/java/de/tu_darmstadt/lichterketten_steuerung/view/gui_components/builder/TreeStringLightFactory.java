package de.tu_darmstadt.lichterketten_steuerung.view.gui_components.builder;

import de.tu_darmstadt.lichterketten_steuerung.models.StringLight;
import de.tu_darmstadt.lichterketten_steuerung.view.gui_components.TreeStringLightWidget;


public class TreeStringLightFactory implements StringLightBuilder{

    @Override
    public StringLightProduct getStringLightWidget(StringLight stringLight) {
        return new TreeStringLightWidget(stringLight.id(), stringLight.isOn());
    }
}
