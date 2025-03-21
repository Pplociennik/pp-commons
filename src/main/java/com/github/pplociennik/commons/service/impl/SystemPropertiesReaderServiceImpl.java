package com.github.pplociennik.commons.service.impl;

import com.github.pplociennik.commons.exc.properties.InvalidPropertyValueException;
import com.github.pplociennik.commons.exc.properties.NoSuchPropertyExistsException;
import com.github.pplociennik.commons.lang.CommonsResExcMsgTranslationKey;
import com.github.pplociennik.commons.service.SystemPropertiesReaderService;
import com.github.pplociennik.commons.system.SystemProperty;
import lombok.NonNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Service;

import java.util.Set;

import static java.util.Objects.requireNonNull;

/**
 * Implementation of the {@link SystemPropertiesReaderService} that provides functionality
 * for reading and validating system properties using the Spring {@code Environment}.
 * This class ensures that property values exist and conform to expected constraints
 * for application configuration and behavior.
 * <p>
 * It interacts with the Spring {@code Environment} to retrieve the values of properties
 * defined in application properties or system environment variables. Validation is also
 * performed to ensure correctness and compliance with defined property constraints.
 * <p>
 * Key responsibilities include:
 * - Reading property values by a property name or {@link SystemProperty} instance.
 * - Verifying the existence of required properties.
 * - Ensuring property values match a predefined set of acceptable values.
 * <p>
 * This service is defined as a Spring-managed bean under the {@link Service} stereotype
 * annotation and reads properties from the default `application.properties` defined in the classpath.
 *
 * @author Created by: Pplociennik
 * @date 21.03.2025 22:25
 */
@Service
@PropertySource( "classpath:application.properties" )
public class SystemPropertiesReaderServiceImpl implements SystemPropertiesReaderService {

    /**
     * The {@code Environment} instance provided by Spring Framework.
     * This variable is used for accessing application properties and environment variables.
     */
    private Environment environment;

    /**
     * Constructs an instance of {@code SystemPropertiesReaderServiceImpl}.
     *
     * @param environment
     *         the Spring {@code Environment} instance used for accessing application properties and environment variables.
     */
    @Autowired
    public SystemPropertiesReaderServiceImpl( Environment environment ) {
        this.environment = environment;
    }

    /**
     * Reads the value of the specified property name from the system environment.
     *
     * @param aPropertyName
     *         the name of the property to read.
     * @return the value of the specified property, or null if the property does not exist.
     *
     * @throws NullPointerException
     *         if the provided property name is null.
     * @throws NoSuchPropertyExistsException
     *         if the specified property does not exist in the system environment.
     */
    @Override
    public String readProperty( @NonNull String aPropertyName ) {
        requireNonNull( aPropertyName );
        verifyProperty( aPropertyName );
        return environment.getProperty( aPropertyName );
    }

    /**
     * Reads the value of a specified system property.
     *
     * @param aProperty
     *         the system property to read.
     * @return the value of the specified property.
     *
     * @throws NullPointerException
     *         if the provided property instance is null.
     * @throws NoSuchPropertyExistsException
     *         if the specified property does not exist in the system environment.
     * @throws InvalidPropertyValueException
     *         if the value of the specified property is not valid.
     */
    @Override
    public String readProperty( @NonNull SystemProperty aProperty ) {
        requireNonNull( aProperty );

        String value = readProperty( aProperty.getName() );
        verifyValue( aProperty, value );
        return value;
    }

    /**
     * Verifies the existence of a specified property in the system environment.
     *
     * @param aPropertyName
     *         the name of the property to verify.
     * @return true if the property exists.
     *
     * @throws NullPointerException
     *         if the provided property name is null.
     * @throws NoSuchPropertyExistsException
     *         if the specified property does not exist in the system environment.
     */
    private boolean verifyProperty( @NonNull String aPropertyName ) {
        requireNonNull( aPropertyName );

        if ( !environment.containsProperty( aPropertyName ) ) {
            throw new NoSuchPropertyExistsException( CommonsResExcMsgTranslationKey.NO_SUCH_SYSTEM_PROPERTY, aPropertyName );
        }
        return true;
    }

    /**
     * Verifies whether the provided value is a valid option for the given system property.
     *
     * @param aProperty
     *         the system property whose possible values are being verified against.
     * @param aValue
     *         the value to verify.
     * @return true if the provided value is valid for the given property.
     *
     * @throws InvalidPropertyValueException
     *         if the provided value is not among the possible values of the property.
     * @throws NullPointerException
     *         if the provided property or value is null.
     */
    private boolean verifyValue( @NonNull SystemProperty aProperty, @NonNull String aValue ) {
        requireNonNull( aProperty );
        requireNonNull( aValue );

        Set< String > possibleValues = aProperty.getPossibleValues();
        if ( !possibleValues.isEmpty() && !possibleValues.contains( aValue ) ) {
            throw new InvalidPropertyValueException( CommonsResExcMsgTranslationKey.INVALID_PROPERTY_VALUE, aProperty.getName(), aValue, possibleValues.toString() );
        }
        return true;
    }
}
