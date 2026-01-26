package com.github.pplociennik.commons.system.client;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;

import java.io.Serializable;

/**
 * An interface for client action flags.
 *
 * @author Created by: Pplociennik at 26.01.2026 19:59
 */
public interface ClientActionFlag extends Serializable {

    /**
     * Retrieves the name associated with the client action flag.
     *
     * @return the name of the client action flag as a string.
     */
    String name();
}
