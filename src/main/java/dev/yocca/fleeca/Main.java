package dev.yocca.fleeca;

import javax.swing.*;

import com.formdev.flatlaf.FlatDarculaLaf;

import dev.yocca.fleeca.gui.App;

public class Main {
    public static void main(String[] args) {
        try {
            UIManager.setLookAndFeel(new FlatDarculaLaf());
        } catch (UnsupportedLookAndFeelException e) {
            // Do nothing
        }
        SwingUtilities.invokeLater(App::new);
    }
}