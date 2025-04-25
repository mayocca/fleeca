package dev.yocca.fleeca.gui;

import dev.yocca.fleeca.gui.accounts.AccountsPanel;
import dev.yocca.fleeca.gui.components.navigation.Sidebar;

import javax.swing.*;

public class FleecaApp extends JFrame {

    /**
     * The name of the application. Will be displayed in the window title.
     */
    private static final String APP_NAME = "Fleeca Bank";

    private AccountsPanel accountsPanel;
    private JPanel mainPanel;
    private JScrollPane scrollPanel;
    private JSplitPane splitPanel;
    private JPanel userAccountPanel;
    private JLabel userNameLabel;
    private Sidebar sidebar;

    public FleecaApp() {
        super();
        setup();
    }

    private void setup() {
        this.configureWindow();
        this.configureComponents();

        this.pack();
        this.setVisible(true);
    }

    /**
     * Configures the window properties.
     */
    private void configureWindow() {
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setTitle(APP_NAME);
        this.setLocationRelativeTo(null);
    }

    /**
     * Initializes and configures the window components.
     */
    private void configureComponents() {
        splitPanel = new JSplitPane();
        userAccountPanel = new JPanel();
        userNameLabel = new JLabel();
        scrollPanel = new JScrollPane();
        mainPanel = new JPanel();
        accountsPanel = new AccountsPanel();
        sidebar = new Sidebar();

        splitPanel.setEnabled(false);

        GroupLayout userAccountPanelLayout = new GroupLayout(userAccountPanel);
        userAccountPanel.setLayout(userAccountPanelLayout);
        userAccountPanelLayout.setHorizontalGroup(
                userAccountPanelLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
                        .addGroup(userAccountPanelLayout.createSequentialGroup()
                                .addContainerGap()
                                .addGroup(userAccountPanelLayout
                                        .createParallelGroup(
                                                GroupLayout.Alignment.LEADING)
                                        .addGroup(userAccountPanelLayout
                                                .createSequentialGroup()
                                                .addComponent(userNameLabel)
                                                .addGap(0, 0, Short.MAX_VALUE)))));
        userAccountPanelLayout.setVerticalGroup(
                userAccountPanelLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
                        .addGroup(userAccountPanelLayout.createSequentialGroup()
                                .addContainerGap()
                                .addComponent(userNameLabel)
                                .addPreferredGap(
                                        LayoutStyle.ComponentPlacement.RELATED)));

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
    }
}
