package dev.yocca.fleeca;

import javax.swing.*;

import dev.yocca.fleeca.gui.FleecaApp;

public class Main {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(FleecaApp::new);
    }
}