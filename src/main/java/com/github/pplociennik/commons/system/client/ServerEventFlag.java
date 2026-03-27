package com.github.pplociennik.commons.system.client;

import java.io.Serializable;

/**
 * An interface for client action flags. The flags indicate an event which occurred oon the server side so the client can react on it.
 *
 * @author Created by: Pplociennik at 26.01.2026 19:59
 */
public interface ServerEventFlag extends Serializable {

    /**
     * Retrieves the name associated with the client action flag.
     *
     * @return the name of the client action flag as a string.
     */
    String name();
}
