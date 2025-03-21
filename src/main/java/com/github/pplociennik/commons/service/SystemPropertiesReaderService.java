package com.github.pplociennik.commons.service;

import com.github.pplociennik.commons.system.SystemProperty;
import lombok.NonNull;

/**
 * A service providing functionalities for reading the system properties.
 *
 * @author Created by: Pplociennik at 21.03.2025 22:24
 */
public interface SystemPropertiesReaderService {

    /**
     * Reads a property with a given name from the system environment.
     *
     * @param aPropertyName
     *         a name of the property to read.
     * @return a value of the property.
     */
    String readProperty( @NonNull String aPropertyName );

    /**
     * Reads a property with a given name from the system environment.
     *
     * @param aProperty
     *         a property to read.
     * @return a value of the property.
     *
     * @throws
     */
    String readProperty( @NonNull SystemProperty aProperty );
}
