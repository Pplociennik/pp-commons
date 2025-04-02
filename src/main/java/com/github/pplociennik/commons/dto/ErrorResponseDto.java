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
import lombok.AllArgsConstructor;
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
@AllArgsConstructor
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

}
