package de.tu_darmstadt.lichterketten_steuerung.view;

import javax.swing.*;
import java.awt.*;
import java.util.stream.IntStream;

public class MacroEditor {

    private final JDialog dialog;

    // Components exposed for the Controller
    private JComboBox<String> areaSelector;
    private JComboBox<String> lightIdSelector;
    private JComboBox<String> timeSelector; // For time slot selection
    private JButton btnOn, btnOff, btnMode, btnColor;
    private JButton btnSave, btnDiscard;

    /**
     * Creates a non-modal macro editor pop-up window.
     *
     * @param owner The JFrame this dialog belongs to (your main window).
     */
    public MacroEditor(JFrame owner) {
        // JDialog is the pop-up container
        dialog = new JDialog(owner, "Macro Editor", false); // 'false' means non-modal
        dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE); // Close only the dialog

        // Use BorderLayout for the main structure (Control Panel + Buttons)
        dialog.setLayout(new BorderLayout());

        // 1. Create the Main Control Panel (Top)
        JComponent controlPanel = createControlPanel();
        dialog.add(controlPanel, BorderLayout.CENTER);

        // 2. Create the Action Buttons Panel (Bottom)
        JComponent buttonPanel = createButtonPanel();
        dialog.add(buttonPanel, BorderLayout.SOUTH);

        dialog.pack(); // Size the dialog to fit components
        dialog.setLocationRelativeTo(owner);
    }

    private JComponent createControlPanel() {
        // Use a vertical BoxLayout to stack the three rows (Time, Selection, Buttons)
        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
        panel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        // --- Row 1: Time Slot Picker ---
        JPanel timeRow = new JPanel(new FlowLayout(FlowLayout.LEFT));
        timeRow.add(new JLabel("Run Time:"));
        timeSelector = createTimeSlotPicker();
        timeRow.add(timeSelector);

        // --- Row 2: Selection Dropdowns (Area & Light ID) ---
        JPanel selectionRow = new JPanel(new FlowLayout(FlowLayout.LEFT));
        areaSelector = new JComboBox<>(new String[]{"-- Area --", "Patio", "Garden"});
        lightIdSelector = new JComboBox<>(new String[]{"-- Light ID --", "1", "2", "3"});

        selectionRow.add(new JLabel("Target Area:"));
        selectionRow.add(areaSelector);
        selectionRow.add(new JLabel("Target ID:"));
        selectionRow.add(lightIdSelector);

        // --- Row 3: Action Buttons (ON/OFF/MODE/COLOR) ---
        JPanel actionRow = new JPanel(new FlowLayout(FlowLayout.LEFT));
        btnOn = new JButton("ON");
        btnOff = new JButton("OFF");
        btnMode = new JButton("Set Mode");
        btnColor = new JButton("Set Color");

        actionRow.add(btnOn);
        actionRow.add(btnOff);
        actionRow.add(btnMode);
        actionRow.add(btnColor);

        // Add rows to the main panel
        panel.add(timeRow);
        panel.add(selectionRow);
        panel.add(actionRow);

        return panel;
    }

    // Helper method for the time slot picker (00:00 to 23:30)
    private JComboBox<String> createTimeSlotPicker() {
        String[] times = IntStream.range(0, 24) // 0 to 23 hours
                .mapToObj(h -> {
                    String hour = String.format("%02d", h);
                    return new String[]{hour + ":00", hour + ":30"};
                })
                .flatMap(java.util.Arrays::stream)
                .toArray(String[]::new);

        JComboBox<String> selector = new JComboBox<>(times);
        selector.setSelectedItem("18:00"); // Placeholder default time
        return selector;
    }

    private JComponent createButtonPanel() {
        // Use FlowLayout anchored to the right for "Save/Discard" buttons
        return new JPanel(new FlowLayout(FlowLayout.RIGHT, 10, 10));
    }

    public void show() {
        // Use setVisible to make the pop-up appear
        dialog.setVisible(true);
    }

    public void hide() {
        // Use dispose to close and free resources
        dialog.dispose();
    }
}