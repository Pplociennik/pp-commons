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
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.springframework.http.HttpStatus;

import java.time.ZonedDateTime;

/**
 * A data transfer object holding tha data about the response of the request when the error has occurred during the execution process.
 *
 * @author Created by: Pplociennik at 14.03.2024 18:40
 */
@Schema(
        name = "ErrorResponse",
        description = "Schema for holding the response information data when error has occurred during the execution."
)
@EqualsAndHashCode( callSuper = true )
@Data
public class ErrorResponseDto extends BaseAbstractExtendableDto {

    @Schema(
            description = "API path invoked by the client.",
            example = "/api/users/create"
    )
    private String apiPath;

    @Schema(
            description = "Error code representing the error which occurred.",
            example = "500"
    )
    private HttpStatus errorCode;

    @Schema(
            description = "Error message representing the error which occurred.",
            example = "Internal Server Error: Null Pointer Exception."
    )
    private String errorMessage;

    @Schema(
            description = "A timestamp of the error's occurrence.",
            example = "2024-03-23T18:00:00.000"
    )
    private ZonedDateTime errorTime;

    @Schema(
            description = "A client action flag determining an action to be executed on the client's side after receiving the response.",
            example = "VERIFY_USER_EMAIL"
    )
    private ClientActionFlag clientActionFlag;

    /**
     * Constructs a new instance of ErrorResponseDto with the specified parameters.
     *
     * @param apiPath
     *         the API path where the error occurred
     * @param errorCode
     *         the HTTP status code representing the error
     * @param errorMessage
     *         the detailed error message describing the issue
     * @param errorTime
     *         the timestamp of when the error occurred
     */
    public ErrorResponseDto( String apiPath, HttpStatus errorCode, String errorMessage, ZonedDateTime errorTime ) {
        this.apiPath = apiPath;
        this.errorCode = errorCode;
        this.errorMessage = errorMessage;
        this.errorTime = errorTime;
    }

    /**
     * Constructs a new instance of ErrorResponseDto with the specified parameters.
     *
     * @param apiPath
     *         the API path where the error occurred
     * @param errorCode
     *         the HTTP status code representing the error
     * @param errorMessage
     *         the detailed error message describing the issue
     * @param errorTime
     *         the timestamp of when the error occurred
     * @param clientActionFlag
     *         the client action flag associated with the error
     */
    public ErrorResponseDto( String apiPath, HttpStatus errorCode, String errorMessage, ZonedDateTime errorTime, ClientActionFlag clientActionFlag ) {
        this.apiPath = apiPath;
        this.errorCode = errorCode;
        this.errorMessage = errorMessage;
        this.errorTime = errorTime;
        this.clientActionFlag = clientActionFlag;
    }

    /**
     * Creates a new Builder instance for constructing an ErrorResponseDto object.
     *
     * @param aApiPath
     *         the API path that was invoked by the client
     * @param aErrorCode
     *         the error code representing the occurred error
     * @param aErrorMessage
     *         the error message describing the error
     * @param aErrorTime
     *         the timestamp when the error occurred
     * @return a new Builder instance initialized with the provided values
     */
    public static Builder builder( String aApiPath, HttpStatus aErrorCode, String aErrorMessage, ZonedDateTime aErrorTime ) {
        return new Builder( aApiPath, aErrorCode, aErrorMessage, aErrorTime );
    }

    /**
     * Builder class responsible for constructing instances of {@link ErrorResponseDto}.
     * This class provides a fluent API for setting specific parameters and creating
     * an instance of the ErrorResponseDto.
     */
    public static class Builder {
        private String apiPath;
        private HttpStatus errorCode;
        private String errorMessage;
        private ZonedDateTime errorTime;
        private ClientActionFlag clientActionFlag;

        /**
         * Constructs a new Builder instance with the specified parameters.
         *
         * @param apiPath
         *         the API path associated with the error
         * @param errorCode
         *         the HTTP status code representing the error
         * @param errorMessage
         *         the error message providing details about the issue
         * @param errorTime
         *         the timestamp when the error occurred
         */
        Builder( String apiPath, HttpStatus errorCode, String errorMessage, ZonedDateTime errorTime ) {
            this.apiPath = apiPath;
            this.errorCode = errorCode;
            this.errorMessage = errorMessage;
            this.errorTime = errorTime;
        }

        /**
         * Sets the client action flag for the builder.
         *
         * @param clientActionFlag
         *         the client action flag to associate with the error response
         * @return the builder instance for method chaining
         */
        public final Builder withClientActionFlag( ClientActionFlag clientActionFlag ) {
            this.clientActionFlag = clientActionFlag;
            return this;
        }

        /**
         * Constructs an instance of {@link ErrorResponseDto} using the parameters
         * that have been set in the {@code Builder}.
         *
         * @return a new instance of {@link ErrorResponseDto} containing the provided API path,
         * error code, error message, error timestamp, and client action flag.
         */
        public final ErrorResponseDto build() {
            return new ErrorResponseDto( this.apiPath, this.errorCode, this.errorMessage, this.errorTime, this.clientActionFlag );
        }
    }

}
