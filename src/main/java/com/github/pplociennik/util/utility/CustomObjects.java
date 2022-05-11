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

package com.github.pplociennik.util.utility;

import java.util.Collection;

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
}
