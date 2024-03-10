/*
 * MIT License
 *
 * Copyright (c) 2024 Przemysław Płóciennik
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in all
 * copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
 * SOFTWARE.
 */

package com.github.pplociennik.commons.utility.properties;

import com.github.pplociennik.commons.exc.ReadingPropertiesException;

import java.io.InputStream;
import java.util.Properties;

import static com.github.pplociennik.commons.lang.CommonsResExcMsgTranslationKey.READING_PROPERTIES_FAILED;
import static com.github.pplociennik.commons.utility.CustomObjects.requireNonEmpty;

/**
 * A util for reading properties.
 *
 * @author Created by: Pplociennik at 29.01.2022 20:15
 */
public abstract class PropertiesReader {

    /**
     * Returns a value for the specified property in the specified file.
     *
     * @param aPath
     *         a path to the properties file
     * @param aKey
     *         the property key
     * @return a value of the property
     * @throws IllegalStateException
     *         when the key was not found in the file
     */
    protected String readProperty( String aPath, String aKey ) {
        requireNonEmpty( aKey );
        requireNonEmpty( aPath );

        ClassLoader classLoader = getClass().getClassLoader();
        Properties properties = new Properties();
        InputStream inputStream;
        try {
            inputStream = classLoader.getResourceAsStream( aPath );
            properties.load( inputStream );
        } catch ( Exception aE ) {
            throw new ReadingPropertiesException( READING_PROPERTIES_FAILED, aPath );
        }

        var property = properties.getProperty( aKey );
        return validateProperty( property, aKey, aPath );
    }

    private String validateProperty( String aProperty, String aKey, String aPath ) {
        if ( aProperty == null ) {
            throw new IllegalStateException( "Property " + aKey + " has not been found in file '" + aPath + "'." );
        }
        return aProperty;
    }
}
