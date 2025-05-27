package com.github.pplociennik.commons.system.registry.impl;

import com.github.pplociennik.commons.system.registry.CollectingSystemRegistry;
import com.github.pplociennik.commons.system.registry.SystemRegistry;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Component;

import java.util.*;
import java.util.stream.Stream;

import static java.util.Objects.requireNonNull;

/**
 * A thread-safe implementation of {@link CollectingSystemRegistry} that uses a synchronized {@link HashSet} as its underlying storage mechanism.
 * This implementation provides efficient storage and retrieval of unique elements while ensuring thread safety for all operations.
 * <p>
 * The registry uses {@link Collections#synchronizedSet(Set)} to wrap a {@link HashSet}, providing thread-safe access to the collection.
 * All modifying operations are additionally synchronized at the method level to ensure atomic execution of complex operations.
 * The HashSet backing ensures O(1) average time complexity for basic operations such as add, remove, and contains, while
 * preventing duplicate entries.
 * <p>
 * This implementation is suitable for concurrent environments where multiple threads need to access and modify the registry simultaneously.
 *
 * @param <T>
 *         the type of elements maintained by this registry
 * @author Created by: Pplociennik at 27.05.2025 17:16
 */
@AllArgsConstructor
@NoArgsConstructor
@Component
public final class SynchronizedHashSetBasedSystemRegistry< T > implements CollectingSystemRegistry< T > {

    /**
     * A thread-safe set used in the system registry for storing elements of type {@code T}.
     * The set synchronizes its operations and ensures that concurrent access is properly managed.
     * The underlying collection is a {@code HashSet}, which prevents duplicates and provides constant-time
     * performance for basic operations like add, remove, and contains.
     */
    private Set< T > values = Collections.synchronizedSet( new HashSet< T >() );

    /**
     * Adds the specified objects to the collecting system registry.
     *
     * @param aObjects
     *         the objects to be added to the collecting system registry
     */
    @SafeVarargs
    @Override
    public final synchronized void add( @NonNull T... aObjects ) {
        requireNonNull( aObjects );
        values.addAll( Arrays.asList( aObjects ) );
    }

    /**
     * Adds a collection of specified objects to the collecting system registry.
     *
     * @param aObjects
     *         the collection of objects to be added to the collecting system registry
     */
    @Override
    public synchronized void add( @NonNull Collection< T > aObjects ) {
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
    public synchronized void remove( @NonNull Collection< T > aObjects ) {
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
        return values;
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
    public synchronized void add( @NonNull T aObject ) {
        requireNonNull( aObject );
        values.add( aObject );
    }

    /**
     * Removes all elements from the system registry.
     * After invoking this method, the registry will be empty.
     */
    @Override
    public synchronized void clear() {
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
        return new SynchronizedHashSetBasedSystemRegistry<>( values );
    }
}
