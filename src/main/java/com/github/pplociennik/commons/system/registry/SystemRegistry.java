package com.github.pplociennik.commons.system.registry;

import org.springframework.lang.NonNull;

/**
 * The SystemRegistry interface defines a generic registry system for managing objects of type T.
 * It provides basic operations for adding, removing, and managing objects within the registry.
 *
 * @param <T>
 *         the type of objects stored in this registry
 * @author Created by: Pplociennik at 27.05.2025 16:31
 */
public interface SystemRegistry< T > {

    /**
     * Adds the specified object to the system registry.
     *
     * @param aObject
     *         the object to be added to the system registry
     */
    void add( @NonNull T aObject );

    /**
     * Removes all elements from the system registry.
     * After invoking this method, the registry will be empty.
     */
    void clear();

    /**
     * Creates and returns a copy of this {@code SystemRegistry} instance.
     * The exact meaning of "copy" may depend on the implementation, but it typically involves duplicating
     * the current state of the registry, including its stored elements, if any.
     *
     * @return a clone of this {@code SystemRegistry} instance
     */
    SystemRegistry< T > clone();
}
