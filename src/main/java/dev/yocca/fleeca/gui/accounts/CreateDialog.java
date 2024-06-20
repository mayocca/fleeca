package dev.yocca.fleeca.gui.accounts;

import dev.yocca.fleeca.exceptions.ServiceException;
import dev.yocca.fleeca.gui.events.AccountCreatedEvent;
import dev.yocca.fleeca.gui.events.AccountCreatedListener;
import dev.yocca.fleeca.entities.Account;
import dev.yocca.fleeca.services.AccountService;

import java.util.ArrayList;
import java.util.List;

import javax.swing.*;

public class CreateDialog extends JDialog {

    private JLabel accountsHeader;
    private JPanel aliasPanel;
    private JTextField aliasTextField;
    private JPanel clientPanel;
    private JTextField clientTextField;
    private JButton confirmButton;
    private JLabel jLabel1;
    private JLabel jLabel2;
    private JPanel jPanel1;

    private List<AccountCreatedListener> listeners = new ArrayList<>();

    public CreateDialog(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        initComponents();
    }

    private void initComponents() {

        accountsHeader = new JLabel();
        jPanel1 = new JPanel();
        clientPanel = new JPanel();
        jLabel1 = new JLabel();
        clientTextField = new JTextField();
        aliasPanel = new JPanel();
        jLabel2 = new JLabel();
        aliasTextField = new JTextField();
        confirmButton = new JButton();

        setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);

        accountsHeader.setFont(new java.awt.Font("Cantarell", 1, 24)); // NOI18N
        accountsHeader.setText("Crear cuenta");

        jLabel1.setText("Nombre de cliente");

        GroupLayout clientPanelLayout = new GroupLayout(clientPanel);
        clientPanel.setLayout(clientPanelLayout);
        clientPanelLayout.setHorizontalGroup(
                clientPanelLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
                        .addGroup(clientPanelLayout.createSequentialGroup()
                                .addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addGroup(
                                        clientPanelLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
                                                .addComponent(clientTextField, GroupLayout.PREFERRED_SIZE,
                                                        200, GroupLayout.PREFERRED_SIZE)
                                                .addComponent(jLabel1))));
        clientPanelLayout.setVerticalGroup(
                clientPanelLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
                        .addGroup(clientPanelLayout.createSequentialGroup()
                                .addContainerGap()
                                .addComponent(jLabel1)
                                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(clientTextField, GroupLayout.PREFERRED_SIZE,
                                        GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                                .addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)));

        jLabel2.setText("Alias");

        GroupLayout aliasPanelLayout = new GroupLayout(aliasPanel);
        aliasPanel.setLayout(aliasPanelLayout);
        aliasPanelLayout.setHorizontalGroup(
                aliasPanelLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
                        .addGroup(aliasPanelLayout.createSequentialGroup()
                                .addContainerGap()
                                .addGroup(
                                        aliasPanelLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
                                                .addComponent(aliasTextField, GroupLayout.PREFERRED_SIZE,
                                                        200, GroupLayout.PREFERRED_SIZE)
                                                .addComponent(jLabel2))
                                .addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)));
        aliasPanelLayout.setVerticalGroup(
                aliasPanelLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
                        .addGroup(aliasPanelLayout.createSequentialGroup()
                                .addContainerGap()
                                .addComponent(jLabel2)
                                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(aliasTextField, GroupLayout.PREFERRED_SIZE,
                                        GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                                .addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)));

        confirmButton.setText("Confirm");
        confirmButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                confirmButtonActionPerformed(evt);
            }
        });

        GroupLayout jPanel1Layout = new GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
                jPanel1Layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                        .addGroup(jPanel1Layout.createSequentialGroup()
                                .addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addGroup(jPanel1Layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                                        .addGroup(jPanel1Layout.createSequentialGroup()
                                                .addComponent(clientPanel, GroupLayout.PREFERRED_SIZE,
                                                        GroupLayout.DEFAULT_SIZE,
                                                        GroupLayout.PREFERRED_SIZE)
                                                .addGap(6, 6, 6)
                                                .addComponent(aliasPanel, GroupLayout.PREFERRED_SIZE,
                                                        GroupLayout.DEFAULT_SIZE,
                                                        GroupLayout.PREFERRED_SIZE))
                                        .addComponent(confirmButton))));
        jPanel1Layout.setVerticalGroup(
                jPanel1Layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                        .addGroup(GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                                .addContainerGap()
                                .addGroup(jPanel1Layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                                        .addComponent(aliasPanel, GroupLayout.PREFERRED_SIZE,
                                                GroupLayout.DEFAULT_SIZE,
                                                GroupLayout.PREFERRED_SIZE)
                                        .addComponent(clientPanel, GroupLayout.PREFERRED_SIZE,
                                                GroupLayout.DEFAULT_SIZE,
                                                GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(confirmButton)
                                .addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)));

        GroupLayout layout = new GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
                layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                        .addGroup(layout.createSequentialGroup()
                                .addContainerGap()
                                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                                        .addComponent(jPanel1, GroupLayout.DEFAULT_SIZE,
                                                GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addComponent(accountsHeader, GroupLayout.DEFAULT_SIZE,
                                                GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                .addContainerGap()));
        layout.setVerticalGroup(
                layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                        .addGroup(layout.createSequentialGroup()
                                .addContainerGap()
                                .addComponent(accountsHeader, GroupLayout.PREFERRED_SIZE, 42,
                                        GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jPanel1, GroupLayout.DEFAULT_SIZE,
                                        GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addContainerGap()));

        pack();
    }

    private void confirmButtonActionPerformed(java.awt.event.ActionEvent evt) {
        Account account = new Account(this.clientTextField.getText(), this.aliasTextField.getText());
        AccountService accountService = new AccountService();
        try {
            accountService.add(account);
        } catch (ServiceException e) {
            JOptionPane.showMessageDialog(clientPanel, e.getMessage(), "Error al crear cuenta",
                    JOptionPane.ERROR_MESSAGE);
            return;
        }
        notifyAccountCreation(account);
        this.dispose();
    }

    public void addAccountCreationListener(AccountCreatedListener listener) {
        listeners.add(listener);
    }

    private void notifyAccountCreation(Account account) {
        AccountCreatedEvent event = new AccountCreatedEvent(this, account);
        for (AccountCreatedListener listener : listeners) {
            listener.accountCreated(event);
        }
    }

}
