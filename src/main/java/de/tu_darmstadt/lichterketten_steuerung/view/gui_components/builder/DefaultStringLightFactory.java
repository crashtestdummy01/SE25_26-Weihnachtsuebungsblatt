package de.tu_darmstadt.lichterketten_steuerung.view.gui_components.builder;

import de.tu_darmstadt.lichterketten_steuerung.models.StringLight;
import de.tu_darmstadt.lichterketten_steuerung.view.gui_components.StringLightWidget;


public class DefaultStringLightFactory implements StringLightBuilder{

    @Override
    public StringLightDisplay getStringLightWidget(StringLight stringLight) {
        return new StringLightWidget(stringLight.id(), stringLight.isOn());
    }
}
