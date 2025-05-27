package com.github.pplociennik.commons.system.registry;

import org.springframework.lang.NonNull;

import java.util.Collection;
import java.util.stream.Stream;

/**
 * The CollectingSystemRegistry interface extends SystemRegistry to provide additional functionality
 * for managing collections of objects. It introduces bulk operations for adding and removing multiple
 * objects simultaneously, as well as methods to access and stream the stored elements.
 *
 * @param <T>
 *         the type of objects stored in this collecting registry
 * @author Created by: Pplociennik at 27.05.2025 16:48
 */
public interface CollectingSystemRegistry< T > extends SystemRegistry< T > {

    /**
     * Adds the specified objects to the collecting system registry.
     *
     * @param aObjects
     *         the objects to be added to the collecting system registry
     */
    void add( @NonNull T... aObjects );

    /**
     * Adds a collection of specified objects to the collecting system registry.
     *
     * @param aObjects
     *         the collection of objects to be added to the collecting system registry
     */
    void add( @NonNull Collection< T > aObjects );

    /**
     * Removes a collection of specified objects from the collecting system registry.
     *
     * @param aObjects
     *         the collection of objects to be removed from the collecting system registry
     */
    void remove( @NonNull Collection< T > aObjects );

    /**
     * Returns an iterable collection of all elements currently stored in the registry.
     *
     * @return an {@code Iterable} containing all elements in the registry
     */
    Iterable< T > values();

    /**
     * Returns a sequential {@code Stream} containing all elements currently stored in the registry.
     * The stream allows operations such as filtering, mapping, and aggregation to be performed
     * on the registry elements.
     *
     * @return a {@code Stream} containing all elements in the registry
     */
    Stream< T > stream();

}
