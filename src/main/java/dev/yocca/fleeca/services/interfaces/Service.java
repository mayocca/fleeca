package dev.yocca.fleeca.services.interfaces;

import dev.yocca.fleeca.database.Entity;
import dev.yocca.fleeca.exceptions.ServiceException;

import java.util.List;

public interface Service<T extends Entity> {
    void add(T item) throws ServiceException;
    T get(int id) throws ServiceException;
    void update(T item) throws ServiceException;
    void delete(int id) throws ServiceException;
    List<T> getAll() throws ServiceException;
}
