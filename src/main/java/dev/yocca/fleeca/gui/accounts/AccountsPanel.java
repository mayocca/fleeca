package dev.yocca.fleeca.gui.accounts;

import dev.yocca.fleeca.exceptions.ServiceException;
import dev.yocca.fleeca.entities.Account;
import dev.yocca.fleeca.services.AccountService;
import dev.yocca.fleeca.tables.AccountTableModel;
import java.util.ArrayList;
import java.util.List;
import javax.swing.*;
import javax.swing.event.*;

/**
 *
 * @author mayocca
 */
public class AccountsPanel extends JPanel {

    private JLabel accountsHeader;
    private JScrollPane accountsScrollPanel;
    private JTable accountsTable;
    private JButton createAccountButton;
    private JButton deleteAccountButton;
    private JPanel jPanel1;


    private AccountService accountService;

    public AccountsPanel() {
        this.accountService = new AccountService();
        initComponents();
    }

    private void initComponents() {

        accountsScrollPanel = new JScrollPane();
        accountsTable = new JTable();
        jPanel1 = new JPanel();
        accountsHeader = new JLabel();
        createAccountButton = new JButton();
        deleteAccountButton = new JButton();

        List<Account> accounts = new ArrayList<>();
        try {
            accounts = accountService.getAll();
        } catch (ServiceException ex) {
            JOptionPane.showMessageDialog(this, ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
        AccountTableModel model = new AccountTableModel(accounts);

        accountsTable.setModel(model);
        accountsTable.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        accountsScrollPanel.setViewportView(accountsTable);

        accountsHeader.setFont(new java.awt.Font("Cantarell", 1, 24)); // NOI18N
        accountsHeader.setText("Cuentas");

        createAccountButton.setText("Nueva Cuenta");
        createAccountButton.addActionListener(actionEvent -> {
            CreateDialog dialog = new CreateDialog(null , true);
            dialog.addAccountCreationListener(accountCreatedEvent -> updateTable());
            dialog.setVisible(true);
        });

        deleteAccountButton.setText("Eliminar Cuenta");
        deleteAccountButton.addActionListener(this::deleteAccountButtonActionPerformed);

        GroupLayout jPanel1Layout = new GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
                jPanel1Layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                        .addGroup(jPanel1Layout.createSequentialGroup()
                                .addContainerGap()
                                .addComponent(accountsHeader)
                                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED,
                                        GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(deleteAccountButton)
                                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(createAccountButton)
                                .addContainerGap()));
        jPanel1Layout.setVerticalGroup(
                jPanel1Layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                        .addGroup(jPanel1Layout.createSequentialGroup()
                                .addContainerGap()
                                .addGroup(jPanel1Layout
                                        .createParallelGroup(GroupLayout.Alignment.LEADING, false)
                                        .addComponent(accountsHeader, GroupLayout.DEFAULT_SIZE, 42,
                                                Short.MAX_VALUE)
                                        .addComponent(deleteAccountButton, GroupLayout.DEFAULT_SIZE,
                                                GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addComponent(createAccountButton, GroupLayout.DEFAULT_SIZE,
                                                GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                .addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)));

        GroupLayout layout = new GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
                layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                        .addGroup(GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                .addContainerGap()
                                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.TRAILING)
                                        .addComponent(jPanel1, GroupLayout.DEFAULT_SIZE,
                                                GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addComponent(accountsScrollPanel, GroupLayout.DEFAULT_SIZE, 673,
                                                Short.MAX_VALUE))
                                .addGap(11, 11, 11)));
        layout.setVerticalGroup(
                layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                        .addGroup(layout.createSequentialGroup()
                                .addContainerGap()
                                .addComponent(jPanel1, GroupLayout.PREFERRED_SIZE,
                                        GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(accountsScrollPanel, GroupLayout.PREFERRED_SIZE, 431,
                                        GroupLayout.PREFERRED_SIZE)
                                .addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)));
    }

    private void deleteAccountButtonActionPerformed(java.awt.event.ActionEvent evt) {
        int response = JOptionPane.showConfirmDialog(this, "¿Está seguro que desea eliminar la cuenta?",
                "Eliminar cuenta", JOptionPane.YES_NO_OPTION);
        if (response == JOptionPane.YES_OPTION) {
            AccountTableModel model = (AccountTableModel) accountsTable.getModel();
            int row = accountsTable.getSelectedRow();
            int id = (int) model.getValueAt(row, 0);
            try {
                accountService.delete(id);
            } catch (ServiceException e) {
                JOptionPane.showMessageDialog(this, e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    private void updateTable() {
        AccountTableModel model = (AccountTableModel) accountsTable.getModel();
        List<Account> accounts = new ArrayList<>();
        try {
            accounts = accountService.getAll();
        } catch (ServiceException ex) {
            JOptionPane.showMessageDialog(this, ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
        model.setAccounts(accounts);
    }
}
