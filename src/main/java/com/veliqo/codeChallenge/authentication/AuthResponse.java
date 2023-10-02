package com.veliqo.codeChallenge.authentication;

import com.veliqo.codeChallenge.user.models.Status;
import lombok.Getter;
import lombok.Setter;

/**
 * Author Richard K Chifamba on 10/2/2023
 **/

@Getter
@Setter
public class AuthResponse {

    private String token;

    private String name;

    private String roles;

    private Status status;

    private String username;

}
