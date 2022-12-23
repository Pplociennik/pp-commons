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

    private static final String SEPARATOR = "#";
    private static final String DATE_FORMAT = "yyMMdd";
    private static final String TIME_FORMAT = "hhmmss";

    public static String generateIdentifier(
            @NonNull Class< ? > aObjectType, @NonNull String aAdditionalSpecificString ) {
        validateNonNull( aObjectType, aAdditionalSpecificString );

        var currentDateTime = Instant.now();

        var className = aObjectType.getSimpleName();
        var currentDateFormatted = getFormattedDate( currentDateTime );
        var currentTimeFormatted = getFormattedTime( currentDateTime );

        return buildIdentifier( className, currentDateFormatted, currentTimeFormatted, aAdditionalSpecificString );
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
