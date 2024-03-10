/*
 * <!--
 *   ~ MIT License
 *   ~
 *   ~ Copyright (c) 2024 Przemysław Płóciennik
 *   ~
 *   ~ Permission is hereby granted, free of charge, to any person obtaining a copy
 *   ~ of this software and associated documentation files (the "Software"), to deal
 *   ~ in the Software without restriction, including without limitation the rights
 *   ~ to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 *   ~ copies of the Software, and to permit persons to whom the Software is
 *   ~ furnished to do so, subject to the following conditions:
 *   ~
 *   ~ The above copyright notice and this permission notice shall be included in all
 *   ~ copies or substantial portions of the Software.
 *   ~
 *   ~ THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 *   ~ IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 *   ~ FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 *   ~ AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 *   ~ LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 *   ~ OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
 *   ~ SOFTWARE.
 *   -->
 */

package com.github.pplociennik.commons.utility;

import org.springframework.lang.NonNull;

import java.util.Objects;

import static java.util.Objects.requireNonNull;

/**
 * An immutable generic object representing a pair of generic typed objects. Both objects are mandatory.
 *
 * @author Created by: Pplociennik at 08.05.2022 00:01
 */
public class GenericPair< E, T > {

    /**
     * A former object of the pair.
     */
    private final E former;

    /**
     * A latter object of the pair.
     */
    private final T latter;

    public GenericPair( @NonNull E aFormer, @NonNull T aLatter ) {
        former = requireNonNull( aFormer );
        latter = requireNonNull( aLatter );
    }

    /**
     * Returns the former object from the pair.
     */
    public E getFormer() {
        return former;
    }

    /**
     * Returns the second object from the pair.
     */
    public T getLatter() {
        return latter;
    }

    @Override
    public int hashCode() {
        return Objects.hash( former, latter );
    }

    @Override
    public boolean equals( Object aO ) {
        if ( this == aO ) {
            return true;
        }
        if ( aO == null || getClass() != aO.getClass() ) {
            return false;
        }
        GenericPair< ?, ? > that = ( GenericPair< ?, ? > ) aO;
        return Objects.equals( former, that.former ) && Objects.equals( latter, that.latter );
    }

    @Override
    public String toString() {
        return "GenericPair{" + "former=" + former + ", latter=" + latter + '}';
    }
}
