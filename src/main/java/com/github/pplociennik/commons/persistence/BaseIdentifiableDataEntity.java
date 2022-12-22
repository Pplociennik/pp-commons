package com.github.pplociennik.commons.persistence;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

/**
 * A base entity for unique objects identification.
 *
 * @author Created by: Pplociennik at 21.12.2022 21:27
 */
@MappedSuperclass
public abstract class BaseIdentifiableDataEntity {

    /**
     * Number object id.
     */
    @Id
    @GeneratedValue( strategy = GenerationType.SEQUENCE )
    @Column( name = "ID", nullable = false )
    @Getter( AccessLevel.PUBLIC )
    @Setter( AccessLevel.PUBLIC )
    protected long id;

    /**
     * Unique String object identifier.
     */
    @Column( name = "UNIQUE_OBJECT_IDENTIFIER", nullable = false, unique = true, updatable = false )
    @Getter( AccessLevel.PUBLIC )
    @Setter( AccessLevel.PUBLIC )
    protected String uniqueObjectIdentifier;

}
