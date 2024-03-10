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

    public BaseRuntimeException() {
    }

    public BaseRuntimeException( String aMessage ) {
        super( aMessage );
    }

    public BaseRuntimeException( String aMessage, Throwable aCause ) {
        super( aMessage, aCause );
    }

    public BaseRuntimeException( Throwable aCause ) {
        super( aCause );
    }

    public BaseRuntimeException(
            String aMessage, Throwable aCause, boolean aEnableSuppression, boolean aWritableStackTrace ) {
        super( aMessage, aCause, aEnableSuppression, aWritableStackTrace );
    }

    public BaseRuntimeException( TranslationKey aTranslationKey, Serializable... aParams ) {
        super( LanguageUtil.getLocalizedMessage( aTranslationKey, aParams ) );
        messageKey = aTranslationKey;
        params = aParams;
    }

    public BaseRuntimeException( TranslationKey aTranslationKey ) {
        super( LanguageUtil.getLocalizedMessage( aTranslationKey ) );
        messageKey = aTranslationKey;
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
