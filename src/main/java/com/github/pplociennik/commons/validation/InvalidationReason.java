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

package com.github.pplociennik.commons.validation;

import com.github.pplociennik.commons.exc.validation.ValidationException;
import com.github.pplociennik.commons.lang.TranslationKey;
import org.springframework.lang.NonNull;

import java.io.Serializable;

import static com.github.pplociennik.commons.utility.LanguageUtil.getLocalizedMessage;


/**
 * Presents the reason of the validation process failure. Holds an exception with parameterized localized message.
 *
 * @author Created by: Pplociennik at 22.12.2021 19:32
 */
public class InvalidationReason {

    /**
     * The exception causing the invalidation.
     */
    private final ValidationException exception;

    public InvalidationReason( @NonNull TranslationKey aKey, @NonNull Serializable[] aParams ) {
        this.exception = new ValidationException( getLocalizedMessage( aKey, aParams ) );
    }

    /**
     * Returns a localized message being the reason of the invalidation.
     *
     * @return a localized exception message.
     */
    public String getReason() {
        return exception.getMessage();
    }

    /**
     * Returns a stack trace of the exception.
     *
     * @return a string being the stack trace of the exception.
     */
    public StackTraceElement[] getStackTrace() {
        return exception.getStackTrace();
    }

    /**
     * Returns a table of the suppressed exceptions.
     *
     * @return a table of the exceptions being suppressed.
     */
    public Throwable[] getSuppressed() {
        return exception.getSuppressed();
    }

    @Override
    public String toString() {
        return "InvalidationReason{" + "exception=" + exception + '}';
    }
}
