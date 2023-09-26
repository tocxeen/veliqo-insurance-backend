package com.veliqo.codeChallenge.exceptions;

/**
 * Author Richard K Chifamba on 9/25/2023
 **/

public class BusinessException extends RuntimeException {

    public BusinessException(String message) {
        super(message);
    }

    public BusinessException(String message, Throwable cause) {
        super(message, cause);
    }

    public BusinessException(final Throwable cause) {
        super(cause);
    }
}
