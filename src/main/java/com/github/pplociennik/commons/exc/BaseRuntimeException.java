package com.github.pplociennik.commons.exc;

import com.github.pplociennik.commons.lang.TranslationKey;
import com.github.pplociennik.commons.utility.LanguageUtil;

import java.io.Serializable;

/**
 * An exception class being a base for another exceptions. Holds the {@link TranslationKey} and message parameters.
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

    public BaseRuntimeException(
            String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace ) {
        super( message, cause, enableSuppression, writableStackTrace );
    }

    public BaseRuntimeException( TranslationKey aTranslationKey, Serializable... aParams ) {
        super( LanguageUtil.getLocalizedMessage( aTranslationKey, aParams ) );
        messageKey = aTranslationKey;
        params = aParams;
    }

    public BaseRuntimeException( TranslationKey aTranslationKey ) {
        super( LanguageUtil.getLocalizedMessage( aTranslationKey ) );
        messageKey = aTranslationKey;
    }

    protected TranslationKey getMessageKey() {
        return messageKey;
    }

    protected Serializable[] getParams() {
        return params;
    }
}
