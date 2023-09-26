package com.veliqo.codeChallenge.exceptions;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

/**
 * Author Richard K Chifamba on 9/25/2023
 **/

@Getter
@Setter
@RequiredArgsConstructor
public class UpdateMessage {
    private String message;
    private int statusCode;
}
