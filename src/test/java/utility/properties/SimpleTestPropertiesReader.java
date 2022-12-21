/*
 * MIT License
 *
 * Copyright (c) 2021 Przemysław Płóciennik
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

package utility.properties;

import com.github.pplociennik.commons.utility.properties.PropertiesReader;

/**
 * A simple test implementation of {@link PropertiesReader} abstract class.
 *
 * @author Created by: Pplociennik at 30.01.2022 23:11
 */
class SimpleTestPropertiesReader extends PropertiesReader {

    /**
     * Simple test caller of a method from superclass.
     *
     * @param aKey
     *         a key of property
     * @param aPath
     *         a path to the properties file
     * @return a value of the property
     * @throws IllegalStateException
     *         when the key was not found in the file
     */
    String getProperty( String aKey, String aPath ) {
        return readProperty( aPath, aKey );
    }
}
