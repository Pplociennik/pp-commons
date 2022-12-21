package com.github.pplociennik.commons.persistence;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

import javax.persistence.*;

/**
 * A base entity for unique objects identification.
 *
 * @author Created by: Pplociennik at 21.12.2022 21:27
 */
@MappedSuperclass
@SuperBuilder
public abstract class BaseIdentifiableDataEntity {

    /**
     * Number object id.
     */
    @Id
    @GeneratedValue( strategy = GenerationType.AUTO )
    @Column( name = "ID", nullable = false )
    @Getter( AccessLevel.PROTECTED )
    @Setter( AccessLevel.PROTECTED )
    protected long id;

    /**
     * Unique String object identifier.
     */
    @Column( name = "UNIQUE_OBJECT_IDENTIFIER", nullable = false, unique = true, updatable = false )
    @Getter( AccessLevel.PROTECTED )
    @Setter( AccessLevel.PROTECTED )
    protected String uniqueObjectIdentifier;

}
