/*
 * MIT License
 *
 * Copyright (c) 2023 Przemysław Płóciennik
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

import com.github.pplociennik.commons.utility.CustomCollectors;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.List;

import static com.github.pplociennik.commons.utility.CustomCollectors.toSingleton;

/**
 * Unit tests for {@link CustomCollectors} class.
 *
 * @author Created by: Pplociennik at 30.01.2022 22:38
 */
class CustomCollectorsTest {

    private static final String EX_STRING_1 = "Simple test String 1";
    private static final String EX_STRING_2 = "Simple test String 2";
    private static final String EX_STRING_3 = "Simple test String 3";

    private static final List EMPTY_LIST = List.of();
    private static final List NULL = null;
    private static final List SINGLE_ELEMENT_LIST = List.of( EX_STRING_1 );
    private static final List TWO_ELEMENTS_LIST = List.of( EX_STRING_1, EX_STRING_2 );
    private static final List THREE_ELEMENTS_LIST = List.of( EX_STRING_1, EX_STRING_2, EX_STRING_3 );


    @Test
    void shouldReturnValidSingletonObject_whenToSingletonAndThereIsOnlyOneElementInTheStream() {

        var extractedString = SINGLE_ELEMENT_LIST.stream().collect( toSingleton() );

        Assertions.assertThat( extractedString ).isNotNull();
    }

    @Test
    void shouldThrowIllegalStateException_whenToSingletonAndThereTwoElementsInTheStream() {
        Assertions.assertThatThrownBy( () -> TWO_ELEMENTS_LIST.stream().collect( toSingleton() ) ).isInstanceOf( IllegalStateException.class );
    }

    @Test
    void shouldThrowIllegalStateException_whenToSingletonAndThereThreeElementsInTheStream() {
        Assertions.assertThatThrownBy( () -> THREE_ELEMENTS_LIST.stream().collect( toSingleton() ) ).isInstanceOf( IllegalStateException.class );
    }

    @Test
    void shouldThrowIllegalStateException_whenToSingletonAndThereAreNoElementsInTheList() {
        Assertions.assertThatThrownBy( () -> EMPTY_LIST.stream().collect( toSingleton() ) ).isInstanceOf( IllegalStateException.class );
    }

    @Test
    void shouldThrowNullPointerException_whenToSingletonAndCollectionIsNull() {
        Assertions.assertThatThrownBy( () -> NULL.stream().collect( toSingleton() ) ).isInstanceOf( NullPointerException.class );
    }

}