package de.tu_darmstadt.lichterketten_steuerung.view;

import javax.swing.*;
import java.awt.*;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;

public class StringLightWidget extends JPanel {
    private final JLabel infoLabel;
    private final JPanel colorIndicator;
    private final JPanel statusIndicator;

    // Define the size for the little square indicators
    private static final Dimension INDICATOR_SIZE = new Dimension(16, 16);
    private static final Color STATUS_ON_COLOR = Color.YELLOW;
    private static final Color STATUS_OFF_COLOR = Color.BLACK;

    public StringLightWidget(String id, String name, Color initialColor, boolean isOn) {
        // Main widget panel uses BorderLayout to ensure the Info Label gets most of the width
        super(new BorderLayout(5, 0));

        // Ensure fixed height and full width stretch
        setMaximumSize(new Dimension(Integer.MAX_VALUE, 50));
        setPreferredSize(new Dimension(300, 50));
        setBorder(new LineBorder(Color.GRAY, 2));
        setBackground(Color.WHITE);

        // --- Column 1: ID + Name ---
        infoLabel = new JLabel(id + " - " + name);
        infoLabel.setFont(infoLabel.getFont().deriveFont(Font.BOLD, 12f));
        infoLabel.setBorder(new EmptyBorder(0, 10, 0, 0));

        // Add the info label to the West area, taking up the most space
        add(infoLabel, BorderLayout.WEST);

        // --- Columns 2 & 3: Indicators Panel ---
        // Use GridBagLayout for precise 2-row, 3-column control (Labels row + Indicators row)
        JPanel indicatorsPanel = new JPanel(new GridBagLayout());
        indicatorsPanel.setOpaque(false);

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(2, 5, 2, 5); // Padding around cells

        // ------------------------------------
        // --- Row 1: Labels (Color: | Status:) ---
        // ------------------------------------

        // Label for Color (Column 1)
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.anchor = GridBagConstraints.CENTER;
        indicatorsPanel.add(new JLabel("Color:"), gbc);

        // Label for Status (Column 2)
        gbc.gridx = 1;
        indicatorsPanel.add(new JLabel("Status:"), gbc);

        // ------------------------------------
        // --- Row 2: Indicators ---
        // ------------------------------------

        // Column 1, Row 2: Color Indicator (Anchored to Center)
        colorIndicator = new JPanel();
        colorIndicator.setPreferredSize(INDICATOR_SIZE);
        colorIndicator.setBackground(initialColor);
        colorIndicator.setBorder(new LineBorder(Color.DARK_GRAY));

        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.weightx = 1.0; // Crucial: Gives this column horizontal weight
        gbc.anchor = GridBagConstraints.CENTER; // Centers the indicator within the weighted column
        indicatorsPanel.add(colorIndicator, gbc);

        // Column 2, Row 2: Status Indicator
        statusIndicator = new JPanel();
        statusIndicator.setPreferredSize(INDICATOR_SIZE);
        updateStatusIndicator(isOn);
        statusIndicator.setBorder(new LineBorder(Color.DARK_GRAY));

        gbc.gridx = 1;
        gbc.weightx = 0; // Does not take extra space
        gbc.anchor = GridBagConstraints.CENTER;
        indicatorsPanel.add(statusIndicator, gbc);

        // Add the combined indicators panel to the East of the main widget
        add(indicatorsPanel, BorderLayout.EAST);
    }

    // --- Public Setters for Controller use later ---

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
        // Optional tooltips
        statusIndicator.setToolTipText(isOn ? "Status: ON" : "Status: OFF");
    }
}
