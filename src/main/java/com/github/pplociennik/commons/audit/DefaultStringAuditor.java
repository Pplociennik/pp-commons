package com.github.pplociennik.commons.audit;

import org.springframework.data.domain.AuditorAware;

import java.util.Optional;

/**
 * A default {@link String} auditor. Needs to be configured in the spring application starter using the @EnableJpaAuditing annotation.
 *
 * @author Created by: Pplociennik at 04.02.2025 21:35
 */
public class DefaultStringAuditor implements AuditorAware< String > {

    /**
     * {@inheritDoc}
     */
    @Override
    public Optional< String > getCurrentAuditor() {
        return Optional.of( "SYSTEM" );
    }
}
