package com.github.pplociennik.commons.persistence;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.FieldDefaults;

import javax.persistence.Column;
import javax.persistence.MappedSuperclass;
import java.time.ZonedDateTime;

/**
 * A base data entity for identifiable object modification time storing.
 *
 * @author Created by: Pplociennik at 21.12.2022 21:39
 */
@MappedSuperclass
public abstract class ModifiableDataEntity extends BaseIdentifiableDataEntity {

    /**
     * A date and time of the object's creation.
     */
    @Column( name = "CREATION_DATE", nullable = false, updatable = false )
    @Getter( AccessLevel.PUBLIC )
    @Setter( AccessLevel.PUBLIC )
    protected ZonedDateTime creationDate;

    /**
     * A date and time of the object's last modification.
     */
    @Column( name = "LAST_MODIFICATION" )
    @Getter( AccessLevel.PUBLIC )
    @Setter( AccessLevel.PUBLIC )
    protected ZonedDateTime lastModification;
}
