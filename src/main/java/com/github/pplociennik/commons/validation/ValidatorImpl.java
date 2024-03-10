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

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Set;
import java.util.function.Function;
import java.util.function.Predicate;

import static java.util.stream.Collectors.joining;

/**
 * Implementation of {@link Validator} giving a possibility to enable validation chaining.
 *
 * @author Created by: Pplociennik at 22.12.2021 19:12
 */
public final class ValidatorImpl< T > implements Validator< T > {

    /**
     * A collection of objects being validated.
     */
    private final Collection< T > values;
    /**
     * A resulting list of invalidation reasons.
     */
    private final List< InvalidationReason > reasons = new ArrayList<>();

    ValidatorImpl( final Collection< T > aValues ) {
        values = aValues;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Validator< T > validate( Predicate< T > aPredicate, TranslationKey aKey ) {
        this.validate( aPredicate, aKey, Set.of() );
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Validator< T > validate(
            Predicate< T > aPredicate, TranslationKey aKey, Set< Function< T, Serializable > > aExcParams ) {
        values
                .stream()
                .filter( aPredicate.negate() )
                .map( value -> mapExcParams( aExcParams, value ) )
                .map( params -> new InvalidationReason( aKey, params ) )
                .forEach( reasons::add );
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public < S > Validator< T > validate(
            Function< T, S > aProjection, Predicate< S > aPredicate, TranslationKey aKey ) {
        return validate( aProjection.andThen( aPredicate::test )::apply, aKey, Set.of() );
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public < S > Validator< T > validate(
            Function< T, S > aProjection, Predicate< S > aPredicate, TranslationKey aKey,
            Set< Function< T, Serializable > > aExcParams ) {
        return validate( aProjection.andThen( aPredicate::test )::apply, aKey, aExcParams );
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void perform() {
        if ( ! reasons.isEmpty() ) {
            throw validationException();
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Validator< T > performAndThen() {
        if ( reasons.isEmpty() ) {
            return this;
        }
        throw validationException();
    }

    private Serializable[] mapExcParams( Set< Function< T, Serializable > > aExcParams, T aValue ) {
        return aExcParams
                .stream()
                .map( mapper -> mapper.apply( aValue ) )
                .toArray( Serializable[]::new );
    }

    private ValidationException validationException() {
        var exception = new ValidationException();
        var message = reasons
                .stream()
                .map( InvalidationReason::getReason )
                .collect( joining( ";", "[", "]" ) );
        exception.addSuppressed( new ValidationException( message ) );
        return exception;
    }
}
