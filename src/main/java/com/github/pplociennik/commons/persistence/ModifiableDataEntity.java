package com.github.pplociennik.commons.persistence;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

import javax.persistence.Column;
import javax.persistence.MappedSuperclass;
import java.time.ZonedDateTime;

/**
 * A base data entity for identifiable object modification time storing.
 *
 * @author Created by: Pplociennik at 21.12.2022 21:39
 */
@MappedSuperclass
@SuperBuilder
public abstract class ModifiableDataEntity extends BaseIdentifiableDataEntity {

    /**
     * A date and time of the object's creation.
     */
    @Column( name = "CREATION_DATE", nullable = false, updatable = false )
    @Getter( AccessLevel.PROTECTED )
    @Setter( AccessLevel.PROTECTED )
    protected ZonedDateTime creationDate;

    /**
     * A date and time of the object's last modification.
     */
    @Column( name = "LAST_MODIFICATION" )
    @Getter( AccessLevel.PROTECTED )
    @Setter( AccessLevel.PROTECTED )
    protected ZonedDateTime lastModification;
}
