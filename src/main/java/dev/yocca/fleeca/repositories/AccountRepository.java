package dev.yocca.fleeca.repositories;

import dev.yocca.fleeca.database.DatabaseConnection;
import dev.yocca.fleeca.database.Repository;
import dev.yocca.fleeca.exceptions.DatabaseConnectionException;
import dev.yocca.fleeca.exceptions.RepositoryException;
import dev.yocca.fleeca.entities.Account;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class AccountRepository implements Repository<Account> {

    @Override
    public void add(Account item) throws RepositoryException {
        Connection connection;
        try {
            connection = DatabaseConnection.getInstance();
        } catch (DatabaseConnectionException e) {
            throw new RepositoryException("No se pudo realizar la conexión a la base de datos", e);
        }

        String query = "INSERT INTO accounts (owner, alias) VALUES (?, ?)";
        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setString(1, item.getOwner());
            statement.setString(2, item.getAlias());
            statement.executeUpdate();
        } catch (SQLException e) {
            String message = e.getMessage();
            if (message.contains("accounts_alias_key")) {
                throw new RepositoryException("El alias ya está en uso", e);
            }
            if (message.contains("valid_owner")) {
                throw new RepositoryException("El nombre del cliente no es válido", e);
            }
            if (message.contains("valid_alias")) {
                throw new RepositoryException("El alias no es válido", e);
            }
            throw new RepositoryException("No se pudo insertar el registro", e);
        }
    }

    @Override
    public Account get(int id) throws RepositoryException {
        Connection connection;
        try {
            connection = DatabaseConnection.getInstance();
        } catch (DatabaseConnectionException e) {
            throw new RepositoryException("No se pudo realizar la conexión a la base de datos", e);
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
            throw new RepositoryException("No se pudo obtener el registro", e);
        }

        return null;
    }

    @Override
    public List<Account> getAll() throws RepositoryException {
        Connection connection;
        try {
            connection = DatabaseConnection.getInstance();
        } catch (DatabaseConnectionException e) {
            throw new RepositoryException("No se pudo realizar la conexión a la base de datos", e);
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
            throw new RepositoryException("No se pudo obtener el registro", e);
        }

        return accounts;
    }

    @Override
    public void update(Account item) throws RepositoryException {
        Connection connection;
        try {
            connection = DatabaseConnection.getInstance();
        } catch (DatabaseConnectionException e) {
            throw new RepositoryException("No se pudo realizar la conexión a la base de datos", e);
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
                throw new RepositoryException("El alias ya está en uso", e);
            }
            throw new RepositoryException("No se pudo actualizar el registro", e);
        }
    }

    @Override
    public void remove(int id) throws RepositoryException {
        Connection connection;
        try {
            connection = DatabaseConnection.getInstance();
        } catch (DatabaseConnectionException e) {
            throw new RepositoryException("No se pudo realizar la conexión a la base de datos", e);
        }

        String query = "DELETE FROM accounts WHERE id = ?";
        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setInt(1, id);
            statement.executeUpdate();
        } catch (SQLException e) {
            throw new RepositoryException("No se pudo eliminar el registro", e);
        }
    }
}
