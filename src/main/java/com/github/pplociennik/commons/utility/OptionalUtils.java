package com.github.pplociennik.commons.utility;

import java.util.NoSuchElementException;
import java.util.Optional;

/**
 * Utility class for working with Optional objects, providing simple methods to handle Optional values with or without default fallbacks.
 *
 * @author Created by: Pplociennik at 02.04.2025 19:55
 */
public class OptionalUtils {

    /**
     * Retrieves the value from the provided Optional if it is present. Throws an exception if the Optional is empty.
     *
     * @param aOptional
     *         the Optional instance to retrieve the value from.
     * @param <T>
     *         the type of the value contained in the Optional.
     * @return the value inside the Optional.
     *
     * @throws NoSuchElementException
     *         if the Optional is empty.
     */
    public static < T > T getMandatoryValue( Optional< T > aOptional ) {
        return aOptional.orElseThrow();
    }

    /**
     * Retrieves the value from the provided Optional if it is present. Returns null if the Optional is empty.
     *
     * @param aOptional
     *         the Optional instance to retrieve the value from.
     * @param <T>
     *         the type of the value contained in the Optional.
     * @return the value inside the Optional or null if the Optional is empty.
     */
    public static < T > T getOptionalValue( Optional< T > aOptional ) {
        return aOptional.orElse( null );
    }
}
