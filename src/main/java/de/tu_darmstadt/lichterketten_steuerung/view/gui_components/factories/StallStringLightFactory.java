package de.tu_darmstadt.lichterketten_steuerung.view.gui_components.factories;


import de.tu_darmstadt.lichterketten_steuerung.models.StringLight;
import de.tu_darmstadt.lichterketten_steuerung.view.gui_components.stringlightwidgets.Widget;
import de.tu_darmstadt.lichterketten_steuerung.view.gui_components.stringlightwidgets.StallStringLightWidget;

public class StallStringLightFactory implements StringLightFactory {

    @Override
    public Widget getStringLightWidget(StringLight stringLight) {
        return new StallStringLightWidget(stringLight.id(), stringLight.isOn());
    }
}
