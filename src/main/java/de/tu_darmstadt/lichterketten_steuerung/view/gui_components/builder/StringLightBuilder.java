package de.tu_darmstadt.lichterketten_steuerung.view.gui_components.builder;

import de.tu_darmstadt.lichterketten_steuerung.models.StringLight;
import de.tu_darmstadt.lichterketten_steuerung.view.gui_components.stringlightwidgets.Product;

public interface StringLightBuilder {
    public Product getStringLightWidget(StringLight stringLight);
}
