package de.tu_darmstadt.lichterketten_steuerung.view.gui_components.builder;

import de.tu_darmstadt.lichterketten_steuerung.models.StringLight;

public interface StringLightBuilder {
    public StringLightDisplay getStringLightWidget(StringLight stringLight);
}
