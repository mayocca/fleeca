package dev.yocca.fleeca.gui;

import javax.swing.*;

public class App extends JFrame {
    private JButton accountsButton;
    private dev.yocca.fleeca.gui.accounts.AccountsPanel accountsPanel;
    private JLabel loggedMessageLabel;
    private JButton logoutButton;
    private JPanel mainPanel;
    private JScrollPane scrollPanel;
    private JPanel sideNavigationPanel;
    private JSplitPane splitPanel;
    private JLabel titleLabel;
    private JButton transferButton;
    private JPanel userAccountPanel;
    private JLabel userNameLabel;

    public App() {
        initComponents();
    }

    private void initComponents() {

        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setTitle("Fleeca Bank");

        splitPanel = new JSplitPane();
        sideNavigationPanel = new JPanel();
        transferButton = new JButton();
        accountsButton = new JButton();
        titleLabel = new JLabel();
        userAccountPanel = new JPanel();
        loggedMessageLabel = new JLabel();
        logoutButton = new JButton();
        userNameLabel = new JLabel();
        scrollPanel = new JScrollPane();
        mainPanel = new JPanel();
        accountsPanel = new dev.yocca.fleeca.gui.accounts.AccountsPanel();


        splitPanel.setEnabled(false);

        sideNavigationPanel.setBorder(BorderFactory.createEtchedBorder());

        transferButton.setText("Transferencias");
        transferButton.setEnabled(false);

        accountsButton.setText("Mis Cuentas");

        titleLabel.setFont(new java.awt.Font("Cantarell", 2, 48)); // NOI18N
        titleLabel.setHorizontalAlignment(SwingConstants.CENTER);
        titleLabel.setText("FLEECA");
        titleLabel.setHorizontalTextPosition(SwingConstants.CENTER);

        loggedMessageLabel.setFont(new java.awt.Font("Cantarell", 0, 14)); // NOI18N
        loggedMessageLabel.setText("Logueado como");

        logoutButton.setFont(new java.awt.Font("Cantarell", 0, 14)); // NOI18N
        logoutButton.setText("Cerrar sesión");
        logoutButton.setEnabled(false);

        userNameLabel.setFont(new java.awt.Font("Cantarell", 1, 20)); // NOI18N
        userNameLabel.setText("Admin");

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
                                        .addGroup(userAccountPanelLayout.createSequentialGroup()
                                                .addGroup(userAccountPanelLayout
                                                        .createParallelGroup(GroupLayout.Alignment.LEADING)
                                                        .addComponent(logoutButton,
                                                                GroupLayout.DEFAULT_SIZE,
                                                                GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                        .addGroup(userAccountPanelLayout.createSequentialGroup()
                                                                .addComponent(loggedMessageLabel)
                                                                .addGap(0, 0, Short.MAX_VALUE)))
                                                .addContainerGap()))));
        userAccountPanelLayout.setVerticalGroup(
                userAccountPanelLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
                        .addGroup(userAccountPanelLayout.createSequentialGroup()
                                .addContainerGap()
                                .addComponent(loggedMessageLabel)
                                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(userNameLabel)
                                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(logoutButton, GroupLayout.PREFERRED_SIZE, 42,
                                        GroupLayout.PREFERRED_SIZE)
                                .addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)));

        GroupLayout sideNavigationPanelLayout = new GroupLayout(sideNavigationPanel);
        sideNavigationPanel.setLayout(sideNavigationPanelLayout);
        sideNavigationPanelLayout.setHorizontalGroup(
                sideNavigationPanelLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
                        .addGroup(sideNavigationPanelLayout.createSequentialGroup()
                                .addContainerGap()
                                .addGroup(sideNavigationPanelLayout
                                        .createParallelGroup(GroupLayout.Alignment.LEADING)
                                        .addComponent(titleLabel, GroupLayout.Alignment.TRAILING,
                                                GroupLayout.DEFAULT_SIZE, 184, Short.MAX_VALUE)
                                        .addComponent(accountsButton, GroupLayout.DEFAULT_SIZE,
                                                GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addComponent(transferButton, GroupLayout.DEFAULT_SIZE,
                                                GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addComponent(userAccountPanel, GroupLayout.DEFAULT_SIZE,
                                                GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                .addContainerGap()));
        sideNavigationPanelLayout.setVerticalGroup(
                sideNavigationPanelLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
                        .addGroup(sideNavigationPanelLayout.createSequentialGroup()
                                .addContainerGap()
                                .addComponent(titleLabel, GroupLayout.PREFERRED_SIZE, 72,
                                        GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(accountsButton, GroupLayout.PREFERRED_SIZE, 42,
                                        GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(transferButton, GroupLayout.PREFERRED_SIZE, 42,
                                        GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED, 272,
                                        Short.MAX_VALUE)
                                .addComponent(userAccountPanel, GroupLayout.PREFERRED_SIZE,
                                        GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                                .addContainerGap()));

        splitPanel.setLeftComponent(sideNavigationPanel);

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
    }

    public static void main(String args[]) {
        java.awt.EventQueue.invokeLater(() -> {
            new App().setVisible(true);
        });
    }

}
