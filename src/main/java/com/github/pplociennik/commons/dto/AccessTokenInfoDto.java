package com.github.pplociennik.commons.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import org.springframework.lang.NonNull;

import static com.github.pplociennik.commons.utility.CustomObjects.requireNonEmpty;

/**
 * A Data Transfer Object (DTO) that represents access token information.
 * This class provides details about whether the access token has
 * been refreshed and the new access token value if applicable.
 *
 * <p>It includes methods for creating instances of the class for both
 * the refreshed and non-refreshed states.</p>
 *
 * @author Created by: Pplociennik at 16.04.2025 19:00
 */
@Getter
public class AccessTokenInfoDto extends BaseAbstractExtendableDto {

    @Schema(
            description = "Indicates whether the access token was refreshed or not.",
            example = "false"
    )
    private final boolean refreshed;

    @Schema(
            description = "Contains a new token if it was refreshed. Null otherwise."
    )
    private final String accessToken;

    /**
     * Private constructor.
     *
     * @param refreshed
     *         a flag determining whether the access token has been refreshed or not
     * @param accessToken
     *         a new access token (if it was refreshed)
     */
    private AccessTokenInfoDto( boolean refreshed, String accessToken ) {
        this.refreshed = refreshed;
        this.accessToken = accessToken;
    }

    /**
     * Creates and returns new instance marking the token refreshment and containing a new access token.
     *
     * @param aNewAccessToken
     *         a new access token
     */
    public static AccessTokenInfoDto refreshed( @NonNull String aNewAccessToken ) {
        requireNonEmpty( aNewAccessToken );
        return new AccessTokenInfoDto( true, aNewAccessToken );
    }

    /**
     * Creates and returns new instance marking the token was not refreshed.
     */
    public static AccessTokenInfoDto notRefreshed() {
        return new AccessTokenInfoDto( false, null );
    }
}
