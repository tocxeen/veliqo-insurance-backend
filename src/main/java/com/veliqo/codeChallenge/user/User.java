package com.veliqo.codeChallenge.user;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.veliqo.codeChallenge.user.models.Role;
import com.veliqo.codeChallenge.user.models.Status;
import jakarta.persistence.*;
import lombok.Data;

import java.io.Serializable;

/**
 * Author Richard K Chifamba on 9/24/2023
 **/


@Data
@Entity
@Table(	name = "users")
@JsonInclude(JsonInclude.Include.NON_NULL)
public class User implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(nullable = false,updatable = false)
    private Long id;

    @Column(nullable = false, unique = true)
    private String email;

    private String name;

    private String password;

    @Enumerated(EnumType.STRING)
    private Status status;

    @Enumerated(EnumType.STRING)
    private Role role;

}
