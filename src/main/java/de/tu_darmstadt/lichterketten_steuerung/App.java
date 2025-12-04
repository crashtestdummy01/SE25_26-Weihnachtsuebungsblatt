package de.tu_darmstadt.lichterketten_steuerung;

import javax.swing.JFrame;
import javax.swing.SwingUtilities;

public class App {
    public static void main(String[] args) {
        // Swing components should be created on the Event Dispatch Thread
        SwingUtilities.invokeLater(App::createAndShowGUI);
    }

    private static void createAndShowGUI() {
        // Create the window (JFrame)
        JFrame frame = new JFrame("Simple Maven App");

        // Set the operation to close the application when the X is clicked
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // Set the size of the window (width, height)
        frame.setSize(400, 300);

        // Center the window on the screen
        frame.setLocationRelativeTo(null);

        // Display the window
        frame.setVisible(true);
    }
}