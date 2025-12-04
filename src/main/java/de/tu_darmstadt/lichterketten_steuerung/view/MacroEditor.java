package de.tu_darmstadt.lichterketten_steuerung.view;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Calendar;
import java.util.Date;
import java.util.stream.IntStream;

public class MacroEditor {

    private final JDialog dialog;

    // Components exposed for the Controller
    private JComboBox<String> areaSelector;
    private JComboBox<String> lightIdSelector;
    private JSpinner timeSelector;

    // CHANGED: Radio buttons and Checkbox
    private JRadioButton rbOn, rbOff;
    private ButtonGroup powerGroup; // Group to link On/Off radio buttons
    private JCheckBox chkBlink;

    private JButton btnColor;
    private JButton btnSave, btnDiscard;

    /**
     * Creates a non-modal macro editor pop-up window.
     * @param owner The JFrame this dialog belongs to (your main window).
     */
    public MacroEditor(JFrame owner) {
        dialog = new JDialog(owner, "Macro Editor", false);
        dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);

        dialog.setLayout(new BorderLayout());

        JComponent controlPanel = createControlPanel();
        dialog.add(controlPanel, BorderLayout.CENTER);

        JComponent buttonPanel = createButtonPanel();
        dialog.add(buttonPanel, BorderLayout.SOUTH);

        dialog.pack();
        dialog.setLocationRelativeTo(owner);
    }

    private JComponent createControlPanel() {
        // Use a vertical BoxLayout to stack the three rows (Time, Selection, Controls)
        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
        panel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        // --- Row 1: Time Slot Picker ---
        JPanel timeRow = new JPanel(new FlowLayout(FlowLayout.LEFT));
        timeRow.add(new JLabel("Time:"));
        timeSelector = createTimeSpinner();
        timeRow.add(timeSelector);

        // --- Row 2: Selection Dropdowns (Area & Light ID) ---
        JPanel selectionRow = new JPanel(new FlowLayout(FlowLayout.LEFT));
        areaSelector = new JComboBox<>(new String[]{"-- Area --", "Patio", "Garden"});
        lightIdSelector = new JComboBox<>(new String[]{"-- Light ID --", "1", "2", "3"});

        selectionRow.add(new JLabel("Target Area:"));
        selectionRow.add(areaSelector);
        selectionRow.add(new JLabel("Target ID:"));
        selectionRow.add(lightIdSelector);

        // --- Row 3: Control Options (ON/OFF, BLINK, COLOR) ---
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
                        panel.getTopLevelAncestor(),
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
        panel.add(timeRow);
        panel.add(selectionRow);
        panel.add(actionRow);

        return panel;
    }

    // Helper method for the time slot picker (00:00 to 23:30)
    private JComboBox<String> createTimeSlotPicker() {
        String[] times = IntStream.range(0, 24)
                .mapToObj(h -> {
                    String hour = String.format("%02d", h);
                    return new String[]{hour + ":00", hour + ":30"};
                })
                .flatMap(java.util.Arrays::stream)
                .toArray(String[]::new);

        JComboBox<String> selector = new JComboBox<>(times);
        selector.setSelectedItem("18:00");
        return selector;
    }

    private JComponent createButtonPanel() {
        JPanel panel = new JPanel(new FlowLayout(FlowLayout.RIGHT, 10, 10));

        btnSave = new JButton("Save Macro");
        btnDiscard = new JButton("Discard");

        panel.add(btnDiscard);
        panel.add(btnSave);
        return panel;
    }

    private JSpinner createTimeSpinner() {
        // 1. Define the model to handle Date objects
        // Start date (irrelevant), End date (irrelevant), Step (1 minute), Calendar Field (Minute)
        SpinnerDateModel model = new SpinnerDateModel(
            new Date(), null, null, Calendar.MINUTE
        );
        JSpinner spinner = new JSpinner(model);

        // 2. Define the format to display only the time (e.g., HH:mm)
        JSpinner.DateEditor editor = new JSpinner.DateEditor(
            spinner,
            "HH:mm" // 24-hour format
            // OR "hh:mm a" for 12-hour format with AM/PM
        );
        spinner.setEditor(editor);

        // Optional: Set the initial time (e.g., 6:00 PM)
        Calendar cal = Calendar.getInstance();
        cal.set(Calendar.HOUR_OF_DAY, 18);
        cal.set(Calendar.MINUTE, 0);
        spinner.setValue(cal.getTime());

        return spinner;
    }

    // --- Control Methods ---

    public void show() {
        dialog.setVisible(true);
    }

    public void hide() {
        dialog.dispose();
    }

    // --- Getters for Controller Binding (Updated) ---

    public JComboBox<String> getAreaSelector() { return areaSelector; }
    public JComboBox<String> getLightIdSelector() { return lightIdSelector; }
    public JSpinner getTimeSelector() { return timeSelector; }

    public JRadioButton getRbOn() { return rbOn; }
    public JRadioButton getRbOff() { return rbOff; }
    public ButtonGroup getPowerGroup() { return powerGroup; } // Useful for checking selection status
    public JCheckBox getChkBlink() { return chkBlink; }

    public JButton getBtnColor() { return btnColor; }
    public JButton getBtnSave() { return btnSave; }
    public JButton getBtnDiscard() { return btnDiscard; }
}