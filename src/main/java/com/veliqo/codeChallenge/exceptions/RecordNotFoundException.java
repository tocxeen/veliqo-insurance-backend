package com.veliqo.codeChallenge.exceptions;

/**
 * Author Richard K Chifamba on 9/25/2023
 **/
public class RecordNotFoundException extends RuntimeException {

    public RecordNotFoundException(String message) {
        super(message);
    }

    public RecordNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }
}
