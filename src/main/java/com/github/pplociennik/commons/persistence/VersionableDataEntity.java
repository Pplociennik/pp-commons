package com.github.pplociennik.commons.persistence;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.MappedSuperclass;

/**
 * A base data entity for modifiable objects' versions storing.
 *
 * @author Created by: Pplociennik at 21.12.2022 21:48
 */
@MappedSuperclass
public abstract class VersionableDataEntity extends ModifiableDataEntity {

    /**
     * A version of the object.
     */
    @Column( name = "VERSION", nullable = false, updatable = false )
    @Getter( AccessLevel.PROTECTED )
    @Setter( AccessLevel.PROTECTED )
    protected Integer version;
}
