package de.tu_darmstadt.lichterketten_steuerung.view;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.border.EmptyBorder;
import java.awt.*;

public class MainWindow {

    private JPanel stringlightListPanel;
    private JTable macroTable;
    private JButton btnOpenMacroEditor;
    private JFrame frame;

    public MainWindow() {
        frame = new JFrame("Lichterketten Steuerung System");

        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        frame.setSize(1000, 700);

        JComponent stringlightListPanel = createLightListPanel();
        JComponent controlPanel = new ControlPanel();
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

    private JComponent createLightListPanel() {
        // Use a panel with vertical BoxLayout to stack widgets
        stringlightListPanel = new JPanel();
        stringlightListPanel.setLayout(new BoxLayout(stringlightListPanel, BoxLayout.Y_AXIS));
        // Add some padding around the edges of the list
        stringlightListPanel.setBorder(new EmptyBorder(5, 5, 5, 5));

        // Add placeholder custom widgets
        stringlightListPanel.add(new StringLightWidget("L-101", "Patio Main", Color.RED, false));
        // Add a tiny gap between widgets
        stringlightListPanel.add(Box.createVerticalStrut(5));
        stringlightListPanel.add(new StringLightWidget("L-102", "Garden Path", Color.BLUE, true));
        stringlightListPanel.add(Box.createVerticalStrut(5));
        stringlightListPanel.add(new StringLightWidget("L-103", "Roof Edge", Color.GREEN, true));

        // Add "glue" to push items to the top if the list is short
        stringlightListPanel.add(Box.createVerticalGlue());

        JPanel panel = new JPanel(new BorderLayout());
        panel.setBorder(BorderFactory.createTitledBorder("String Lights"));
        // Wrap the container in a JScrollPane
        JScrollPane scrollPane = new JScrollPane(stringlightListPanel);
        // Increase scrolling speed slightly
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

        btnOpenMacroEditor.addActionListener(e -> {
         MacroEditor editor = new MacroEditor(frame);
         editor.show();
        });

        return panel;
    }
}
