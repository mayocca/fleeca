package dev.yocca.fleeca.database;

import dev.yocca.fleeca.database.Model;
import dev.yocca.fleeca.exceptions.RepositoryException;

import java.util.List;

/**
 * @param <T> The model this repository provides
 */
public interface Repository<T extends Model> {
    void add(T item) throws RepositoryException;
    T get(int id) throws RepositoryException;
    List<T> getAll() throws RepositoryException;
    void update(T item) throws RepositoryException;
    void remove(int id) throws RepositoryException;
}
