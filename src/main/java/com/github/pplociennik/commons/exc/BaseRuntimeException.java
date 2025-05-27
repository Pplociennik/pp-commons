/*
 * <!--
 *   ~ MIT License
 *   ~
 *   ~ Copyright (c) 2024 Przemysław Płóciennik
 *   ~
 *   ~ Permission is hereby granted, free of charge, to any person obtaining a copy
 *   ~ of this software and associated documentation files (the "Software"), to deal
 *   ~ in the Software without restriction, including without limitation the rights
 *   ~ to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 *   ~ copies of the Software, and to permit persons to whom the Software is
 *   ~ furnished to do so, subject to the following conditions:
 *   ~
 *   ~ The above copyright notice and this permission notice shall be included in all
 *   ~ copies or substantial portions of the Software.
 *   ~
 *   ~ THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 *   ~ IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 *   ~ FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 *   ~ AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 *   ~ LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 *   ~ OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
 *   ~ SOFTWARE.
 *   -->
 */

package com.github.pplociennik.commons.exc;

import com.github.pplociennik.commons.lang.TranslationKey;
import com.github.pplociennik.commons.utility.LanguageUtil;

import java.io.Serializable;

/**
 * An exception class being a base for another exceptions. Holds the {@link TranslationKey} and message parameters.
 *
 * @author Created by: Pplociennik at 10.05.2022 19:24
 */
public class BaseRuntimeException extends RuntimeException {

    /**
     * A message key.
     */
    private TranslationKey messageKey;

    /**
     * Message parameters.
     */
    private Serializable[] params;

    /**
     * Constructs a new {@code BaseRuntimeException} with a specified translation key and parameters.
     * The localized message is generated using the provided translation key and parameters.
     *
     * @param aTranslationKey
     *         the translation key used to retrieve the localized message
     * @param aParams
     *         optional parameters for constructing the localized message
     */
    public BaseRuntimeException( TranslationKey aTranslationKey, Serializable... aParams ) {
        super( LanguageUtil.getLocalizedMessage( aTranslationKey, aParams ) );
        messageKey = aTranslationKey;
        params = aParams;
    }

    /**
     * Constructs a new {@code BaseRuntimeException} with a specified translation key.
     * The localized message is generated using the provided translation key.
     *
     * @param aTranslationKey
     *         the translation key used to retrieve the localized message
     */
    public BaseRuntimeException( TranslationKey aTranslationKey ) {
        super( LanguageUtil.getLocalizedMessage( aTranslationKey ) );
        messageKey = aTranslationKey;
    }

    /**
     * Constructs a new {@code BaseRuntimeException} with the specified cause, translation key, and parameters.
     * This constructor allows specifying a throwable cause, a translation key for the localized message,
     * and optional parameters for constructing the localized message.
     *
     * @param aCause
     *         the cause of the exception, which may be retrieved later by the {@link #getCause()} method.
     *         A {@code null} value is permitted and indicates that the cause is nonexistent or unknown.
     * @param aMessageKey
     *         the translation key used to retrieve the localized message.
     * @param aParams
     *         optional parameters for constructing the localized message.
     */
    public BaseRuntimeException( Throwable aCause, TranslationKey aMessageKey, Serializable... aParams ) {
        super( aCause );
        this.messageKey = aMessageKey;
        this.params = aParams;
    }

    /**
     * Constructs a new runtime exception with the specified detail message.
     * The cause is not initialized, and may subsequently be initialized by a
     * call to {@link #initCause}.
     *
     * @param message
     *         the detail message. The detail message is saved for
     *         later retrieval by the {@link #getMessage()} method.
     */
    public BaseRuntimeException( String message ) {
        super( message );
    }

    /**
     * Constructs a new runtime exception with the specified detail message and
     * cause.  <p>Note that the detail message associated with
     * {@code cause} is <i>not</i> automatically incorporated in
     * this runtime exception's detail message.
     *
     * @param message
     *         the detail message (which is saved for later retrieval
     *         by the {@link #getMessage()} method).
     * @param cause
     *         the cause (which is saved for later retrieval by the
     *         {@link #getCause()} method).  (A {@code null} value is
     *         permitted, and indicates that the cause is nonexistent or
     *         unknown.)
     * @since 1.4
     */
    public BaseRuntimeException( String message, Throwable cause ) {
        super( message, cause );
    }

    /**
     * Creates a localized description of this throwable.
     * Subclasses may override this method in order to produce a
     * locale-specific message.  For subclasses that do not override this
     * method, the default implementation returns the same result as
     * {@code getMessage()}.
     *
     * @return The localized description of this throwable.
     *
     * @since 1.1
     */
    @Override
    public String getLocalizedMessage() {
        return LanguageUtil.getLocalizedMessage( messageKey, ( Object[] ) params );
    }

    /**
     * Getter method for the {@link #messageKey} property.
     *
     * @return a value of the property.
     */
    public TranslationKey getMessageKey() {
        return messageKey;
    }

    /**
     * Getter method for the {@link #params} property.
     *
     * @return a value of the property.
     */
    public Serializable[] getParams() {
        return params;
    }
}
