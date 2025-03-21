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

package com.github.pplociennik.commons.exc;

import com.github.pplociennik.commons.dto.ErrorResponseDto;
import com.github.pplociennik.commons.exc.resources.ResourceNotFoundException;
import com.github.pplociennik.commons.utility.LanguageUtil;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.time.ZonedDateTime;

import static com.github.pplociennik.commons.lang.CommonsResExcMsgTranslationKey.UNEXPECTED_EXCEPTION;

/**
 * A global exception handler providing handling of the main general and unexpected exceptions. Should be overridden for any specific exceptions' cases.
 *
 * @author Created by: Pplociennik at 20.03.2024 17:50
 */
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler {

    /**
     * Handles the {@link ResourceNotFoundException}.
     *
     * @param aException
     *         the exception being thrown during the system work.
     * @param aWebRequest
     *         the web request which execution was interrupted by the exception.
     * @return {@link ErrorResponseDto}.
     */
    @ExceptionHandler( ResourceNotFoundException.class )
    public ResponseEntity< ErrorResponseDto > handleResourceNotFoundException( ResourceNotFoundException aException,
                                                                               WebRequest aWebRequest ) {
        ErrorResponseDto errorResponseDTO = new ErrorResponseDto(
                aWebRequest.getDescription( false ),
                HttpStatus.NOT_FOUND,
                aException.getMessage(),
                ZonedDateTime.now()
        );

        aException.printStackTrace();

        return new ResponseEntity<>( errorResponseDTO, HttpStatus.NOT_FOUND );
    }

    /**
     * Handles the unexpected exception.
     *
     * @param aException
     *         a caught exception
     * @return a response with INTERNAL_SERVER_ERROR status
     */
    @ExceptionHandler( Exception.class )
    ResponseEntity< ErrorResponseDto > handleUnexpectedException( Exception aException, WebRequest aWebRequest ) {
        var message = LanguageUtil.getLocalizedMessage( UNEXPECTED_EXCEPTION, aException.getMessage() );
        ErrorResponseDto errorResponseDto = new ErrorResponseDto(
                aWebRequest.getDescription( false ),
                HttpStatus.INTERNAL_SERVER_ERROR,
                message,
                ZonedDateTime.now()
        );

        aException.printStackTrace();

        return new ResponseEntity<>( errorResponseDto, HttpStatus.INTERNAL_SERVER_ERROR );
    }
}
