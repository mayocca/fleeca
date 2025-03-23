package dev.yocca.fleeca.core.di_v2;

import dev.yocca.fleeca.core.di_v2.exceptions.ContainerException;
import dev.yocca.fleeca.core.di_v2.exceptions.NotFoundException;

/**
 * Describes the interface of a container that exposes methods to read its entries.
 * Based on the PSR-11 Container Interface.
 */
public interface ContainerInterface {

    /**
     * Finds an entry of the container by its identifier and returns it.
     *
     * @param id Identifier of the entry to look for.
     * @return The entry associated with the identifier.
     * @throws NotFoundException If no entry is found for the given identifier.
     * @throws ContainerException If there is an error while retrieving the entry.
     */
    <T> T get(String id) throws NotFoundException, ContainerException;

    /**
     * Returns true if the container can return an entry for the given identifier.
     * Returns false otherwise.
     * `has(id)` returning true does not mean that `get(id)` will not throw an exception.
     * It does however mean that `get(id)` will not throw a `NotFoundException`.
     *
     * @param id Identifier of the entry to check.
     * @return True if the container has an entry for the identifier, false otherwise.
     */
    boolean has(String id);

    /**
     * Finds an entry of the container by its class type and returns it.
     *
     * @param type Class type of the entry to look for.rs
     * @return The entry associated with the class type.
     * @throws NotFoundException If no entry is found for the given class type.
     * @throws ContainerException If there is an error while retrieving the entry.
     */
    <T> T get(Class<T> type) throws NotFoundException, ContainerException;

    /**
     * Returns true if the container can return an entry for the given class type.
     *
     * @param type Class type of the entry to check.
     * @return True if the container has an entry for the class type, false otherwise.
     */
    boolean has(Class<?> type);
}
