package de.tu_darmstadt.lichterketten_steuerung;

import de.tu_darmstadt.lichterketten_steuerung.view.MainWindow;

import javax.swing.*;

public class App {
    public static void main(String[] args) {
        try {
            UIManager.setLookAndFeel("javax.swing.plaf.nimbus.NimbusLookAndFeel");
        } catch (Exception ignored) {}

        SwingUtilities.invokeLater(App::showGUI);
    }

    private static void showGUI() {
        new MainWindow();
    }
}