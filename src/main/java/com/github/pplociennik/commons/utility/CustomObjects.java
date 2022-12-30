/*
 * MIT License
 *
 * Copyright (c) 2021 Przemysław Płóciennik
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in all
 * copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
 * SOFTWARE.
 */

package com.github.pplociennik.commons.utility;

import java.util.Collection;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Stream;

import static java.util.Objects.requireNonNull;

/**
 * A utility providing custom objects.
 *
 * @author Created by: Pplociennik at 30.01.2022 23:30
 */
public class CustomObjects {

    /**
     * Returns given String if it is not null or empty. Otherwise throws NullPointerException.
     *
     * @param aParam
     *         a String for checking
     * @return a given string if it is not null or empty
     * @throws NullPointerException
     *         when the given string is null or empty
     */
    public static String requireNonEmpty( String aParam ) {
        requireNonNull( aParam );
        if ( aParam.isEmpty() ) {
            throw new NullPointerException();
        }
        return aParam;
    }

    /**
     * Returns given collection if it is not null or empty. Otherwise, throws NullPointerException.
     *
     * @param aParam
     *         a Collection for checking
     * @return a given Collection if it is not null or empty
     * @throws NullPointerException
     *         when the given Collection is null or empty
     */
    public static < E > Collection< E > requireNonEmpty( Collection< E > aParam ) {
        requireNonNull( aParam );
        if ( aParam.isEmpty() ) {
            throw new NullPointerException();
        }
        return aParam;
    }

    /**
     * Returns a single element array of the parameter's type.
     *
     * @param aObject
     *         a single object
     * @param <T>
     *         an object to be wrapped
     * @return a single element array of the type T.
     */
    public static < T > T[] arrayOf( T aObject ) {
        requireNonNull( aObject );
        T[] ts = ( T[] ) new Object[]{ aObject };

        if ( ts.length != 1 ) {
            throw new IllegalStateException( "Only one element expected in the array!" );
        }

        return ts;
    }

    /**
     * Validates if any of the given objects is null.
     *
     * @param aObjects
     *         object to be validated.
     */
    public static void validateNonNull( Object... aObjects ) {
        Stream
                .of( aObjects )
                .forEach( Objects::requireNonNull );
    }

    /**
     * Unwraps the value of the optional.
     *
     * @param aOptionalObject
     *         an optional object to be unwrapped.
     * @param <T>
     *         the type of the optional object.
     * @return a value of the optional if present or null.
     */
    public static < T > T unwrap( Optional< T > aOptionalObject ) {
        requireNonNull( aOptionalObject );
        return aOptionalObject.orElse( null );
    }

    /**
     * Validates an object's type.
     *
     * @param aSourceObject
     *         an object to be validated.
     * @param aRequiredType
     *         the expected type of the object.
     * @throws IllegalArgumentException
     *         when the object has incorrect type.
     */
    public static void validateType( Object aSourceObject, Class< ? > aRequiredType ) {
        Optional
                .of( aSourceObject )
                .filter( aRequiredType::isInstance )
                .orElseThrow( () -> new IllegalArgumentException( "Not a valid object type." ) );
    }
}
