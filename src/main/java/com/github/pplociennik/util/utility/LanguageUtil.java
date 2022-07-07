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

package com.github.pplociennik.util.utility;


import com.github.pplociennik.util.lang.TranslationKey;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.context.support.ResourceBundleMessageSource;
import org.springframework.lang.NonNull;

import java.util.Locale;

import static java.util.Objects.requireNonNull;


/**
 * A utility for accessing current system {@link Locale} and translations.
 *
 * @author Created by: Pplociennik at 29.01.2022 19:30
 */
public class LanguageUtil {

    /**
     * Returns current system {@link Locale}.
     *
     * @return a locale
     */
    public static Locale getLocale() {
        return LocaleContextHolder.getLocale();
    }

    /**
     * Sets the specified {@link Locale} in the application's context.
     *
     * @param aLocale
     *         a locale to be set
     */
    public static void setLocale( @NonNull Locale aLocale ) {
        requireNonNull( aLocale );
        LocaleContextHolder.setLocale( aLocale );
    }

    /**
     * Returns translated message by a key.
     *
     * @param aKey
     *         a message key
     * @return the translated message
     */
    public static String getLocalizedMessage( TranslationKey aKey ) {
        return getLocalizedMessage( aKey, ( Object[] ) null );
    }

    /**
     * Returns parameterized translated exception message by a key.
     *
     * @param aKey
     *         a message key
     * @param args
     *         message arguments
     * @return the translated message
     */
    public static String getLocalizedMessage( TranslationKey aKey, Object[] args ) {
        var messageSource = new ResourceBundleMessageSource();
        messageSource.setBasename( aKey.getTranslationsSourcePropertyName() );
        return messageSource.getMessage( aKey.toString(), args, getLocale() );
    }

    /**
     * Returns a translated message by a key and the locale.
     *
     * @param aKey
     *         a message key
     * @param aLocale
     *         a locale
     * @return the translated message
     */
    public static String getLocalizedMessage( TranslationKey aKey, Locale aLocale ) {
        return getLocalizedMessage( aKey, aLocale, null );
    }

    /**
     * Returns parameterized translated message by a key and the locale.
     *
     * @param aKey
     *         a message key
     * @param aLocale
     *         a locale
     * @param args
     *         message arguments
     * @return the translated message
     */
    public static String getLocalizedMessage( TranslationKey aKey, Locale aLocale, Object[] args ) {
        var messageSource = new ResourceBundleMessageSource();
        messageSource.setBasename( aKey.getTranslationsSourcePropertyName() );
        return messageSource.getMessage( aKey.toString(), args, aLocale );
    }
}
