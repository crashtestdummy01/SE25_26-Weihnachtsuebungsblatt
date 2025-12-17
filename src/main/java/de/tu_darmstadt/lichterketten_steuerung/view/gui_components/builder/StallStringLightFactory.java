package de.tu_darmstadt.lichterketten_steuerung.view.gui_components.builder;


import de.tu_darmstadt.lichterketten_steuerung.models.StringLight;
import de.tu_darmstadt.lichterketten_steuerung.view.gui_components.stringlightwidgets.Product;
import de.tu_darmstadt.lichterketten_steuerung.view.gui_components.stringlightwidgets.StallStringLightWidget;

public class StallStringLightFactory implements StringLightBuilder{

    @Override
    public Product getStringLightWidget(StringLight stringLight) {
        return new StallStringLightWidget(stringLight.id(), stringLight.isOn());
    }
}
