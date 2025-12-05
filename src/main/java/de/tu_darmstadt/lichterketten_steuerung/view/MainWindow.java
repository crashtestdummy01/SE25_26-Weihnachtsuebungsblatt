package de.tu_darmstadt.lichterketten_steuerung.view;

import de.tu_darmstadt.lichterketten_steuerung.controllers.StringLightList;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionListener;
import java.util.concurrent.atomic.AtomicReference;

public class MainWindow implements Observer {

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

        JComponent stringlightListPanel = createLightListPanel();
        controlPanel = new ControlPanel();
        JComponent macroListPanel = createMacroListPanel();
        JComponent editMacroButtonPanel = createMacroEditorPanel();

        JSplitPane splitLeft = new JSplitPane(JSplitPane.VERTICAL_SPLIT, stringlightListPanel, controlPanel);
        splitLeft.setResizeWeight(0.7);

        JSplitPane splitRight = new JSplitPane(JSplitPane.VERTICAL_SPLIT, macroListPanel, editMacroButtonPanel);
        splitRight.setResizeWeight(0.8);

        JSplitPane splitMain = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT, splitLeft, splitRight);
        splitMain.setDividerLocation((int) Math.round(frame.getSize().width * 0.7));

        frame.add(splitMain);
        frame.setLocationRelativeTo(null);

        frame.setVisible(true);
    }

    @Override
    public void update(StringLightList lightList) {

    }

    private JComponent createLightListPanel() {
        stringlightListPanel = new JPanel();
        stringlightListPanel.setLayout(new BoxLayout(stringlightListPanel, BoxLayout.Y_AXIS));
        stringlightListPanel.setBorder(new EmptyBorder(5, 5, 5, 5));

        btnNewStringLight = new JButton("+");
        btnNewStringLight.setFont(btnNewStringLight.getFont().deriveFont(Font.BOLD, 16f));
        stringlightListPanel.add(btnNewStringLight);

        stringlightListPanel.add(Box.createVerticalGlue());

        JPanel panel = new JPanel(new BorderLayout());
        panel.setBorder(BorderFactory.createTitledBorder("String Lights"));
        JScrollPane scrollPane = new JScrollPane(stringlightListPanel);
        scrollPane.getVerticalScrollBar().setUnitIncrement(16);
        panel.add(scrollPane, BorderLayout.CENTER);

        return panel;
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

}
