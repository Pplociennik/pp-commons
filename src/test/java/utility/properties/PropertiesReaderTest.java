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

import com.github.pplociennik.commons.exc.ReadingPropertiesException;
import com.github.pplociennik.commons.utility.properties.PropertiesReader;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

/**
 * Unit tests for {@link PropertiesReader} class.
 *
 * @author Created by: Pplociennik at 30.01.2022 22:58
 */
class PropertiesReaderTest {

    private static final String VALID_PROPERTIES_FILE_PATH = "lang/properties/translation.properties";
    private static final String VALID_PROPERTY_KEY = "message.exc.language.basename";
    private static final String INVALID_PROPERTY_KEY = "simple_invalid_key";
    private static final String EMPTY_PROPERTY_KEY = "";
    private static final String INVALID_PROPERTIES_FILE_PATH = "simple_invalid_path";
    private static final String EMPTY_PROPERTIES_FILE_PATH = "";

    private static final SimpleTestPropertiesReader TEST_READER = new SimpleTestPropertiesReader();

    @Test
    void shouldReturnValidValue_whenValidFilePathAndPropertyKey() {
        var expectedValue = "lang/CommonsResExcMsg";
        var value = TEST_READER.getProperty( VALID_PROPERTY_KEY, VALID_PROPERTIES_FILE_PATH );
        Assertions.assertThat( value ).isEqualTo( expectedValue );
    }

    @Test
    void shouldThrowIllegalStateException_whenValidPropertiesFilePathButInvalidPropertyKey() {
        Assertions.assertThatThrownBy( () -> TEST_READER.getProperty( INVALID_PROPERTY_KEY, VALID_PROPERTIES_FILE_PATH ) ).isInstanceOf( IllegalStateException.class );
    }

    @Test
    void shouldThrowReadingPropertiesException_whenInvalidPropertiesFilePathButValidPropertyKey() {
        Assertions.assertThatThrownBy( () -> TEST_READER.getProperty( VALID_PROPERTY_KEY, INVALID_PROPERTIES_FILE_PATH ) ).isInstanceOf( ReadingPropertiesException.class );
    }

    @Test
    void shouldThrowReadingPropertiesException_whenInvalidPropertiesFilePathAndInvalidPropertyKey() {
        Assertions.assertThatThrownBy( () -> TEST_READER.getProperty( INVALID_PROPERTY_KEY, INVALID_PROPERTIES_FILE_PATH ) ).isInstanceOf( ReadingPropertiesException.class );
    }

    @Test
    void shouldThrowNullPointerException_whenValidPropertiesFilePathButPropertyKeyNull() {
        Assertions.assertThatThrownBy( () -> TEST_READER.getProperty( null, VALID_PROPERTIES_FILE_PATH ) ).isInstanceOf( NullPointerException.class );
    }

    @Test
    void shouldThrowNullPointerException_whenInvalidPropertiesFilePathButPropertyKeyNull() {
        Assertions.assertThatThrownBy( () -> TEST_READER.getProperty( null, INVALID_PROPERTIES_FILE_PATH ) ).isInstanceOf( NullPointerException.class );
    }

    @Test
    void shouldThrowNullPointerException_whenValidPropertyKeyPathButPropertiesFilePathNull() {
        Assertions.assertThatThrownBy( () -> TEST_READER.getProperty( VALID_PROPERTY_KEY, null ) ).isInstanceOf( NullPointerException.class );
    }

    @Test
    void shouldThrowNullPointerException_whenInvalidPropertyKeyPathButPropertiesFilePathNull() {
        Assertions.assertThatThrownBy( () -> TEST_READER.getProperty( INVALID_PROPERTY_KEY, null ) ).isInstanceOf( NullPointerException.class );
    }

    @Test
    void shouldThrowNullPointerException_whenPropertyKeyPathAndPropertiesFilePathNull() {
        Assertions.assertThatThrownBy( () -> TEST_READER.getProperty( null, null ) ).isInstanceOf( NullPointerException.class );
    }

    @Test
    void shouldThrowNullPointerException_whenValidPropertiesFilePathButPropertyKeyEmpty() {
        Assertions.assertThatThrownBy( () -> TEST_READER.getProperty( null, EMPTY_PROPERTIES_FILE_PATH ) ).isInstanceOf( NullPointerException.class );
    }

    @Test
    void shouldThrowNullPointerException_whenInvalidPropertiesFilePathButPropertyKeyEmpty() {
        Assertions.assertThatThrownBy( () -> TEST_READER.getProperty( EMPTY_PROPERTY_KEY, INVALID_PROPERTIES_FILE_PATH ) ).isInstanceOf( NullPointerException.class );
    }

    @Test
    void shouldThrowNullPointerException_whenValidPropertyKeyPathButPropertiesFilePathEmpty() {
        Assertions.assertThatThrownBy( () -> TEST_READER.getProperty( VALID_PROPERTY_KEY, EMPTY_PROPERTIES_FILE_PATH ) ).isInstanceOf( NullPointerException.class );
    }

    @Test
    void shouldThrowNullPointerException_whenInvalidPropertyKeyPathButPropertiesFilePathEmpty() {
        Assertions.assertThatThrownBy( () -> TEST_READER.getProperty( INVALID_PROPERTY_KEY, EMPTY_PROPERTIES_FILE_PATH ) ).isInstanceOf( NullPointerException.class );
    }

    @Test
    void shouldThrowNullPointerException_whenPropertyKeyPathAndPropertiesFilePathEmpty() {
        Assertions.assertThatThrownBy( () -> TEST_READER.getProperty( EMPTY_PROPERTY_KEY, EMPTY_PROPERTIES_FILE_PATH ) ).isInstanceOf( NullPointerException.class );
    }
}