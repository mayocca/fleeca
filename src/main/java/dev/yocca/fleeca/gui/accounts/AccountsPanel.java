package dev.yocca.fleeca.gui.accounts;

import dev.yocca.fleeca.exceptions.ServiceException;
import dev.yocca.fleeca.gui.events.AccountEvent;
import dev.yocca.fleeca.gui.events.AccountEventListener;
import dev.yocca.fleeca.entities.Account;
import dev.yocca.fleeca.services.AccountService;
import dev.yocca.fleeca.tables.AccountTableModel;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;
import javax.swing.event.TableModelEvent;

/**
 *
 * @author mayocca
 */
public class AccountsPanel extends javax.swing.JPanel implements AccountEventListener {

    private AccountService accountService;

    public AccountsPanel() {
        this.accountService = new AccountService();
        initComponents();
    }

    private void initComponents() {

        accountsScrollPanel = new javax.swing.JScrollPane();
        accountsTable = new javax.swing.JTable();
        jPanel1 = new javax.swing.JPanel();
        accountsHeader = new javax.swing.JLabel();
        createAccountButton = new javax.swing.JButton();
        deleteAccountButton = new javax.swing.JButton();

        List<Account> accounts = new ArrayList<>();
        try {
            accounts = accountService.getAll();
        } catch (ServiceException ex) {
            JOptionPane.showMessageDialog(this, ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
        AccountTableModel model = new AccountTableModel(accounts);
        model.addTableModelListener(e -> {
            if (e.getType() == TableModelEvent.UPDATE) {
                Integer row = e.getFirstRow();
                Integer id = (Integer) model.getValueAt(row, 0);
                String alias = (String) model.getValueAt(row, 2);
                try {
                    Account account = accountService.get(id);
                    account.setAlias(alias);
                    accountService.update(account);
                } catch (ServiceException ex) {
                    JOptionPane.showMessageDialog(this, ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
                }
            }
        });
        accountsTable.setModel(model);
        accountsTable.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
        accountsTable.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
        accountsScrollPanel.setViewportView(accountsTable);

        accountsHeader.setFont(new java.awt.Font("Cantarell", 1, 24)); // NOI18N
        accountsHeader.setText("Cuentas");

        createAccountButton.setText("Nueva Cuenta");
        createAccountButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                createAccountButtonActionPerformed(evt);
            }
        });

        deleteAccountButton.setText("Eliminar Cuenta");
        deleteAccountButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                deleteAccountButtonActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
                jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(jPanel1Layout.createSequentialGroup()
                                .addContainerGap()
                                .addComponent(accountsHeader)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED,
                                        javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(deleteAccountButton)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(createAccountButton)
                                .addContainerGap()));
        jPanel1Layout.setVerticalGroup(
                jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(jPanel1Layout.createSequentialGroup()
                                .addContainerGap()
                                .addGroup(jPanel1Layout
                                        .createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                        .addComponent(accountsHeader, javax.swing.GroupLayout.DEFAULT_SIZE, 42,
                                                Short.MAX_VALUE)
                                        .addComponent(deleteAccountButton, javax.swing.GroupLayout.DEFAULT_SIZE,
                                                javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addComponent(createAccountButton, javax.swing.GroupLayout.DEFAULT_SIZE,
                                                javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
                layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                .addContainerGap()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                        .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE,
                                                javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addComponent(accountsScrollPanel, javax.swing.GroupLayout.DEFAULT_SIZE, 673,
                                                Short.MAX_VALUE))
                                .addGap(11, 11, 11)));
        layout.setVerticalGroup(
                layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(layout.createSequentialGroup()
                                .addContainerGap()
                                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE,
                                        javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(accountsScrollPanel, javax.swing.GroupLayout.PREFERRED_SIZE, 431,
                                        javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)));
    }// </editor-fold>//GEN-END:initComponents

    private void createAccountButtonActionPerformed(java.awt.event.ActionEvent evt) {// GEN-FIRST:event_createAccountButtonActionPerformed
        CreateDialog dialog = new CreateDialog(null, true);
        dialog.addAccountEventListener(this);
        dialog.setVisible(true);
    }// GEN-LAST:event_createAccountButtonActionPerformed

    private void deleteAccountButtonActionPerformed(java.awt.event.ActionEvent evt) {// GEN-FIRST:event_deleteAccountButtonActionPerformed
        int response = JOptionPane.showConfirmDialog(this, "¿Está seguro que desea eliminar la cuenta?",
                "Eliminar cuenta", JOptionPane.YES_NO_OPTION);
        if (response == 0) {
            AccountTableModel model = (AccountTableModel) accountsTable.getModel();
            int row = accountsTable.getSelectedRow();
            int id = (int) model.getValueAt(row, 0);
            try {
                accountService.delete(id);
            } catch (ServiceException e) {
                JOptionPane.showMessageDialog(this, e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }
            updateTable();
        }
    }// GEN-LAST:event_deleteAccountButtonActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel accountsHeader;
    private javax.swing.JScrollPane accountsScrollPanel;
    private javax.swing.JTable accountsTable;
    private javax.swing.JButton createAccountButton;
    private javax.swing.JButton deleteAccountButton;
    private javax.swing.JPanel jPanel1;
    // End of variables declaration//GEN-END:variables

    @Override
    public void accountEvent(AccountEvent event) {
        updateTable();
    }

    public void updateTable() {
        AccountTableModel model = (AccountTableModel) accountsTable.getModel();
        try {
            model.setAccounts(accountService.getAll());
        } catch (ServiceException e) {
            JOptionPane.showMessageDialog(this, e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }
}
