package de.tu_darmstadt.lichterketten_steuerung.view;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


public class MainWindow {

    private JPanel stringlightListPanel;
    private JComboBox<String> areaSelector;
    private JComboBox<String> lightIdSelector;
    private JButton btnOn;
    private JButton btnOff;
    private JCheckBox btnMode;
    private JButton btnColor;
    private JTable macroTable;
    private JButton btnOpenMacroEditor;
    private JFrame frame;

    public MainWindow() {
        frame = new JFrame("Lichterketten Steuerung System");

        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        frame.setSize(1000, 700);

        // 1. Create the Four Main Panels
        JComponent topLeft = createLightListPanel();
        JComponent bottomLeft = createControlPanel();
        JComponent topRight = createMacroListPanel();
        JComponent bottomRight = createMacroEditorPanel();

        // 2. Create Split Panes for Resizability
        // Left Column (Top vs Bottom)
        JSplitPane splitLeft = new JSplitPane(JSplitPane.VERTICAL_SPLIT, topLeft, bottomLeft);
        splitLeft.setResizeWeight(0.7); // Top gets 70% space

        // Right Column (Top vs Bottom)
        JSplitPane splitRight = new JSplitPane(JSplitPane.VERTICAL_SPLIT, topRight, bottomRight);
        splitRight.setResizeWeight(0.8); // Top gets 80% space

        // Main Split (Left vs Right)
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

    private JComponent createControlPanel() {
        JPanel panel = new JPanel(new GridLayout(2, 1, 5, 5)); // 2 Rows
        panel.setBorder(BorderFactory.createTitledBorder("Controls"));

        // Row 1: Selection Dropdowns
        JPanel row1 = new JPanel(new FlowLayout(FlowLayout.LEFT));
        areaSelector = new JComboBox<>(new String[]{"-- Select Area --", "Patio", "Garden"});
        lightIdSelector = new JComboBox<>(new String[]{"-- Select Light --", "1", "2", "3"});

        row1.add(new JLabel("Area:"));
        row1.add(areaSelector);
        row1.add(new JLabel("Light ID:"));
        row1.add(lightIdSelector);

        // Row 2: Action Buttons
        JPanel row2 = new JPanel(new FlowLayout(FlowLayout.LEFT));
        btnOn = new JButton("ON");
        btnOff = new JButton("OFF");
        btnMode = new JCheckBox("Blink");
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

        panel.add(btnColor);

        row2.add(btnOn);
        row2.add(btnOff);
        row2.add(btnMode);
        row2.add(btnColor);

        panel.add(row1);
        panel.add(row2);
        return panel;
    }

    private JComponent createMacroListPanel() {
        // Table with "Macro Name" and "Active" columns
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
