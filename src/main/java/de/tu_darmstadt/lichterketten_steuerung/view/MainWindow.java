package de.tu_darmstadt.lichterketten_steuerung.view;

import de.tu_darmstadt.lichterketten_steuerung.controllers.StringLightList;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionListener;
import java.util.concurrent.atomic.AtomicReference;

public class MainWindow {

    public JPanel stringlightListPanel;
    public JTable macroTable;
    public JButton btnOpenMacroEditor;
    public JButton btnNewStringLight;
    public ControlPanel controlPanel;

    public JFrame frame;

    public MainWindow() {
        frame = new JFrame("Lichterketten Steuerung System");

        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        frame.setSize(1000, 700);

        StringLightListPanel stringlightListPanel = new StringLightListPanel();
        controlPanel = new ControlPanel();

        StringLightList stringLightList = new StringLightList(stringlightListPanel.base);
        stringLightList.subscribe(controlPanel);

        JSplitPane mainSplit = new JSplitPane(JSplitPane.VERTICAL_SPLIT, stringlightListPanel, controlPanel);
        mainSplit.setDividerLocation((int) Math.round(frame.getHeight() * 0.75));

        frame.add(mainSplit);
        frame.setLocationRelativeTo(null);

        frame.setVisible(true);

        stringlightListPanel.btnNewStringLight.addActionListener( e -> {
           stringLightList.onAddButton(getAreaName("Enter Area", "Name:"));
        });

        controlPanel.getAreaSelector().addActionListener( e -> {
            stringLightList.selectedArea = (String) controlPanel.getAreaSelector().getSelectedItem();
            if(stringLightList.selectedArea == null){stringLightList.selectedArea = "--None--";}
            if(stringLightList.selectedArea.equals("--None--")){stringLightList.selectedArea = null;}
            stringLightList.setSelectedStringLight(null);
            controlPanel.getLightIdSelector().setSelectedIndex(0);
            stringLightList.notifyObservers();
        });

        controlPanel.getLightIdSelector().addActionListener( e -> {
            stringLightList.setSelectedStringLight((String) controlPanel.getLightIdSelector().getSelectedItem());
            stringLightList.notifyObservers();
        });

        controlPanel.getChkOnOff().addActionListener( e -> {
           stringLightList.onLightSwitch(controlPanel.getChkOnOff().isSelected());
        });
    }

    private void bindMethods() {

    }

    public String getAreaName(String title, String message) {
        // Reset the value before starting the new dialog
        AtomicReference<String> inputValue = new AtomicReference<>();

        // JDialog is the pop-up container. Set it to MODAL.
        JDialog dialog = new JDialog(frame, title, true);
        dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);

        // Use a BorderLayout for structure
        JPanel contentPanel = new JPanel(new BorderLayout(10, 10));
        contentPanel.setBorder(BorderFactory.createEmptyBorder(15, 15, 10, 15));

        // Text field for user input
        JTextField inputField = new JTextField(20);

        // Add message label and input field
        contentPanel.add(new JLabel(message), BorderLayout.NORTH);
        contentPanel.add(inputField, BorderLayout.CENTER);

        // --- Buttons Panel ---
        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT, 10, 0));
        JButton acceptButton = new JButton("Accept");
        JButton cancelButton = new JButton("Cancel");

        buttonPanel.add(cancelButton);
        buttonPanel.add(acceptButton);

        contentPanel.add(buttonPanel, BorderLayout.SOUTH);

        dialog.setContentPane(contentPanel);

        // --- Action Listeners ---

        // 1. Accept Button Listener
        ActionListener acceptListener = e -> {
            inputValue.set(inputField.getText().trim());
            if (inputValue.get().isEmpty()) {
                JOptionPane.showMessageDialog(dialog, "Please enter an area.", "Input Required", JOptionPane.WARNING_MESSAGE);
                return; // Do not close the dialog
            }
            dialog.dispose(); // Close the dialog
        };

        // 2. Cancel Button Listener
        ActionListener cancelListener = e -> {
            // inputValue remains null
            dialog.dispose(); // Close the dialog
        };

        acceptButton.addActionListener(acceptListener);
        cancelButton.addActionListener(cancelListener);
        inputField.addActionListener(acceptListener); // Allow pressing Enter to accept

        // --- Display Dialog ---
        dialog.pack();
        dialog.setResizable(false);
        dialog.setLocationRelativeTo(frame);
        dialog.setVisible(true); // This call BLOCKS until dialog.dispose() is called

        // Once the dialog is disposed, execution continues here, returning the result
        return inputValue.get();
    }

    private JComponent createMacroListPanel() {
        String[] columns = {"Macro Name", "Is Active"};
        Object[][] placeholderData = {{"Sunset Mode", "YES"}, {"Party Mode", "NO"}};

        DefaultTableModel model = new DefaultTableModel(placeholderData, columns);
        macroTable = new JTable(model);

        JPanel panel = new JPanel(new BorderLayout());
        panel.setBorder(BorderFactory.createTitledBorder("Macros"));
        panel.add(new JScrollPane(macroTable), BorderLayout.CENTER);
        return panel;
    }

    private JComponent createMacroEditorPanel() {
        JPanel panel = new JPanel(new GridBagLayout()); // Centers component
        panel.setBorder(BorderFactory.createTitledBorder("Editor"));

        btnOpenMacroEditor = new JButton("Open Macro Editor");
        btnOpenMacroEditor.setPreferredSize(new Dimension(150, 40));

        panel.add(btnOpenMacroEditor);

        return panel;
    }

}
