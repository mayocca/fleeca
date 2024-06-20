package dev.yocca.fleeca.services;

import dev.yocca.fleeca.dao.AccountDAO;
import dev.yocca.fleeca.entities.Account;
import dev.yocca.fleeca.exceptions.DAOException;
import dev.yocca.fleeca.exceptions.ServiceException;
import dev.yocca.fleeca.services.interfaces.Service;

import java.util.List;

public class AccountService implements Service<Account> {

    private final AccountDAO accountDAO;

    public AccountService() {
        this.accountDAO = new AccountDAO();
    }

    @Override
    public void add(Account item) throws ServiceException {
        try {
            accountDAO.add(item);
        } catch (DAOException e) {
            String errorMessage = switch (e.getViolatedConstraint()) {
                case "valid_owner" -> "El propietario no es válido";
                case "valid_alias" -> "El alias no es válido";
                case "unique_alias" -> "El alias ya está en uso";
                default -> "No se pudo agregar la cuenta";
            };
            throw new ServiceException(errorMessage, e);
        }
    }

    @Override
    public Account get(int id) throws ServiceException {
        try {
            return accountDAO.get(id);
        } catch (DAOException e) {
            throw new ServiceException("No se pudo obtener la cuenta", e);
        }
    }

    @Override
    public void update(Account item) throws ServiceException {
        if (item.getId() == null) {
            throw new ServiceException("El ID no puede ser nulo");
        }

        try {
            accountDAO.update(item);
        } catch (DAOException e) {
            String errorMessage = switch (e.getViolatedConstraint()) {
                case "valid_owner" -> "El propietario no es válido";
                case "valid_alias" -> "El alias no es válido";
                case "unique_alias" -> "El alias ya está en uso";
                case "positive_balance" -> "El balance no puede ser negativo";
                default -> "No se pudo actualizar la cuenta";
            };
            throw new ServiceException(errorMessage, e);
        }
    }

    @Override
    public void delete(int id) throws ServiceException {
        try {
            accountDAO.delete(id);
        } catch (DAOException e) {
            throw new ServiceException("No se pudo eliminar la cuenta", e);
        }
    }

    @Override
    public List<Account> getAll() throws ServiceException {
        try {
            return accountDAO.getAll();
        } catch (DAOException e) {
            throw new ServiceException("No se pudieron obtener las cuentas", e);
        }
    }
}
