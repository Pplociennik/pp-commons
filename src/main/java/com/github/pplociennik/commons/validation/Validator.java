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

import com.github.pplociennik.commons.exc.ValidationException;
import com.github.pplociennik.commons.lang.TranslationKey;
import org.springframework.lang.NonNull;

import java.io.Serializable;
import java.util.Collection;
import java.util.Set;
import java.util.function.Function;
import java.util.function.Predicate;

import static java.util.Collections.singleton;
import static java.util.Objects.requireNonNull;

/**
 * Base Validator interface for validation chaining. It lets preparing a chain which performs all the specified
 * validations and wraps possible on-way exceptions being thrown during the process as they are being presented then as
 * suppressed in a one clean detailed exception message.
 *
 * @author Created by: Pplociennik at 22.12.2021 19:01
 */
public interface Validator< T > {

    /**
     * A fabric method producing a validator for the singleton collection of the objects of the specified type.
     *
     * @param aSingleValue
     *         A single generic object to be validated.
     * @return A {@link Validator} object for the specified object's type.
     */
    static < T > Validator< T > of( @NonNull T aSingleValue ) {
        return new ValidatorImpl<>( singleton( requireNonNull( aSingleValue ) ) );
    }

    /**
     * A fabric method producing a validator for the specified values of the type.
     *
     * @param aValues
     *         A collection of objects to be validated.
     * @return A {@link Validator} object for the specified objects' type.
     */
    static < T > Validator< T > of( @NonNull Collection< T > aValues ) {
        return new ValidatorImpl<>( aValues );
    }

    /**
     * Validates the objects with given predicate.
     *
     * @param aPredicate
     *         A condition.
     * @param aKey
     *         A key for exception's message.
     * @return {@link Validator}.
     */
    Validator< T > validate( @NonNull Predicate< T > aPredicate, @NonNull TranslationKey aKey );

    /**
     * Validates the objects with given predicate. Lets to parameterize the exception being thrown.
     *
     * @param aPredicate
     *         A condition.
     * @param aKey
     *         A key for exception's message.
     * @param aExcParams
     *         Parameters for exception's message.
     * @return {@link Validator}.
     */
    Validator< T > validate(
            @NonNull Predicate< T > aPredicate, @NonNull TranslationKey aKey,
            @NonNull Set< Function< T, Serializable > > aExcParams );

    /**
     * Validates the object after initial object's projection to another type.
     *
     * @param aProjection
     *         A function defining the projection process.
     * @param aPredicate
     *         A condition.
     * @param aKey
     *         A key for exception's message.
     * @return {@link Validator}.
     */
    < S > Validator< T > validate(
            @NonNull Function< T, S > aProjection, @NonNull Predicate< S > aPredicate, @NonNull TranslationKey aKey );

    /**
     * Validates the object after initial object's projection to another type. Lets to parameterize the exception being
     * thrown.
     *
     * @param aProjection
     *         A function defining the projection process.
     * @param aPredicate
     *         A condition.
     * @param aKey
     *         A key for exception's message.
     * @param aExcParams
     *         Parameters for exception's message.
     * @return {@link Validator}.
     */
    < S > Validator< T > validate(
            @NonNull Function< T, S > aProjection, @NonNull Predicate< S > aPredicate, @NonNull TranslationKey aKey,
            @NonNull Set< Function< T, Serializable > > aExcParams );

    /**
     * Runs the specified validation chain.
     *
     * @throws ValidationException
     *         When the validation process fails.
     */
    void perform();

    /**
     * Runs the specified validation chain and the next one specified.
     *
     * @return {@link Validator}.
     * @throws ValidationException
     *         When the validation process fails.
     */
    Validator< T > performAndThen();
}
