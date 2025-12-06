package de.tu_darmstadt.lichterketten_steuerung.view;

import de.tu_darmstadt.lichterketten_steuerung.view.gui_components.ControlPanel;
import de.tu_darmstadt.lichterketten_steuerung.view.gui_components.StringLightListPanel;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.util.concurrent.atomic.AtomicReference;

public class MainWindow {

    public ControlPanel controlPanel;
    public JFrame frame;
    public StringLightListPanel stringlightListPanel;

    public MainWindow() {
        frame = new JFrame("Lichterketten Steuerung System");

        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        frame.setSize(1000, 700);

        // create widgets and add them to the display
        stringlightListPanel = new StringLightListPanel();
        controlPanel = new ControlPanel();

        JSplitPane mainSplit = new JSplitPane(JSplitPane.VERTICAL_SPLIT, stringlightListPanel, controlPanel);
        mainSplit.setDividerLocation((int) Math.round(frame.getHeight() * 0.75));

        frame.add(mainSplit);
        frame.setLocationRelativeTo(null);

        frame.setVisible(true);
    }

    /**
     * Creates a dialogue box to ask for user input
     * @param   title   title of the dialogue box
     * @param   message messege displayed inside the dialogue box
     * @return          the user input as String
     */
    public String getAreaName(String title, String message) {
        AtomicReference<String> inputValue = new AtomicReference<>();


        JDialog dialog = new JDialog(frame, title, true);
        dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);

        // Set up dialogue box
        JPanel contentPanel = new JPanel(new BorderLayout(10, 10));
        contentPanel.setBorder(BorderFactory.createEmptyBorder(15, 15, 10, 15));


        JTextField inputField = new JTextField(20);

        contentPanel.add(new JLabel(message), BorderLayout.NORTH);
        contentPanel.add(inputField, BorderLayout.CENTER);


        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT, 10, 0));
        JButton acceptButton = new JButton("Accept");
        JButton cancelButton = new JButton("Cancel");

        buttonPanel.add(cancelButton);
        buttonPanel.add(acceptButton);

        contentPanel.add(buttonPanel, BorderLayout.SOUTH);


        dialog.setContentPane(contentPanel);


        // Bind action listeners
        ActionListener acceptListener = e -> {
            inputValue.set(inputField.getText().trim());
            if (inputValue.get().isEmpty()) {
                JOptionPane.showMessageDialog(dialog, "Please enter an area.", "Input Required", JOptionPane.WARNING_MESSAGE);
                return;
            }
            dialog.dispose();
        };

        ActionListener cancelListener = e -> {
            if (inputValue.get().isEmpty()) {
                JOptionPane.showMessageDialog(dialog, "Please enter an area.", "Input Required", JOptionPane.WARNING_MESSAGE);
                return;
            }
            dialog.dispose();
        };

        acceptButton.addActionListener(acceptListener);
        cancelButton.addActionListener(cancelListener);
        inputField.addActionListener(acceptListener);

        // Display dialogue
        dialog.pack();
        dialog.setResizable(false);
        dialog.setLocationRelativeTo(frame);
        dialog.setVisible(true);

        return inputValue.get();
    }

}
