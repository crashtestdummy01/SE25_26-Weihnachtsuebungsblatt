package de.tu_darmstadt.lichterketten_steuerung.view.gui_components;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.util.TreeSet;

public class ControlPanel extends JPanel {

    private JComboBox<String> areaSelector;
    private JComboBox<String> lightIdSelector;
    private JCheckBox chkOnOff;
    private JButton btnRemove;

    public ControlPanel() {
        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        // Create control widgets and add them to the display
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


        actionRow.add(new JLabel("Power:"));
        actionRow.add(chkOnOff);
        actionRow.add(Box.createHorizontalStrut(20)); // Spacing
        actionRow.add(Box.createHorizontalStrut(20));
        actionRow.add(btnRemove);


        add(selectionRow);
        add(actionRow);
    }

    /**
     * Set new options for area and string light selection drop down menus
     * @param options   new options
     * @param dropDown  dropdown selection to update
     */
    private void updateDropDownOptions(TreeSet<String> options, JComboBox<String> dropDown) {
        ActionListener actionListener = removeActionListener(dropDown);
        int selectedIndex = dropDown.getSelectedIndex();

        dropDown.removeAllItems();
        dropDown.addItem("--None--");
        for (String option : options) {
            dropDown.addItem(option);
        }

        try {
            dropDown.setSelectedIndex(selectedIndex);
        }catch(IllegalArgumentException ignored) {}

        dropDown.addActionListener(actionListener);
    }


    //TODO: Aufgabe 3: Your code goes here

    /**
     * Removes the first ActionListener from a combobox and returns it
     * @param dropdown  combobox to remove ActionListener from
     * @return          the removed ActionListener if no listener was removed then null
     */
    public ActionListener removeActionListener(JComboBox<String> dropdown) {
        ActionListener[] listeners = dropdown.getListeners(ActionListener.class);

        if (listeners.length > 0) {
            ActionListener targetListener = listeners[0];
            dropdown.removeActionListener(targetListener);

            return listeners[0];
        }
        return null;
    }

    // Public getters
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

}