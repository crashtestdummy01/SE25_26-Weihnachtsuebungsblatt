package de.tu_darmstadt.lichterketten_steuerung.view.gui_components.factories;

import de.tu_darmstadt.lichterketten_steuerung.models.StringLight;
import de.tu_darmstadt.lichterketten_steuerung.view.gui_components.stringlightwidgets.Widget;

public interface StringLightFactory {
    public Widget getStringLightWidget(StringLight stringLight);
}
