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

import com.github.pplociennik.commons.system.client.ClientActionFlag;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import org.springframework.lang.NonNull;

import java.io.Serializable;
import java.util.Arrays;
import java.util.List;

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
@Getter
public class ResponseDto< T extends Serializable > extends BaseAbstractExtendableDto {

    /**
     * Contains the status information regarding the processing of a request.
     * It encapsulates details such as a status code and a corresponding status message,
     * which provide information about the outcome or state of the request processing.
     */
    private ResponseStatusInfoDto statusInfo;

    /**
     * Encapsulates information about the access token associated with the response.
     * This includes details on whether the token has been refreshed and the new token value, if applicable.
     * Useful for handling token management in API responses.
     */
    private ResponseAccessTokenInfoDto tokenInfo;

    /**
     * Represents a collection of response data items associated with the processed request.
     * This list contains the specific data objects of type {@code T} that are returned as part of the response.
     * It serves as the main content section of the response, holding potentially multiple elements.
     *
     * @param <T> the type of the response data items
     */
    private List< T > responseData;

    /**
     * Represents an action to be executed on the client side after receiving the response.
     */
    private ClientActionFlag clientActionFlag;

    /**
     * Constructs a new instance of {@code ResponseDto}.
     *
     * @param aStatusInfo
     *         the status information of the response
     * @param aTokenInfo
     *         the access token information of the response
     * @param aResponseData
     *         the response data
     */
    private ResponseDto( ResponseStatusInfoDto aStatusInfo, ResponseAccessTokenInfoDto aTokenInfo, List< T > aResponseData ) {
        this.statusInfo = aStatusInfo;
        this.tokenInfo = aTokenInfo;
        this.responseData = aResponseData;
    }

    /**
     * Creates a new instance of the {@code Builder}, allowing to configure and build a {@code ResponseDto} object.
     *
     * @param <T>
     *         the type of the response data element
     * @return a new {@code Builder} instance for constructing {@code ResponseDto} objects
     */
    public static < T extends Serializable > Builder< T > builder() {
        return new Builder<>();
    }

    /**
     * A builder class for constructing {@code ResponseDto} instances. It provides methods
     * for setting the status information, accessing token details, and response data.
     * This class simplifies the creation of {@code ResponseDto} objects
     * with clearly defined configurations and parameters.
     *
     * @param <T>
     *         the type of the response data element
     */
    public static class Builder< T extends Serializable > {
        private ResponseStatusInfoDto statusInfo;
        private ResponseAccessTokenInfoDto tokenInfo;
        private List< T > responseData;
        private ClientActionFlag clientActionFlag;

        /**
         * Constructs a new {@code Builder} instance with default initial values.
         * By default, the {@code tokenInfo} field is initialized using the
         * {@code ResponseAccessTokenInfoDto.notRefreshed()} method, indicating
         * that the access token has not been refreshed.
         */
        Builder() {
            this.tokenInfo = ResponseAccessTokenInfoDto.notRefreshed();
        }

        /**
         * Adds status information to the {@code Builder} instance by setting the status code
         * and status message. This information will be encapsulated as a {@code ResponseStatusInfoDto}.
         *
         * @param aStatusCode
         *         the status code representing the processing result must not be null
         * @param aStatusMsg
         *         the status message describing the processing result must not be null
         * @return the current {@code Builder} instance with updated status information
         */
        public Builder< T > withStatusInfo( @NonNull String aStatusCode, @NonNull String aStatusMsg ) {
            requireNonNull( aStatusCode );
            requireNonNull( aStatusMsg );
            this.statusInfo = ResponseStatusInfoDto.of( aStatusCode, aStatusMsg );
            return this;
        }

        /**
         * Configures the {@code Builder} instance with user access token details. When the access token
         * is refreshed, updates the token information with the new access token.
         *
         * @param aTokenRefreshed
         *         a boolean value indicating whether the access token has been refreshed
         * @param aNewUserAccessToken
         *         the new access token value must not be null
         * @return the current {@code Builder} instance with updated access token information
         */
        public Builder< T > withUserAccessToken( boolean aTokenRefreshed, @NonNull String aNewUserAccessToken, @NonNull Integer aExpiresIn ) {
            requireNonNull( aNewUserAccessToken );
            if ( aTokenRefreshed ) {
                this.tokenInfo = ResponseAccessTokenInfoDto.refreshed( aNewUserAccessToken, aExpiresIn );
            }
            return this;
        }

        /**
         * Configures the {@code Builder} instance with a list of response data objects.
         *
         * @param aResponseData
         *         objects containing response data objects, must not be null
         * @return the current {@code Builder} instance with the response data set
         */
        @SafeVarargs
        public final Builder< T > withResponseData( @NonNull T... aResponseData ) {
            requireNonNull( aResponseData );
            this.responseData = Arrays.asList( aResponseData );
            return this;
        }

        /**
         * Configures the {@code Builder} instance with a list of response data objects.
         *
         * @param aResponseData
         *         a {@code List} containing response data objects, must not be null
         * @return the current {@code Builder} instance with the response data set
         */
        public final Builder< T > withResponseData( @NonNull List< T > aResponseData ) {
            requireNonNull( aResponseData );
            this.responseData = aResponseData;
            return this;
        }

        /**
         * Configures the {@code Builder} instance with a client action flag.
         *
         * @param aClientActionFlag
         *         the client action flag to be set, must not be null
         * @return the current {@code Builder} instance with the specified client action flag
         */
        public final Builder< T > withClientActionFlag( @NonNull ClientActionFlag aClientActionFlag ) {
            requireNonNull( aClientActionFlag );
            this.clientActionFlag = aClientActionFlag;
            return this;
        }

        /**
         * Builds a new {@code ResponseDto} instance using the configured properties of the {@code Builder}.
         * The returned {@code ResponseDto} encapsulates the status information, access token details,
         * and response data that have been set in the {@code Builder}.
         *
         * @return a fully constructed {@code ResponseDto} object containing the status information,
         * access token information, and response data configured in the {@code Builder}
         */
        public ResponseDto< T > build() {
            return new ResponseDto<>( statusInfo, tokenInfo, responseData );
        }

    }
}


