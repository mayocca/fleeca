package dev.yocca.fleeca.gui.components.navigation;

import javax.swing.*;

public class Sidebar extends JPanel {

    private JButton accountsButton;
    private JLabel titleLabel;
    private JButton transferButton;
    private JPanel userAccountPanel;

    public Sidebar() {
        setup();
        mount();
    }

    private void setup() {
        setBorder(BorderFactory.createEtchedBorder());

        titleLabel = new JLabel();
        accountsButton = new JButton();
        transferButton = new JButton();
        userAccountPanel = new JPanel();
    }

    private void mount() {
        transferButton.setText("Transferencias");
        transferButton.setEnabled(false);

        accountsButton.setText("Mis Cuentas");

        titleLabel.setHorizontalAlignment(SwingConstants.CENTER);
        titleLabel.setText("FLEECA");
        titleLabel.setHorizontalTextPosition(SwingConstants.CENTER);

        GroupLayout sideNavigationPanelLayout = new GroupLayout(this);
        this.setLayout(sideNavigationPanelLayout);
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

    }
}
