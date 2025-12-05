package de.tu_darmstadt.lichterketten_steuerung.view;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Calendar;
import java.util.Date;

public class ControlPanel extends JPanel {

    private JComboBox<String> areaSelector;
    private JComboBox<String> lightIdSelector;

    private ButtonGroup powerGroup;
    private JRadioButton rbOn;
    private JRadioButton rbOff;

    private JCheckBox chkBlink;
    private JButton btnColor;

    public ControlPanel() {
        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        JPanel selectionRow = new JPanel(new FlowLayout(FlowLayout.LEFT));
        areaSelector = new JComboBox<>(new String[]{"-- Area --", "Patio", "Garden"});
        lightIdSelector = new JComboBox<>(new String[]{"-- Light ID --", "1", "2", "3"});

        selectionRow.add(new JLabel("Target Area:"));
        selectionRow.add(areaSelector);
        selectionRow.add(new JLabel("Target ID:"));
        selectionRow.add(lightIdSelector);

        JPanel actionRow = new JPanel(new FlowLayout(FlowLayout.LEFT));

        // 1. Radio Buttons (ON/OFF)
        rbOn = new JRadioButton("ON");
        rbOff = new JRadioButton("OFF");
        rbOn.setSelected(true); // Default selection

        powerGroup = new ButtonGroup();
        powerGroup.add(rbOn);
        powerGroup.add(rbOff);

        // 2. Checkbox (BLINK)
        chkBlink = new JCheckBox("Blink");

        // 3. Button (COLOR)
        btnColor = new JButton("Set Color");

        btnColor.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Color initialColor = Color.WHITE;

                // Launch the color chooser dialog
                Color newColor = JColorChooser.showDialog(
                        getTopLevelAncestor(),
                        "Select Light Color",
                        initialColor
                );

                if (newColor != null) {
                    // TODO: Replace this placeholder with your actual model update logic
                    System.out.println("Selected Color: " + newColor);

                    // You would typically store the last chosen color or update the indicator here
                }
            }
        });

        // Add controls to the row
        actionRow.add(new JLabel("Power:"));
        actionRow.add(rbOn);
        actionRow.add(rbOff);
        actionRow.add(Box.createHorizontalStrut(20)); // Spacing
        actionRow.add(chkBlink);
        actionRow.add(Box.createHorizontalStrut(20));
        actionRow.add(btnColor);

        // Add rows to the main panel
        add(selectionRow);
        add(actionRow);
    }
}