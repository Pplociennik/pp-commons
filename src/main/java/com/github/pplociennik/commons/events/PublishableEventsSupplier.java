/*
 * <!--
 *   ~ MIT License
 *   ~
 *   ~ Copyright (c) 2023 Przemysław Płóciennik
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

package com.github.pplociennik.commons.events;

import org.springframework.lang.NonNull;

import java.util.Locale;

/**
 * An interface sharing methods for creating {@link PublishableEvent} typed objects basing on the source objects.
 *
 * @author Created by: Pplociennik at 20.04.2023 18:24
 */
public interface PublishableEventsSupplier {

    /**
     * Returns an event based on the source object. The source object needs to be of the proper type defined in the
     * supplier's implementation.
     *
     * @param aSource
     *         a source object.
     * @return the {@link PublishableEvent} typed object to be published.
     */
    PublishableEvent getEvent( @NonNull Object aSource );

    /**
     * Returns an event based on the source object and using a locale specified. The source object needs to be of the
     * proper type defined in the supplier's implementation.
     *
     * @param aSource
     *         a source object.
     * @return the {@link PublishableEvent} typed object to be published.
     */
    PublishableEvent getEvent( @NonNull Object aSource, @NonNull Locale aLocale );

    /**
     * Returns a class being the required type of the source object.
     *
     * @return a required type of the source object.
     */
    Class< ? > getRequiredSourceType();
}
