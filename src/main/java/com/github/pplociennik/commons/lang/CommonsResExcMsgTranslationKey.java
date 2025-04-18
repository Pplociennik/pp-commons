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

package com.github.pplociennik.commons.lang;

/**
 * An enum holding keys for identifying exceptions' translation messages.
 *
 * @author Created by: Pplociennik at 22.12.2021 20:07
 */
public enum CommonsResExcMsgTranslationKey implements TranslationKey {

    /**
     * Properties cannot be read from file: {0}.
     */
    READING_PROPERTIES_FAILED,

    /**
     * Resource '{0}' not found with the given input data '{1}' : '{2}'.
     */
    RESOURCE_DOES_NOT_EXIST,

    /**
     * Unexpected exception! Reason: {0}
     */
    UNEXPECTED_EXCEPTION,

    /**
     * System property '{0}' not found!
     */
    NO_SUCH_SYSTEM_PROPERTY,

    /**
     * Property '{0}' has an invalid value: '{1}'! Correct values are: {2}.
     */
    INVALID_PROPERTY_VALUE;

    private static final String EXCEPTIONS_TRANSLATIONS_BASENAME_PROPERTY = "lang/CommonsResExcMsg";

    @Override
    public String toString() {
        return super.toString();
    }

    @Override
    public String getTranslationsSourcePropertyName() {
        return EXCEPTIONS_TRANSLATIONS_BASENAME_PROPERTY;
    }
}
