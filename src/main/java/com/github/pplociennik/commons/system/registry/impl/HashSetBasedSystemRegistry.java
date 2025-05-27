package com.github.pplociennik.commons.system.registry.impl;


import com.github.pplociennik.commons.system.registry.CollectingSystemRegistry;
import com.github.pplociennik.commons.system.registry.SystemRegistry;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Component;

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Stream;

import static java.util.Objects.requireNonNull;

/**
 * A concrete implementation of {@link CollectingSystemRegistry} that uses a {@link HashSet} as its underlying storage mechanism.
 * This implementation provides efficient storage and retrieval of unique elements, preventing duplicates from being stored.
 * The HashSet backing ensures O(1) average time complexity for basic operations such as add, remove, and contains.
 * <p>
 * This class is thread-safe when accessed through a synchronized wrapper, but unsafe by default.
 * All operations that modify the registry's contents ensure non-null values through explicit checks.
 *
 * @param <T>
 *         the type of elements maintained by this registry
 * @author Created by: Pplociennik at 27.05.2025 16:59
 */
@AllArgsConstructor
@NoArgsConstructor
@Component
public final class HashSetBasedSystemRegistry< T > implements CollectingSystemRegistry< T > {

    /**
     * A set used to hold the values stored in the registry.
     * This set ensures the uniqueness of the stored elements and does not allow duplicates.
     * The storage is backed by a {@code HashSet}, which provides constant-time performance
     * for basic operations such as add, remove, and contains.
     */
    private Set< T > values = new HashSet<>();

    /**
     * Adds the specified objects to the collecting system registry.
     *
     * @param aObjects
     *         the objects to be added to the collecting system registry
     */
    @SafeVarargs
    @Override
    public final void add( @NonNull T... aObjects ) {
        requireNonNull( aObjects );
        values.addAll( Set.of( aObjects ) );
    }

    /**
     * Adds a collection of specified objects to the collecting system registry.
     *
     * @param aObjects
     *         the collection of objects to be added to the collecting system registry
     */
    @Override
    public void add( @NonNull Collection< T > aObjects ) {
        requireNonNull( aObjects );
        values.addAll( aObjects );
    }

    /**
     * Removes a collection of specified objects from the collecting system registry.
     *
     * @param aObjects
     *         the collection of objects to be removed from the collecting system registry
     */
    @Override
    public void remove( @NonNull Collection< T > aObjects ) {
        requireNonNull( aObjects );
        values.removeAll( aObjects );
    }

    /**
     * Returns an iterable collection of all elements currently stored in the registry.
     *
     * @return an {@code Iterable} containing all elements in the registry
     */
    @Override
    public Iterable< T > values() {
        return new HashSet<>( values );
    }

    /**
     * Returns a sequential {@code Stream} containing all elements currently stored in the registry.
     * The stream allows operations such as filtering, mapping, and aggregation to be performed
     * on the registry elements.
     *
     * @return a {@code Stream} containing all elements in the registry
     */
    @Override
    public Stream< T > stream() {
        return values.stream();
    }

    /**
     * Adds the specified object to the system registry.
     *
     * @param aObject
     *         the object to be added to the system registry
     */
    @Override
    public void add( @NonNull T aObject ) {
        requireNonNull( aObject );
        values.add( aObject );
    }

    /**
     * Removes all elements from the system registry.
     * After invoking this method, the registry will be empty.
     */
    @Override
    public void clear() {
        values.clear();
    }

    /**
     * Creates and returns a copy of this {@code SystemRegistry} instance.
     * The exact meaning of "copy" may depend on the implementation, but it typically involves duplicating
     * the current state of the registry, including its stored elements, if any.
     *
     * @return a clone of this {@code SystemRegistry} instance
     */
    @Override
    public SystemRegistry< T > clone() {
        return new HashSetBasedSystemRegistry<>( values );
    }
}
