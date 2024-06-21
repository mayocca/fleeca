package dev.yocca.fleeca.tables;

import dev.yocca.fleeca.entities.Account;

import java.util.ArrayList;
import javax.swing.table.AbstractTableModel;
import java.util.List;

/**
 *
 * @author mayocca
 */
public class AccountTableModel extends AbstractTableModel {

    private final String[] columns = { "ID", "Cliente", "Alias", "Balance" };
    private final Class[] columnClasses = { Integer.class, String.class, String.class, Double.class };
    private List<Account> accounts;

    public AccountTableModel(List<Account> accounts) {
        this.accounts = accounts;
    }

    public void setAccounts(List<Account> accounts) {
        this.accounts = accounts;
        fireTableDataChanged();
    }

    public void addAccount(Account account) {
        accounts.add(account);
        fireTableDataChanged();
    }

    public void removeAccount(Account account) {
        accounts.remove(account);
        fireTableDataChanged();
    }

    public Account getAccountAt(int rowIndex) {
        return accounts.get(rowIndex);
    }

    @Override
    public int getRowCount() {
        return accounts.size();
    }

    @Override
    public int getColumnCount() {
        return columns.length;
    }

    @Override
    public String getColumnName(int i) {
        return columns[i];
    }

    @Override
    public Class<?> getColumnClass(int i) {
        return columnClasses[i];
    }

    @Override
    public boolean isCellEditable(int i, int i1) {
        // Solo se puede editar el alias
        return i1 == 2;
    }

    @Override
    public Object getValueAt(int i, int i1) {
        Account account = accounts.get(i);
        return switch (i1) {
            case 0 -> account.getId();
            case 1 -> account.getOwner();
            case 2 -> account.getAlias();
            case 3 -> account.getBalance();
            default -> null;
        };
    }
}
