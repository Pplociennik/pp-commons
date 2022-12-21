package com.github.pplociennik.commons.utility;

import org.springframework.lang.NonNull;

import java.util.Objects;

import static java.util.Objects.requireNonNull;

/**
 * An immutable generic object representing a pair of generic typed objects. Both objects are mandatory.
 *
 * @author Created by: Pplociennik at 08.05.2022 00:01
 */
public class GenericPair< E, T > {

    private final E former;

    private final T latter;

    public GenericPair( @NonNull E aFormer, @NonNull T aLatter ) {
        former = requireNonNull( aFormer );
        latter = requireNonNull( aLatter );
    }

    /**
     * Returns the former object from the pair.
     */
    public E getFormer() {
        return former;
    }

    /**
     * Returns the second object from the pair.
     */
    public T getLatter() {
        return latter;
    }

    @Override
    public boolean equals( Object aO ) {
        if ( this == aO ) {
            return true;
        }
        if ( aO == null || getClass() != aO.getClass() ) {
            return false;
        }
        GenericPair< ?, ? > that = ( GenericPair< ?, ? > ) aO;
        return Objects.equals( former, that.former ) && Objects.equals( latter, that.latter );
    }

    @Override
    public int hashCode() {
        return Objects.hash( former, latter );
    }

    @Override
    public String toString() {
        return "GenericPair{" + "former=" + former + ", latter=" + latter + '}';
    }
}
