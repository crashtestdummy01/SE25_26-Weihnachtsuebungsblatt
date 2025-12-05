package de.tu_darmstadt.lichterketten_steuerung.view;

import javax.swing.*;
import java.awt.*;
import java.util.Calendar;
import java.util.Date;

public class MacroEditor {

    private final JDialog dialog;
    private JSpinner timeSelector;
    private JButton btnSave, btnDiscard;

    public MacroEditor(JFrame owner) {
        dialog = new JDialog(owner, "Macro Editor", false);
        dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);

        dialog.setLayout(new BorderLayout());

        JPanel timeRow = new JPanel(new FlowLayout(FlowLayout.LEFT));
        timeRow.add(new JLabel("Select time:"));
        timeSelector = createTimeSpinner();
        timeRow.add(timeSelector);

        dialog.add(timeRow, BorderLayout.NORTH);

        JComponent controlPanel = new ControlPanel();
        dialog.add(controlPanel, BorderLayout.CENTER);

        JComponent buttonPanel = createButtonPanel();
        dialog.add(buttonPanel, BorderLayout.SOUTH);

        dialog.pack();
        dialog.setLocationRelativeTo(owner);
    }

    private JSpinner createTimeSpinner() {
        SpinnerDateModel model = new SpinnerDateModel(
                new Date(), null, null, Calendar.MINUTE
        );

        JSpinner spinner = new JSpinner(model);
        JSpinner.DateEditor editor = new JSpinner.DateEditor(spinner, "HH:mm");
        spinner.setEditor(editor);

        spinner.setValue(Calendar.getInstance().getTime());

        return spinner;
    }

    private JComponent createButtonPanel() {
        JPanel panel = new JPanel(new FlowLayout(FlowLayout.RIGHT, 10, 10));

        btnSave = new JButton("Save");
        btnDiscard = new JButton("Discard");

        panel.add(btnSave);
        panel.add(btnDiscard);
        return panel;
    }

    public void show() {
        dialog.setVisible(true);
    }

    public void hide() {
        dialog.dispose();
    }

    // --- Getters for Controller Binding (Updated) ---
    public JButton getBtnSave() { return btnSave; }
    public JButton getBtnDiscard() { return btnDiscard; }
}