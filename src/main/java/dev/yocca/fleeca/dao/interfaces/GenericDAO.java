package dev.yocca.fleeca.dao.interfaces;

import dev.yocca.fleeca.database.Entity;
import dev.yocca.fleeca.exceptions.DAOException;

import java.util.List;

/**
 * @param <T> The model this repository provides
 */
public interface GenericDAO<T extends Entity> {
    void add(T item) throws DAOException;
    T get(int id) throws DAOException;
    void update(T item) throws DAOException;
    void delete(int id) throws DAOException;
    List<T> getAll() throws DAOException;
}
