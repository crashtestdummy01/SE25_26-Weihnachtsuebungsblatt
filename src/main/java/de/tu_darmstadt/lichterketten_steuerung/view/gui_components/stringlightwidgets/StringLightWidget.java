package de.tu_darmstadt.lichterketten_steuerung.view.gui_components.stringlightwidgets;

import de.tu_darmstadt.lichterketten_steuerung.controllers.StringLightListController;
import de.tu_darmstadt.lichterketten_steuerung.models.StringLight;
import de.tu_darmstadt.lichterketten_steuerung.view.gui_components.Observer;

import javax.swing.*;
import java.awt.*;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;

/**
 * Custom widget for a string light
 */
public class StringLightWidget extends JPanel implements Observer, Widget {
    private final JLabel infoLabel;
    private final JPanel statusIndicator;

    private static final Dimension INDICATOR_SIZE = new Dimension(16, 16);
    private static final Color STATUS_ON_COLOR = Color.YELLOW;
    private static final Color STATUS_OFF_COLOR = Color.BLACK;

    private String id;

    public StringLightWidget(String id, boolean isOn) {
        super(new BorderLayout(5, 0));

        setMaximumSize(new Dimension(Integer.MAX_VALUE, 50));
        setPreferredSize(new Dimension(300, 50));
        setBorder(new LineBorder(Color.GRAY, 2));
        setBackground(Color.WHITE);

        this.id=id;
        infoLabel = new JLabel(id);
        infoLabel.setFont(infoLabel.getFont().deriveFont(Font.BOLD, 12f));
        infoLabel.setBorder(new EmptyBorder(0, 10, 0, 0));


        add(infoLabel, BorderLayout.WEST);


        JPanel indicatorsPanel = new JPanel(new GridBagLayout());
        indicatorsPanel.setOpaque(false);

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(2, 5, 2, 5); // Padding around cells


        gbc.gridx = 0;
        gbc.gridy = 0;
        indicatorsPanel.add(new JLabel("Status:"), gbc);

        statusIndicator = new JPanel();
        statusIndicator.setPreferredSize(INDICATOR_SIZE);
        updateStatusIndicator(isOn);
        statusIndicator.setBorder(new LineBorder(Color.BLACK));

        gbc.gridx = 1;
        gbc.anchor = GridBagConstraints.CENTER;
        indicatorsPanel.add(statusIndicator, gbc);

        add(indicatorsPanel, BorderLayout.EAST);
    }

    public void setStatus(boolean isOn) {
        updateStatusIndicator(isOn);
        statusIndicator.repaint();
    }

    private void updateStatusIndicator(boolean isOn) {
        statusIndicator.setBackground(isOn ? STATUS_ON_COLOR : STATUS_OFF_COLOR);
        statusIndicator.setToolTipText(isOn ? "Status: ON" : "Status: OFF");
    }

    /**
     * Finds the corresponding StringLight instance in the controller's list
     * @param controller    StringLightListController instance containing the model list
     * @return              StringLight instance if found, null if not
     */
    private StringLight extractStringLightModel(Object controller) {
        return ((StringLightListController) controller).getList().stream()
                .filter(stringLight -> stringLight.id().equals(id))
                .findFirst()
                .orElse(null);
    }

    //TODO: Assignment 3: Your code goes here
    @Override
    public void update(StringLightListController context){
        StringLight thisStringLightModel = extractStringLightModel(context);
        if (thisStringLightModel == null) { return;}
        setStatus(thisStringLightModel.isOn());
    }

    public String getId() {
        return id;
    }

    public Component getComponent() {
        return this;
    }
}
