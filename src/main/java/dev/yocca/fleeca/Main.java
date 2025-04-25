package dev.yocca.fleeca;

import javax.swing.*;

import dev.yocca.fleeca.gui.FleecaApp;

public class Main {
    public static void main(String[] args) {
        try {
            com.formdev.flatlaf.FlatDarkLaf.setup();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Failed to initialize FlatLaf", "Error", JOptionPane.ERROR_MESSAGE);
            System.exit(1);
        }
        SwingUtilities.invokeLater(FleecaApp::new);
    }
}