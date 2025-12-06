package de.tu_darmstadt.lichterketten_steuerung.view;

import de.tu_darmstadt.lichterketten_steuerung.controllers.StringLightList;

import javax.swing.*;
import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.Calendar;
import java.util.Date;

public class MacroEditor {

    private final JDialog dialog;
    private JSpinner timeSelector;
    private JButton btnSave, btnDiscard;
    private Controller controller;
    private ControlPanel controlPanel;

    public MacroEditor(JFrame owner, StringLightList controller) {
        dialog = new JDialog(owner, "Macro Editor", false);
        dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);

        dialog.setLayout(new BorderLayout());

        this.controller = controller;

        JPanel timeRow = new JPanel(new FlowLayout(FlowLayout.LEFT));
        timeRow.add(new JLabel("Select time:"));
        timeSelector = createTimeSpinner();
        timeRow.add(timeSelector);

        dialog.add(timeRow, BorderLayout.NORTH);

        controlPanel = new ControlPanel();
        dialog.add(controlPanel, BorderLayout.CENTER);

        JComponent buttonPanel = createButtonPanel();
        dialog.add(buttonPanel, BorderLayout.SOUTH);

        dialog.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosed(WindowEvent e) {
                onDestroy();
            }
        });

        dialog.pack();
        dialog.setLocationRelativeTo(owner);
    }

    private void onDestroy(){
        controller.unsubscribe(controlPanel);
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
        controller.subscribe(controlPanel);
        dialog.setVisible(true);
    }

    public void hide() {
        dialog.dispose();
    }

    // --- Getters for Controller Binding (Updated) ---
    public JButton getBtnSave() { return btnSave; }
    public JButton getBtnDiscard() { return btnDiscard; }
}