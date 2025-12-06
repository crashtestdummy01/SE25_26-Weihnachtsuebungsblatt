package de.tu_darmstadt.lichterketten_steuerung.view;

import de.tu_darmstadt.lichterketten_steuerung.controllers.StringLightList;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionListener;

public class StringLightListPanel extends JPanel  implements Observer {

    public JButton btnNewStringLight;
    public JPanel base;

    public StringLightListPanel() {
        base = new JPanel();
        base.setLayout(new BoxLayout(base, BoxLayout.Y_AXIS));
        base.setBorder(new EmptyBorder(5, 5, 5, 5));

        btnNewStringLight = new JButton("+");
        btnNewStringLight.setFont(btnNewStringLight.getFont().deriveFont(Font.BOLD, 16f));
        btnNewStringLight.setAlignmentX(Component.CENTER_ALIGNMENT);

        base.add(btnNewStringLight);
        base.add(Box.createVerticalGlue());

        setLayout(new BorderLayout());
        setBorder(BorderFactory.createTitledBorder("String Lights"));
        JScrollPane scrollPane = new JScrollPane(base);
        scrollPane.getVerticalScrollBar().setUnitIncrement(16);
        this.add(scrollPane, BorderLayout.CENTER);
    }

    @Override
    public void update(Controller lightList) {
        updateUI();
    }
}
