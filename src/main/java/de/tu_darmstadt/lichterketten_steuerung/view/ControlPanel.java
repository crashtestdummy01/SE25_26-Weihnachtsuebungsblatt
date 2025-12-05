package de.tu_darmstadt.lichterketten_steuerung.view;

import de.tu_darmstadt.lichterketten_steuerung.controllers.StringLightList;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ControlPanel extends JPanel implements Observer {

    public JComboBox<String> areaSelector;
    public JComboBox<String> lightIdSelector;

    private ButtonGroup powerGroup;
    public JRadioButton rbOn;
    public JRadioButton rbOff;

    public JButton btnColor;

    public ControlPanel() {
        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        JPanel selectionRow = new JPanel(new FlowLayout(FlowLayout.LEFT));
        areaSelector = new JComboBox<>(new String[]{"--None--"});
        lightIdSelector = new JComboBox<>(new String[]{"--None--"});

        selectionRow.add(new JLabel("Target Area:"));
        selectionRow.add(areaSelector);
        selectionRow.add(new JLabel("Target ID:"));
        selectionRow.add(lightIdSelector);

        JPanel actionRow = new JPanel(new FlowLayout(FlowLayout.LEFT));

        // 1. Radio Buttons (ON/OFF)
        rbOn = new JRadioButton("ON");
        rbOff = new JRadioButton("OFF");
        rbOff.setSelected(true); // Default selection

        powerGroup = new ButtonGroup();
        powerGroup.add(rbOn);
        powerGroup.add(rbOff);

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
        actionRow.add(Box.createHorizontalStrut(20));
        actionRow.add(btnColor);

        // Add rows to the main panel
        add(selectionRow);
        add(actionRow);
    }

    @Override
    public void update(StringLightList lightList) {
        try {
            StringLightList stringLightList = (StringLightList) lightList;
            int index = areaSelector.getSelectedIndex();
            areaSelector.removeAllItems();
            areaSelector.addItem("--None--");
            for (String area : stringLightList.getAreas()) {
                areaSelector.addItem(area);
            }
            areaSelector.setSelectedIndex(index);
            areaSelector.updateUI();

            lightIdSelector.removeAllItems();
            lightIdSelector.addItem("--None--");
            for (String light : stringLightList.getIDs()) {
                lightIdSelector.addItem(light);
            }

            lightIdSelector.updateUI();
        }catch (Exception ignored) {}
    }
}