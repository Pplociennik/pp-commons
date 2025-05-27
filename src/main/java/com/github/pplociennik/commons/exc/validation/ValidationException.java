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

package com.github.pplociennik.commons.exc.validation;

import com.github.pplociennik.commons.exc.BaseRuntimeException;
import com.github.pplociennik.commons.lang.TranslationKey;

import java.io.Serializable;

/**
 * An exception being thrown when the validation process fails.
 *
 * @author Created by: Pplociennik at 22.12.2021 19:48
 */
public class ValidationException extends BaseRuntimeException {

    /**
     * Default constructor for the {@code ValidationException} class.
     * This constructor initializes a new {@code ValidationException} with
     * a predefined message indicating general validation failure.
     */
    public ValidationException() {
        super( "Validation failed!" );
    }

    /**
     * Constructs a new {@code BaseRuntimeException} with a specified translation key and parameters.
     * The localized message is generated using the provided translation key and parameters.
     *
     * @param aTranslationKey
     *         the translation key used to retrieve the localized message
     * @param aParams
     *         optional parameters for constructing the localized message
     */
    public ValidationException( TranslationKey aTranslationKey, Serializable... aParams ) {
        super( aTranslationKey, aParams );
    }

    /**
     * Constructs a new {@code BaseRuntimeException} with a specified translation key.
     * The localized message is generated using the provided translation key.
     *
     * @param aTranslationKey
     *         the translation key used to retrieve the localized message
     */
    public ValidationException( TranslationKey aTranslationKey ) {
        super( aTranslationKey );
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
    public ValidationException( Throwable aCause, TranslationKey aMessageKey, Serializable... aParams ) {
        super( aCause, aMessageKey, aParams );
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
    public ValidationException( String message ) {
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
    public ValidationException( String message, Throwable cause ) {
        super( message, cause );
    }
}
