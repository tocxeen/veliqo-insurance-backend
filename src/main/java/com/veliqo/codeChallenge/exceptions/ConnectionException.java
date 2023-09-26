package com.veliqo.codeChallenge.exceptions;

/**
 * Author Richard K Chifamba on 9/25/2023
 **/

public class ConnectionException extends RuntimeException {

    public ConnectionException(String message) {
        super(message);
    }

    public ConnectionException(Throwable cause) {
        super(cause);
    }

    public ConnectionException(String message, Throwable cause) {
        super(message, cause);
    }
}
