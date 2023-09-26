package com.veliqo.codeChallenge.user;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.veliqo.codeChallenge.user.models.Role;
import com.veliqo.codeChallenge.user.models.Status;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

/**
 * Author Richard K Chifamba on 9/26/2023
 **/
@Getter
@Setter
@JsonInclude(JsonInclude.Include.NON_NULL)
public class UserDTO {

    private String email;

    private String name;

    private Status status;

    private Role role;
}
