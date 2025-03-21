package com.github.pplociennik.commons.service;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.time.ZoneId;
import java.time.ZonedDateTime;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

/**
 * Test class for TimeService.
 * The class verifies the correctness of the method getCurrentSystemDateTime, which retrieves the current date and time
 * based on the system's default ZoneId.
 */
class TimeServiceTest {

    @Test
    void shouldReturnCurrentSystemDateTimeCorrectly() {
        // Arrange
        TimeService mockTimeService = Mockito.mock( TimeService.class );
        ZoneId mockZoneId = ZoneId.of( "Europe/London" );
        ZonedDateTime expectedDateTime = ZonedDateTime.of( 2023, 10, 29, 12, 0, 0, 0, mockZoneId );

        when( mockTimeService.getSystemZoneId() ).thenReturn( mockZoneId );
        when( mockTimeService.getCurrentSystemDateTime() ).thenReturn( expectedDateTime );

        // Act
        ZonedDateTime actualDateTime = mockTimeService.getCurrentSystemDateTime();

        // Assert
        assertEquals( expectedDateTime, actualDateTime );
    }
}