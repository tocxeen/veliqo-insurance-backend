package com.veliqo.codeChallenge.exceptions;

/**
 * Author Richard K Chifamba on 10/2/2023
 **/

public class InvalidPasswordException extends RuntimeException {

    public InvalidPasswordException(String message) {
        super(message);
    }

    public InvalidPasswordException(String message, Throwable cause) {
        super(message, cause);
    }
}