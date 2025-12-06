package de.tu_darmstadt.lichterketten_steuerung.view;

import de.tu_darmstadt.lichterketten_steuerung.controllers.StringLightList;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;

public class ControlPanel extends JPanel implements Observer {

    private JComboBox<String> areaSelector;
    private JComboBox<String> lightIdSelector;
    private JCheckBox chkOnOff;
    private JButton btnColor;

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

        chkOnOff = new JCheckBox("ON/OFF");

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
        actionRow.add(chkOnOff);
        actionRow.add(Box.createHorizontalStrut(20)); // Spacing
        actionRow.add(Box.createHorizontalStrut(20));
        actionRow.add(btnColor);

        // Add rows to the main panel
        add(selectionRow);
        add(actionRow);
    }

    public JComboBox<String> getAreaSelector() {
        return areaSelector;
    }

    public JComboBox<String> getLightIdSelector() {
        return lightIdSelector;
    }

    public JCheckBox getChkOnOff() {
        return chkOnOff;
    }

    public JButton getBtnColor() {
        return btnColor;
    }

    @Override
    public void update(Controller lightList) {
        StringLightList stringLightList = (StringLightList) lightList;

        int index = areaSelector.getSelectedIndex();
        areaSelector.removeAllItems();
        areaSelector.addItem("--None--");
        for (String areaName: new TreeSet<>(stringLightList.getAreas())){
            areaSelector.addItem(areaName);
        }
        try {
            areaSelector.setSelectedIndex(index);
        }catch (Exception ignored){}

        index = lightIdSelector.getSelectedIndex();
        lightIdSelector.removeAllItems();
        lightIdSelector.addItem("--None--");
        for (String areaName: new TreeSet<>(stringLightList.getIDsInArea(stringLightList.selectedArea))){
            lightIdSelector.addItem(areaName);
        }
        try {
            lightIdSelector.setSelectedIndex(index);
        }catch (Exception ignored){}
    }

}