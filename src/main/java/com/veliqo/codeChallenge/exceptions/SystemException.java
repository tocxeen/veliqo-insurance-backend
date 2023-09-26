package com.veliqo.codeChallenge.exceptions;

/**
 * Author Richard K Chifamba on 9/25/2023
 **/
public class SystemException extends RuntimeException {

    public SystemException(String message) {
        super(message);
    }

    public SystemException(String message, Throwable cause) {
        super(message, cause);
    }

    public SystemException(final Throwable cause) {
        super(cause);
    }

}
