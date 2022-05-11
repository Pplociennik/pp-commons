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

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.springframework.context.i18n.LocaleContextHolder;

import java.util.Locale;

import static com.github.pplociennik.auth.common.lang.AuthResExcMsgTranslationKey.*;

/**
 * Unit tests for {@link LanguageUtil}.
 *
 * @author Created by: Pplociennik at 30.01.2022 11:22
 */
class LanguageUtilTest {

    @ParameterizedTest
    @CsvSource( value = { "pl:pl", "de:de", "en:en" }, delimiter = ':' )
    void shouldChangeLocaleSuccessfully( String aInputLocaleId, String aExpectedLocaleId ) {

        var localeForBeingSet = new Locale( aInputLocaleId );
        LocaleContextHolder.setLocale( localeForBeingSet );

        var newLocale = LanguageUtil.getLocale();
        Assertions.assertThat( newLocale.getLanguage() ).isEqualTo( aExpectedLocaleId );
    }

    @Test
    void shouldReturnEnglishTranslation_whenLocaleSetToEnglish() {

        var expectedTranslation = "The provided email address is already in use!";

        var localeForBeingSet = Locale.ENGLISH;
        LocaleContextHolder.setLocale( localeForBeingSet );

        var translation = LanguageUtil.getLocalizedMessage( AUTHENTICATION_EMAIL_ALREADY_IN_USE );
        Assertions.assertThat( translation ).isEqualTo( expectedTranslation );
    }

    @Test
    void shouldReturnGermanTranslation_whenLocaleSetToGerman() {

        var expectedTranslation = "E-Mail-Adresse entspricht nicht dem angegebenen Muster!";

        var localeForBeingSet = Locale.GERMAN;
        LocaleContextHolder.setLocale( localeForBeingSet );

        var translation = LanguageUtil.getLocalizedMessage( AUTHENTICATION_EMAIL_NOT_MATCHING_PATTERN );
        Assertions.assertThat( translation ).isEqualTo( expectedTranslation );
    }

    @Test
    void shouldReturnPolishTranslation_whenLocaleSetToPolish() {

        var expectedTranslation = "Niepoprawny adres email!";

        var localeForBeingSet = new Locale( "pl" );
        LocaleContextHolder.setLocale( localeForBeingSet );

        var translation = LanguageUtil.getLocalizedMessage( AUTHENTICATION_EMAIL_NOT_MATCHING_PATTERN );
        Assertions.assertThat( translation ).isEqualTo( expectedTranslation );
    }

    @Test
    void shouldReturnValidTranslationWithParams() {

        var testReasonMessage = "Test reason message";
        var params = new Object[]{ testReasonMessage };

        var expectedTranslation = "Unexpected exception! Reason: Test reason message";

        var localeForBeingSet = Locale.ENGLISH;
        LocaleContextHolder.setLocale( localeForBeingSet );

        var translation = LanguageUtil.getLocalizedMessage( UNEXPECTED_EXCEPTION, params );
        Assertions.assertThat( translation ).isEqualTo( expectedTranslation );
    }

}