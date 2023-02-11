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

package com.github.pplociennik.commons.utility.identifier;

import org.springframework.lang.NonNull;

import java.time.Instant;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.Objects;
import java.util.stream.Stream;

import static com.github.pplociennik.commons.utility.CustomObjects.validateNonNull;
import static java.util.function.Predicate.not;
import static java.util.stream.Collectors.joining;

/**
 * A generator of the unique objects' identifiers.
 *
 * @author Created by: Pplociennik at 22.12.2022 20:23
 */
public class UniqueIdentifierGenerator {

    /**
     * A separator dividing segments of the identifier.
     */
    private static final String SEPARATOR = "#";

    /**
     * A format of the date being used for the identifier's creation.
     */
    private static final String DATE_FORMAT = "yyMMdd";

    /**
     * A format of the time being used for the identifier's creation.
     */
    private static final String TIME_FORMAT = "hhmmss";

    /**
     * Returns the unique identifier for the object basing on the object's type and the unique specific information.
     *
     * @param aObject
     *         an object being identified.
     * @param aObjectTypeSpecifier
     *         a special specific information according to the object being identified.
     * @return a unique identifier for the object.
     */
    public static < T > String generateIdentifier(
            @NonNull T aObject, @NonNull ObjectTypeSpecifier< T > aObjectTypeSpecifier ) {
        validateNonNull( aObject, aObjectTypeSpecifier );

        var currentDateTime = Instant.now();

        var className = aObject
                .getClass()
                .getSimpleName();
        var currentDateFormatted = getFormattedDate( currentDateTime );
        var currentTimeFormatted = getFormattedTime( currentDateTime );

        return buildIdentifier( className, currentDateFormatted, currentTimeFormatted,
                                aObjectTypeSpecifier.get( aObject ) );
    }

    public static < T > String generateCustomIdentifier(
            @NonNull String aCustomObjectTypeName, @NonNull T aObject,
            @NonNull ObjectTypeSpecifier< T > aObjectTypeSpecifier ) {
        validateNonNull( aCustomObjectTypeName, aObject, aObjectTypeSpecifier );

        var currentDateTime = Instant.now();

        var currentDateFormatted = getFormattedDate( currentDateTime );
        var currentTimeFormatted = getFormattedTime( currentDateTime );

        return buildIdentifier( aCustomObjectTypeName, currentDateFormatted, currentTimeFormatted,
                                aObjectTypeSpecifier.get( aObject ) );
    }

    private static String buildIdentifier(
            String... aElements ) {
        return Stream
                .of( aElements )
                .filter( Objects::nonNull )
                .filter( not( String::isBlank ) )
                .collect( joining( SEPARATOR ) );
    }

    private static String getFormattedTime( Instant aCurrentDateTime ) {
        var formatter = DateTimeFormatter
                .ofPattern( TIME_FORMAT )
                .withZone( ZoneId.systemDefault() );
        return formatter.format( aCurrentDateTime );
    }

    private static String getFormattedDate( Instant aCurrentDateTime ) {
        var formatter = DateTimeFormatter
                .ofPattern( DATE_FORMAT )
                .withZone( ZoneId.systemDefault() );
        return formatter.format( aCurrentDateTime );
    }
}
