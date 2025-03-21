package com.github.pplociennik.commons.service;

import com.github.pplociennik.commons.exc.properties.InvalidPropertyValueException;
import com.github.pplociennik.commons.exc.properties.NoSuchPropertyExistsException;
import com.github.pplociennik.commons.service.impl.SystemPropertiesReaderServiceImpl;
import com.github.pplociennik.commons.system.SystemProperty;
import org.junit.jupiter.api.Test;
import org.springframework.core.env.Environment;

import java.util.Collections;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

class SystemPropertiesReaderServiceTest {

    /**
     * Tests for SystemPropertiesReaderService which provides methods
     * to read system properties.
     */

    @Test
    void shouldReturnPropertyValue_whenPropertyNameIsValid() {
        // Arrange
        String propertyName = "test.property";
        String propertyValue = "testValue";
        SystemPropertiesReaderService service = mock( SystemPropertiesReaderService.class );
        when( service.readProperty( propertyName ) ).thenReturn( propertyValue );

        // Act
        String result = service.readProperty( propertyName );

        // Assert
        assertEquals( propertyValue, result );
    }

    @Test
    void shouldThrowException_whenPropertyNameIsNull() {
        // Arrange
        SystemPropertiesReaderService service = mock( SystemPropertiesReaderService.class );
        when( service.readProperty( ( String ) null ) ).thenThrow( NullPointerException.class );

        // Act & Assert
        assertThrows( NullPointerException.class, () -> service.readProperty( ( String ) null ) );
    }

    @Test
    void shouldReturnPropertyValue_whenSystemPropertyObjectIsValid() {
        // Arrange
        SystemProperty property = mock( SystemProperty.class );
        String propertyValue = "systemPropertyValue";
        SystemPropertiesReaderService service = mock( SystemPropertiesReaderService.class );
        when( service.readProperty( property ) ).thenReturn( propertyValue );

        // Act
        String result = service.readProperty( property );

        // Assert
        assertEquals( propertyValue, result );
    }

    @Test
    void shouldThrowException_whenSystemPropertyObjectIsNull() {
        // Arrange
        SystemPropertiesReaderService service = mock( SystemPropertiesReaderService.class );
        when( service.readProperty( ( SystemProperty ) null ) ).thenThrow( NullPointerException.class );

        // Act & Assert
        assertThrows( NullPointerException.class, () -> service.readProperty( ( SystemProperty ) null ) );
    }

    @Test
    void shouldReturnPropertyValue_whenPropertyExists() {
        // Arrange
        Environment mockEnvironment = mock( Environment.class );
        SystemPropertiesReaderServiceImpl service = new SystemPropertiesReaderServiceImpl( mockEnvironment );
        String propertyName = "test.property";
        String propertyValue = "testValue";

        when( mockEnvironment.containsProperty( propertyName ) ).thenReturn( true );
        when( mockEnvironment.getProperty( propertyName ) ).thenReturn( propertyValue );

        // Act
        String result = service.readProperty( propertyName );

        // Assert
        assertEquals( propertyValue, result );
        verify( mockEnvironment ).containsProperty( propertyName );
        verify( mockEnvironment ).getProperty( propertyName );
    }

    @Test
    void shouldThrowException_whenPropertyDoesNotExist() {
        // Arrange
        Environment mockEnvironment = mock( Environment.class );
        SystemPropertiesReaderServiceImpl service = new SystemPropertiesReaderServiceImpl( mockEnvironment );
        String propertyName = "non.existent.property";

        when( mockEnvironment.containsProperty( propertyName ) ).thenReturn( false );

        // Act & Assert
        assertThrows( NoSuchPropertyExistsException.class, () -> service.readProperty( propertyName ) );
        verify( mockEnvironment ).containsProperty( propertyName );
    }

    @Test
    void shouldReturnPropertyValue_whenSystemPropertyIsValid() {
        // Arrange
        Environment mockEnvironment = mock( Environment.class );
        SystemPropertiesReaderServiceImpl service = new SystemPropertiesReaderServiceImpl( mockEnvironment );
        SystemProperty mockSystemProperty = mock( SystemProperty.class );
        String propertyName = "test.property";
        String propertyValue = "validValue";

        when( mockSystemProperty.getName() ).thenReturn( propertyName );
        when( mockSystemProperty.getPossibleValues() ).thenReturn( Set.of( "validValue", "anotherValue" ) );
        when( mockEnvironment.containsProperty( propertyName ) ).thenReturn( true );
        when( mockEnvironment.getProperty( propertyName ) ).thenReturn( propertyValue );

        // Act
        String result = service.readProperty( mockSystemProperty );

        // Assert
        assertEquals( propertyValue, result );
        verify( mockEnvironment ).containsProperty( propertyName );
        verify( mockEnvironment ).getProperty( propertyName );
        verify( mockSystemProperty ).getPossibleValues();
    }

    @Test
    void shouldThrowException_whenSystemPropertyValueIsInvalid() {
        // Arrange
        Environment mockEnvironment = mock( Environment.class );
        SystemPropertiesReaderServiceImpl service = new SystemPropertiesReaderServiceImpl( mockEnvironment );
        SystemProperty mockSystemProperty = mock( SystemProperty.class );
        String propertyName = "test.property";
        String propertyValue = "invalidValue";

        when( mockSystemProperty.getName() ).thenReturn( propertyName );
        when( mockSystemProperty.getPossibleValues() ).thenReturn( Set.of( "validValue", "anotherValue" ) );
        when( mockEnvironment.containsProperty( propertyName ) ).thenReturn( true );
        when( mockEnvironment.getProperty( propertyName ) ).thenReturn( propertyValue );

        // Act & Assert
        assertThrows( InvalidPropertyValueException.class, () -> service.readProperty( mockSystemProperty ) );
        verify( mockEnvironment ).containsProperty( propertyName );
        verify( mockEnvironment ).getProperty( propertyName );
        verify( mockSystemProperty ).getPossibleValues();
    }

    @Test
    void shouldReturnPropertyValue_whenSystemPropertyHasEmptyPossibleValues() {
        // Arrange
        Environment mockEnvironment = mock( Environment.class );
        SystemPropertiesReaderServiceImpl service = new SystemPropertiesReaderServiceImpl( mockEnvironment );
        SystemProperty mockSystemProperty = mock( SystemProperty.class );
        String propertyName = "test.property";
        String propertyValue = "anyValue";

        when( mockSystemProperty.getName() ).thenReturn( propertyName );
        when( mockSystemProperty.getPossibleValues() ).thenReturn( Collections.emptySet() );
        when( mockEnvironment.containsProperty( propertyName ) ).thenReturn( true );
        when( mockEnvironment.getProperty( propertyName ) ).thenReturn( propertyValue );

        // Act
        String result = service.readProperty( mockSystemProperty );

        // Assert
        assertEquals( propertyValue, result );
        verify( mockEnvironment ).containsProperty( propertyName );
        verify( mockEnvironment ).getProperty( propertyName );
        verify( mockSystemProperty ).getPossibleValues();
    }
}