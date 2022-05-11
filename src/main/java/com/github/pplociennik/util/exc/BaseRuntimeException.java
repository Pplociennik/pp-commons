package com.github.pplociennik.util.exc;

import com.github.pplociennik.util.lang.TranslationKey;

import java.io.Serializable;

/**
 * TODO: Describe this class.
 *
 * @author Created by: Pplociennik at 10.05.2022 19:24
 */
public class BaseRuntimeException extends RuntimeException {

    private TranslationKey messageKey;

    private Serializable[] params;

    public BaseRuntimeException() {
    }

    public BaseRuntimeException( String message ) {
        super( message );
    }

    public BaseRuntimeException( String message, Throwable cause ) {
        super( message, cause );
    }

    public BaseRuntimeException( Throwable cause ) {
        super( cause );
    }

    public BaseRuntimeException( String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace ) {
        super( message, cause, enableSuppression, writableStackTrace );
    }

    public BaseRuntimeException( TranslationKey aTranslationKey, Serializable... aParams ) {
        messageKey = aTranslationKey;
        params = aParams;
    }

}
