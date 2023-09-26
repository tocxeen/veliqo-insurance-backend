package com.veliqo.codeChallenge.exceptions;

/**
 * Author Richard K Chifamba on 9/25/2023
 **/
public class RecordExistException extends RuntimeException {

    public RecordExistException(String message) {
        super(message);
    }

    public RecordExistException(String message, Throwable cause) {
        super(message, cause);
    }
}
