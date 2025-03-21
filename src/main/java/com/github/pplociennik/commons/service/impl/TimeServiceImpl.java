package com.github.pplociennik.commons.service.impl;

import com.github.pplociennik.commons.service.SystemPropertiesReaderService;
import com.github.pplociennik.commons.service.TimeService;

import java.time.ZoneId;
import java.time.ZonedDateTime;

import static com.github.pplociennik.commons.system.CommonSystemProperties.SYSTEM_JPA_TIME_ZONE;

/**
 * Implementation of the {@link TimeService} interface, providing functionalities to handle system-related time data.
 * This class retrieves the system time zone from the properties or a default value ("UTC") if no system property
 * is set. It also provides the current system date and time adjusted to the determined system time zone.
 *
 * <p>Note: This implementation relies on {@link SystemPropertiesReaderService} to read system configuration
 * properties.</p>
 *
 * @author Created by: Pplociennik at 21.03.2025 22:32
 */
public class TimeServiceImpl implements TimeService {

    private static final String DEFAULT_SYSTEM_TIME_ZONE = "UTC";
    private final SystemPropertiesReaderService systemPropertiesReader;

    public TimeServiceImpl( SystemPropertiesReaderService aPropertiesProvider ) {
        systemPropertiesReader = aPropertiesProvider;
    }

    /**
     * Returns the system time zone. The time zone should be set in the system properties. In case if it is not, the
     * method returns a default system time zone "UTC".
     *
     * @return the system time zone
     */
    @Override
    public ZoneId getSystemZoneId() {
        var systemTimeZone = getSystemTimeZone();

        return systemTimeZone.isBlank()
                ? ZoneId.of( DEFAULT_SYSTEM_TIME_ZONE )
                : ZoneId.of( systemTimeZone );
    }

    /**
     * Returns current system date and time with system time zone.
     *
     * @return current zoned date time
     */
    @Override
    public ZonedDateTime getCurrentSystemDateTime() {
        return ZonedDateTime.now( getSystemZoneId() );
    }

    private String getSystemTimeZone() {
        return systemPropertiesReader.readProperty( SYSTEM_JPA_TIME_ZONE );
    }
}
