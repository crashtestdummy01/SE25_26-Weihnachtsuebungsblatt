package de.tu_darmstadt.lichterketten_steuerung.view.gui_components.factories;


import de.tu_darmstadt.lichterketten_steuerung.models.StringLight;
import de.tu_darmstadt.lichterketten_steuerung.view.gui_components.stringlightwidgets.Widget;
import de.tu_darmstadt.lichterketten_steuerung.view.gui_components.stringlightwidgets.StringLightWidget;

public class DefaultStringLightFactory implements StringLightFactory {

    @Override
    public Widget getStringLightWidget(StringLight stringLight) {
        return new StringLightWidget(stringLight.id(), stringLight.isOn());
    }
}
