package com.veliqo.codeChallenge.user;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.veliqo.codeChallenge.user.models.Role;
import com.veliqo.codeChallenge.user.models.Status;
import lombok.Getter;
import lombok.Setter;

/**
 * Author Richard K Chifamba on 9/26/2023
 **/
@Getter
@Setter
@JsonInclude(JsonInclude.Include.NON_NULL)
public class UserDTO {

    private String username;

    private String name;

    private String password;

    private Status status;

    private String roles;


}

@Getter
@Setter
class NumberOfUsers{

    private Long admin;

    private Long applicants;

    private Long total;
}
