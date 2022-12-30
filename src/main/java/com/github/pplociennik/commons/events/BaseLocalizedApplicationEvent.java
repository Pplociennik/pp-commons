package com.github.pplociennik.commons.events;

import lombok.AccessLevel;
import lombok.Getter;
import org.springframework.context.ApplicationEvent;
import org.springframework.lang.NonNull;

import java.util.Locale;

import static java.util.Objects.requireNonNull;

/**
 * Base application event class containing a locale.
 *
 * @author Created by: Pplociennik at 06.08.2022 22:58
 */
public abstract class BaseLocalizedApplicationEvent extends ApplicationEvent {

    @Getter( AccessLevel.PUBLIC )
    protected final Locale locale;

    protected BaseLocalizedApplicationEvent( @NonNull Object aSource, @NonNull Locale aLocale ) {
        super( aSource );
        locale = requireNonNull( aLocale );
    }
}
