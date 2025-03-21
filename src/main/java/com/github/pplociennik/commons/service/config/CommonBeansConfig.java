package com.github.pplociennik.commons.service.config;

import com.github.pplociennik.commons.service.SystemPropertiesReaderService;
import com.github.pplociennik.commons.service.TimeService;
import com.github.pplociennik.commons.service.impl.SystemPropertiesReaderServiceImpl;
import com.github.pplociennik.commons.service.impl.TimeServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;

/**
 * A configuration class defining common beans.
 *
 * @author Created by: Pplociennik at 21.03.2025 22:27
 */
@Configuration
public class CommonBeansConfig {

    /**
     * A reference to the Spring {@code Environment} object used for accessing environment-related properties
     * in the application. The {@code Environment} encapsulates property sources and provides the capability
     * to resolve properties dynamically from the environment.
     */
    private Environment environment;

    /**
     * Constructs a CommonBeansConfig instance with the specified {@code Environment}.
     *
     * @param environment
     *         the Spring {@code Environment} object used for accessing environment-related properties in the application.
     */
    @Autowired
    public CommonBeansConfig( Environment environment ) {
        this.environment = environment;
    }

    /**
     * Defines a bean of type {@link SystemPropertiesReaderService}, providing functionalities
     * for reading properties from the system environment.
     *
     * @return an implementation of {@link SystemPropertiesReaderService} that utilizes
     * the Spring {@code Environment} for property resolution.
     */
    @Bean
    public SystemPropertiesReaderService systemPropertiesReaderService() {
        return new SystemPropertiesReaderServiceImpl( environment );
    }

    /**
     * Defines a bean of type {@link TimeService}, which provides functionalities for managing and retrieving
     * system time and time zones. This implementation ensures the determination of the system time zone
     * either from system properties or by using a default time zone ("UTC") if not specified.
     *
     * @return an instance of {@link TimeService} configured with a {@link SystemPropertiesReaderService}
     * to read system-related properties.
     */
    @Bean
    public TimeService timeService() {
        return new TimeServiceImpl( systemPropertiesReaderService() );
    }
}
