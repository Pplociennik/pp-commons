/*
 * <!--
 *   ~ MIT License
 *   ~
 *   ~ Copyright (c) 2024 Przemysław Płóciennik
 *   ~
 *   ~ Permission is hereby granted, free of charge, to any person obtaining a copy
 *   ~ of this software and associated documentation files (the "Software"), to deal
 *   ~ in the Software without restriction, including without limitation the rights
 *   ~ to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 *   ~ copies of the Software, and to permit persons to whom the Software is
 *   ~ furnished to do so, subject to the following conditions:
 *   ~
 *   ~ The above copyright notice and this permission notice shall be included in all
 *   ~ copies or substantial portions of the Software.
 *   ~
 *   ~ THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 *   ~ IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 *   ~ FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 *   ~ AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 *   ~ LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 *   ~ OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
 *   ~ SOFTWARE.
 *   -->
 */

package com.github.pplociennik.commons.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.springframework.lang.NonNull;

import static java.util.Objects.requireNonNull;

/**
 * A data transfer object holding the data describing the response of the request.
 *
 * @author Created by: Pplociennik at 14.03.2024 18:29
 */
@Schema(
        name = "Response to a request.",
        description = "Schema for holding the response information data."
)
@EqualsAndHashCode( callSuper = true )
@Data
public class ResponseDto extends BaseAbstractExtendableDto {

    @Schema(
            description = "A status code of the response.",
            example = "200" )
    private String statusCode;

    @Schema(
            description = "A status message of the response.",
            example = "Resource created successfully." )
    private String statusMsg;

    private AccessTokenInfoDto tokenInfo;

    /**
     * Private constructor for creating an instance of {@code ResponseDto}.
     *
     * @param statusCode
     *         the status code of the response
     * @param statusMsg
     *         the status message of the response
     * @param tokenInfo
     *         the access token information associated with the response
     */
    private ResponseDto( String statusCode, String statusMsg, AccessTokenInfoDto tokenInfo ) {
        this.statusCode = statusCode;
        this.statusMsg = statusMsg;
        this.tokenInfo = tokenInfo;
    }

    /**
     * Creates a {@link ResponseDto} instance containing the provided status code, status message,
     * and refreshed access token information.
     *
     * @param aStatusCode
     *         the status code representing the outcome of the response
     * @param aStatusMsg
     *         the status message providing details about the response
     * @param aNewUserAccessToken
     *         the new access token that has been refreshed
     * @return a {@link ResponseDto} containing the response data along with the refreshed token information
     */
    private static ResponseDto withTokenRefreshed( @NonNull String aStatusCode, @NonNull String aStatusMsg, @NonNull String aNewUserAccessToken ) {
        requireNonNull( aStatusCode );
        requireNonNull( aStatusMsg );
        requireNonNull( aNewUserAccessToken );

        AccessTokenInfoDto refreshedTokenInfo = AccessTokenInfoDto.refreshed( aNewUserAccessToken );
        return new ResponseDto( aStatusCode, aStatusMsg, refreshedTokenInfo );
    }

    /**
     * Creates a {@link ResponseDto} instance containing the provided status code, status message,
     * and information indicating that the current access token is valid and has not been refreshed.
     *
     * @param aStatusCode
     *         the status code representing the outcome of the response
     * @param aStatusMsg
     *         the status message providing details about the response
     * @return a {@link ResponseDto} containing the response data with the current (unrefreshed) access token information
     */
    public static ResponseDto withCurrentTokenValid( @NonNull String aStatusCode, @NonNull String aStatusMsg ) {
        requireNonNull( aStatusCode );
        requireNonNull( aStatusMsg );

        return new ResponseDto( aStatusCode, aStatusMsg, AccessTokenInfoDto.notRefreshed() );
    }
}


