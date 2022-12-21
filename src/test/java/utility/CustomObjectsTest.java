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

package utility;

import com.github.pplociennik.commons.utility.CustomObjects;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import java.util.Collection;
import java.util.Set;

import static com.github.pplociennik.commons.utility.CustomObjects.requireNonEmpty;


/**
 * Unit tests for {@link CustomObjects} class.
 *
 * @author Created by: Pplociennik at 30.01.2022 23:45
 */
class CustomObjectsTest {

    private static final String NON_EMPTY_STRING_1 = "Simple test non empty String 1";
    private static final String NON_EMPTY_STRING_2 = "Simple test non empty String 2";
    private static final String NON_EMPTY_STRING_3 = "Simple test non empty String 3";
    private static final String EMPTY_STRING = "";
    private static final Set NON_EMPTY_SINGLE_ELEMENT_SET = Set.of( NON_EMPTY_STRING_1 );
    private static final Set NON_EMPTY_MULTIPLE_ELEMENTS_SET = Set.of( NON_EMPTY_STRING_1, NON_EMPTY_STRING_2, NON_EMPTY_STRING_3 );
    private static final Set EMPTY_SET = Set.of();

    @ParameterizedTest
    @ValueSource( strings = { NON_EMPTY_STRING_1, NON_EMPTY_STRING_2, NON_EMPTY_STRING_3 } )
    void shouldReturnExactString_whenParamNotNullOrEmpty( String aParam ) {
        var result = requireNonEmpty( aParam );
        Assertions.assertThat( result ).isEqualTo( aParam );
    }

    @Test
    void shouldThrowNullPointerException_whenStringIsNull() {
        Assertions.assertThatThrownBy( () -> requireNonEmpty( ( String ) null ) ).isInstanceOf( NullPointerException.class );
    }

    @Test
    void shouldThrowNullPointerException_whenStringIsEmpty() {
        Assertions.assertThatThrownBy( () -> requireNonEmpty( EMPTY_STRING ) ).isInstanceOf( NullPointerException.class );
    }

    @Test
    void shouldReturnExactCollection_whenCollectionHasOneElement() {
        var result = requireNonEmpty( NON_EMPTY_SINGLE_ELEMENT_SET );
        Assertions.assertThat( result ).isEqualTo( NON_EMPTY_SINGLE_ELEMENT_SET );
    }

    @Test
    void shouldReturnExactCollection_whenCollectionHasMoreThanOneElement() {
        var result = requireNonEmpty( NON_EMPTY_MULTIPLE_ELEMENTS_SET );
        Assertions.assertThat( result ).isEqualTo( NON_EMPTY_MULTIPLE_ELEMENTS_SET );
    }

    @Test
    void shouldThrowNullPointerException_whenCollectionIsNull() {
        Assertions.assertThatThrownBy( () -> requireNonEmpty( ( Collection< Object > ) null ) ).isInstanceOf( NullPointerException.class );
    }

    @Test
    void shouldThrowNullPointerException_whenCollectionIsEmpty() {
        Assertions.assertThatThrownBy( () -> requireNonEmpty( EMPTY_SET ) ).isInstanceOf( NullPointerException.class );
    }


}