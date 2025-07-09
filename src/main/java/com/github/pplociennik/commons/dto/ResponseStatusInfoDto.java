package com.github.pplociennik.commons.dto;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NonNull;

import static java.util.Objects.requireNonNull;

/**
 * A Data Transfer Object (DTO) that represents status information for request processing.
 * This class encapsulates both a status code and a corresponding status message,
 * providing a standardized way to communicate processing results.
 *
 * <p>The class is immutable and provides a static factory method for object creation.</p>
 *
 * @author Created by: Pplociennik at 07.05.2025 23:21
 */
@Schema(
        name = "Status Information",
        description = "Request processing status information."
)
@EqualsAndHashCode( callSuper = true )
@Getter
public class ResponseStatusInfoDto extends BaseAbstractExtendableDto {

    @Schema(
            description = "A status code of the response.",
            example = "200" )
    private final String statusCode;

    @Schema(
            description = "A status message of the response.",
            example = "Resource created successfully." )
    private final String statusMsg;

    /**
     * Private constructor.
     *
     * @param aStatusCode
     *         the status code of the response
     * @param aStatusMsg
     *         the status message of the response
     */
    @JsonCreator
    private ResponseStatusInfoDto( @NonNull @JsonProperty( value = "statusCode" ) String aStatusCode, @NonNull @JsonProperty( value = "statusMsg" ) String aStatusMsg ) {
        requireNonNull( aStatusCode );
        requireNonNull( aStatusMsg );

        this.statusCode = aStatusCode;
        this.statusMsg = aStatusMsg;
    }

    /**
     * Creates a new instance of {@code StatusInfoDto} with the provided status code and status message.
     *
     * @param aStatusCode
     *         the status code of the response
     * @param aStatusMsg
     *         the status message of the response
     * @return a {@code StatusInfoDto} instance initialized with the given status code and status message
     */
    public static ResponseStatusInfoDto of( @NonNull String aStatusCode, @NonNull String aStatusMsg ) {
        return new ResponseStatusInfoDto( aStatusCode, aStatusMsg );
    }
}
