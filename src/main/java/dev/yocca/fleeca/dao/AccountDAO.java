package dev.yocca.fleeca.dao;

import dev.yocca.fleeca.database.DatabaseConnection;
import dev.yocca.fleeca.dao.interfaces.GenericDAO;
import dev.yocca.fleeca.exceptions.DAOException;
import dev.yocca.fleeca.entities.Account;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class AccountDAO implements GenericDAO<Account> {

    @Override
    public void add(Account item) throws DAOException {
        Connection connection;
        try {
            connection = DatabaseConnection.getInstance();
        } catch (SQLException e) {
            throw new DAOException("No se pudo realizar la conexión a la base de datos", e);
        }

        String query = "INSERT INTO accounts (owner, alias) VALUES (?, ?)";
        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setString(1, item.getOwner());
            statement.setString(2, item.getAlias());
            statement.executeUpdate();
        } catch (SQLException e) {
            throw new DAOException(e.getMessage(), e);
        }
    }

    @Override
    public Account get(int id) throws DAOException {
        Connection connection;
        try {
            connection = DatabaseConnection.getInstance();
        } catch (SQLException e) {
            throw new DAOException("No se pudo realizar la conexión a la base de datos", e);
        }

        String query = "SELECT * FROM accounts WHERE id = ?";
        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setInt(1, id);
            ResultSet result = statement.executeQuery();
            if (result.next()) {
                return new Account(
                        result.getInt("id"),
                        result.getString("owner"),
                        result.getString("alias"),
                        result.getDouble("balance"),
                        result.getTimestamp("created_at").toLocalDateTime(),
                        result.getTimestamp("updated_at").toLocalDateTime());
            }
        } catch (SQLException e) {
            throw new DAOException(e.getMessage(), e);
        }

        return null;
    }

    @Override
    public List<Account> getAll() throws DAOException {
        Connection connection;
        try {
            connection = DatabaseConnection.getInstance();
        } catch (SQLException e) {
            throw new DAOException("No se pudo realizar la conexión a la base de datos", e);
        }

        List<Account> accounts = new ArrayList<>();
        String query = "SELECT * FROM accounts";

        try (PreparedStatement statement = connection.prepareStatement(query)) {
            ResultSet result = statement.executeQuery();

            while (result.next()) {
                accounts.add(new Account(
                        result.getInt("id"),
                        result.getString("owner"),
                        result.getString("alias"),
                        result.getDouble("balance"),
                        result.getTimestamp("created_at").toLocalDateTime(),
                        result.getTimestamp("updated_at").toLocalDateTime()));
            }
        } catch (SQLException e) {
            throw new DAOException(e.getMessage(), e);
        }

        return accounts;
    }

    @Override
    public void update(Account item) throws DAOException {
        Connection connection;
        try {
            connection = DatabaseConnection.getInstance();
        } catch (SQLException e) {
            throw new DAOException("No se pudo realizar la conexión a la base de datos", e);
        }

        String query = "UPDATE accounts SET owner = ?, alias = ? WHERE id = ?";
        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setString(1, item.getOwner());
            statement.setString(2, item.getAlias());
            statement.setInt(3, item.getId());
            statement.executeUpdate();
        } catch (SQLException e) {
            String message = e.getMessage();
            if (message.contains("accounts_alias_key")) {
                throw new DAOException("El alias ya está en uso", e);
            }
            throw new DAOException(e.getMessage(), e);
        }
    }

    @Override
    public void delete(int id) throws DAOException {
        Connection connection;
        try {
            connection = DatabaseConnection.getInstance();
        } catch (SQLException e) {
            throw new DAOException("No se pudo realizar la conexión a la base de datos", e);
        }

        String query = "DELETE FROM accounts WHERE id = ?";
        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setInt(1, id);
            statement.executeUpdate();
        } catch (SQLException e) {
            throw new DAOException(e.getMessage(), e);
        }
    }
}
