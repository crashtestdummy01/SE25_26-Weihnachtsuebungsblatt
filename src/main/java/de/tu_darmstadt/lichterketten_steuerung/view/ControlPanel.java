package de.tu_darmstadt.lichterketten_steuerung.view;

import de.tu_darmstadt.lichterketten_steuerung.controllers.StringLightList;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.util.TreeSet;

public class ControlPanel extends JPanel implements Observer {

    private JComboBox<String> areaSelector;
    private JComboBox<String> lightIdSelector;
    private JCheckBox chkOnOff;
    private JButton btnRemove;

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

        btnRemove = new JButton("Remove String Light");

        // Add controls to the row
        actionRow.add(new JLabel("Power:"));
        actionRow.add(chkOnOff);
        actionRow.add(Box.createHorizontalStrut(20)); // Spacing
        actionRow.add(Box.createHorizontalStrut(20));
        actionRow.add(btnRemove);

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

    public JButton getBtnRemove() {
        return btnRemove;
    }

    @Override
    public void update(Controller lightList) {
        StringLightList stringLightList = (StringLightList) lightList;
        ActionListener areaDropDownActionListener = removeActionListener(areaSelector);
        ActionListener idDropDownActionListener = removeActionListener(lightIdSelector);

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
        System.out.println(lightIdSelector.getSelectedIndex());
        lightIdSelector.removeAllItems();
        lightIdSelector.addItem("--None--");
        for (String areaName: new TreeSet<>(stringLightList.getIDsInArea(stringLightList.selectedArea))){
            lightIdSelector.addItem(areaName);
        }
        try {
            lightIdSelector.setSelectedIndex(index);
        }catch (Exception ignored){}

        areaSelector.addActionListener(areaDropDownActionListener);
        lightIdSelector.addActionListener(idDropDownActionListener);
    }

    public ActionListener removeActionListener(JComboBox<String> dropdown) {
        ActionListener[] listeners = dropdown.getListeners(ActionListener.class);

        if (listeners.length > 0) {
            ActionListener targetListener = listeners[0];
            dropdown.removeActionListener(targetListener);

            return listeners[0];
        }
        return null;
    }

}