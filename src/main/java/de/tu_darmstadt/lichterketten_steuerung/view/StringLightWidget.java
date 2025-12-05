package de.tu_darmstadt.lichterketten_steuerung.view;

import de.tu_darmstadt.lichterketten_steuerung.models.StringLight;

import javax.swing.*;
import java.awt.*;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;

public class StringLightWidget extends JPanel {
    private final JLabel infoLabel;
    private final JPanel colorIndicator;
    private final JPanel statusIndicator;

    private static final Dimension INDICATOR_SIZE = new Dimension(16, 16);
    private static final Color STATUS_ON_COLOR = Color.YELLOW;
    private static final Color STATUS_OFF_COLOR = Color.BLACK;

    public StringLightWidget(String id, Color initialColor, boolean isOn) {
        super(new BorderLayout(5, 0));

        setMaximumSize(new Dimension(Integer.MAX_VALUE, 50));
        setPreferredSize(new Dimension(300, 50));
        setBorder(new LineBorder(Color.GRAY, 2));
        setBackground(Color.WHITE);


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
        gbc.anchor = GridBagConstraints.CENTER;
        indicatorsPanel.add(new JLabel("Color:"), gbc);

        gbc.gridx = 2;
        indicatorsPanel.add(new JLabel("Status:"), gbc);


        colorIndicator = new JPanel();
        colorIndicator.setPreferredSize(INDICATOR_SIZE);
        colorIndicator.setBackground(initialColor);
        colorIndicator.setBorder(new LineBorder(Color.BLACK));

        gbc.gridx = 1;
        gbc.gridy = 0;
        gbc.weightx = 1.0;
        gbc.anchor = GridBagConstraints.CENTER;
        gbc.insets = new Insets(2, 5, 2, 50);
        indicatorsPanel.add(colorIndicator, gbc);
        gbc.insets = new Insets(2, 5, 2, 5);


        statusIndicator = new JPanel();
        statusIndicator.setPreferredSize(INDICATOR_SIZE);
        updateStatusIndicator(isOn);
        statusIndicator.setBorder(new LineBorder(Color.BLACK));

        gbc.gridx = 3;
        gbc.weightx = 0;
        gbc.anchor = GridBagConstraints.CENTER;
        indicatorsPanel.add(statusIndicator, gbc);

        add(indicatorsPanel, BorderLayout.EAST);
    }

    public void setLightColor(Color c) {
        colorIndicator.setBackground(c);
        // Repaint needed for color changes to show immediately
        colorIndicator.repaint();
    }

    public void setStatus(boolean isOn) {
        updateStatusIndicator(isOn);
        statusIndicator.repaint();
    }

    private void updateStatusIndicator(boolean isOn) {
        statusIndicator.setBackground(isOn ? STATUS_ON_COLOR : STATUS_OFF_COLOR);
        statusIndicator.setToolTipText(isOn ? "Status: ON" : "Status: OFF");
    }
}
