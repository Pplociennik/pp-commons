package com.github.pplociennik.commons.system;

import java.util.HashSet;
import java.util.Set;

/**
 * An enumeration for managing common system properties. This enum provides a way to handle system property names
 * along with their possible values. It implements the {@link SystemProperty} interface, allowing retrieval of
 * property names and potential values.
 *
 * <p>Each enum instance represents a specific system property, and it can store its possible values as restrictions
 * when certain constraints on the property values are required. If no constraints are provided, all values are allowed.</p>
 *
 * @author Created by: Pplociennik
 * @since 21.03.2025
 */
public enum CommonSystemProperties implements SystemProperty {

    // -- Time Zone properties.

    SYSTEM_JPA_TIME_ZONE( "spring.jpa.properties.hibernate.jdbc.time_zone" );

    // #################################################################################################################

    /**
     * A name of the system property.
     */
    private final String name;

    /**
     * Possible values of the system property.
     */
    private final Set< String > possibleValues;

    /**
     * Constructs a CommonSystemProperties enum instance with the specified name and possible values.
     *
     * @param name
     *         the name of the system property
     * @param possibleValues
     *         a varargs array of possible values for the system property
     */
    CommonSystemProperties( String name, String... possibleValues ) {
        this.name = name;
        this.possibleValues = Set.of( possibleValues );
    }

    /**
     * Constructs a CommonSystemProperties enum instance with the specified name.
     *
     * @param name
     *         the name of the system property
     */
    CommonSystemProperties( String name ) {
        this.name = name;
        this.possibleValues = new HashSet<>();
    }

    /**
     * Returns the name of the system property.
     *
     * @return a {@link String} being a name of the property.
     */
    @Override
    public String getName() {
        return this.name;
    }

    /**
     * Returns possible values of the system property.
     *
     * @return a {@link Set} of the property's possible values. Empty one if there is no restriction on values and all are possible.
     */
    @Override
    public Set< String > getPossibleValues() {
        return this.possibleValues;
    }
}
