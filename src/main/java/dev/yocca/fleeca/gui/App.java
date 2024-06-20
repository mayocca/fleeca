package dev.yocca.fleeca.gui;

import dev.yocca.fleeca.gui.components.navigation.Sidebar;

import javax.swing.*;

public class App extends JFrame {

    private dev.yocca.fleeca.gui.accounts.AccountsPanel accountsPanel;
    private JPanel mainPanel;
    private JScrollPane scrollPanel;
    private JSplitPane splitPanel;
    private JPanel userAccountPanel;
    private JLabel userNameLabel;
    private Sidebar sidebar;

    public App() {
        super();
        setup();
    }

    private void setup() {
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setTitle("Fleeca Bank");

        splitPanel = new JSplitPane();
        userAccountPanel = new JPanel();
        userNameLabel = new JLabel();
        scrollPanel = new JScrollPane();
        mainPanel = new JPanel();
        accountsPanel = new dev.yocca.fleeca.gui.accounts.AccountsPanel();
        sidebar = new Sidebar();

        splitPanel.setEnabled(false);

        GroupLayout userAccountPanelLayout = new GroupLayout(userAccountPanel);
        userAccountPanel.setLayout(userAccountPanelLayout);
        userAccountPanelLayout.setHorizontalGroup(
                userAccountPanelLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
                        .addGroup(userAccountPanelLayout.createSequentialGroup()
                                .addContainerGap()
                                .addGroup(userAccountPanelLayout
                                        .createParallelGroup(GroupLayout.Alignment.LEADING)
                                        .addGroup(userAccountPanelLayout.createSequentialGroup()
                                                .addComponent(userNameLabel)
                                                .addGap(0, 0, Short.MAX_VALUE))
                                        )));
        userAccountPanelLayout.setVerticalGroup(
                userAccountPanelLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
                        .addGroup(userAccountPanelLayout.createSequentialGroup()
                                .addContainerGap()
                                .addComponent(userNameLabel)
                                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED) ));


        splitPanel.setLeftComponent(sidebar);

        mainPanel.setLayout(new java.awt.CardLayout());
        mainPanel.add(accountsPanel, "card2");

        scrollPanel.setViewportView(mainPanel);

        splitPanel.setRightComponent(scrollPanel);

        GroupLayout layout = new GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
                layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                        .addComponent(splitPanel));
        layout.setVerticalGroup(
                layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                        .addComponent(splitPanel));

        pack();
        setLocationRelativeTo(null);
        setVisible(true);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(App::new);
    }
}
