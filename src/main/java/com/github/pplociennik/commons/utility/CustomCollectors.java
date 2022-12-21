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

import java.util.stream.Collector;
import java.util.stream.Collectors;

/**
 * A utility providing custom collectors.
 *
 * @author Created by: Pplociennik at 30.01.2022 10:10
 */
public class CustomCollectors {

    /**
     * Returns a singleton element from {@link java.util.stream.Stream}.
     *
     * @param <T>
     *         a type of the object being returned
     * @return a singleton element from stream
     * @throws {@link
     *         IllegalStateException} when there is more or less elements in the stream than one
     */
    public static < T > Collector< T, ?, T > toSingleton() {
        return Collectors.collectingAndThen( Collectors.toList(), list -> {
            if ( list.size() != 1 ) {
                throw new IllegalStateException( "There should be exactly one element in the stream!" );
            }
            return list.get( 0 );
        } );
    }
}
