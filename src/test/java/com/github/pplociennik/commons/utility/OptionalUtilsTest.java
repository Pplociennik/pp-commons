package com.github.pplociennik.commons.utility;

import org.junit.jupiter.api.Test;

import java.util.NoSuchElementException;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class OptionalUtilsTest {

    /**
     * Tests for the {@link OptionalUtils#getMandatoryValue(Optional)} method.
     * This method is expected to retrieve the value contained in an Optional, throwing
     * a NoSuchElementException if the Optional is empty.
     */

    @Test
    void shouldReturnValueWhenOptionalContainsValue() {
        // Arrange
        String expectedValue = "TestValue";
        Optional< String > input = Optional.of( expectedValue );

        // Act
        String result = OptionalUtils.getMandatoryValue( input );

        // Assert
        assertEquals( expectedValue, result );
    }

    @Test
    void shouldThrowExceptionWhenOptionalIsEmpty() {
        // Arrange
        Optional< String > input = Optional.empty();

        // Act & Assert
        assertThrows( NoSuchElementException.class, () -> OptionalUtils.getMandatoryValue( input ) );
    }
}