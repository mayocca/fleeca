package dev.yocca.fleeca.gui.components;


import javax.swing.*;

public class ApplicationPanel extends JPanel {
    public ApplicationPanel() {
        super();
        setup();
    }

    private void setup() {
        this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
    }
}
