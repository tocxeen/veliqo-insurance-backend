package com.veliqo.codeChallenge.beneficiary;

import com.fasterxml.jackson.annotation.JsonInclude;
import jakarta.persistence.*;
import lombok.Data;

import java.io.Serializable;

/**
 * Author Richard K Chifamba on 9/24/2023
 **/


@Data
@Entity
@Table(	name = "beneficiaries")
@JsonInclude(JsonInclude.Include.NON_NULL)
public class Beneficiary implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(nullable = false,updatable = false)
    private Long id;

    @Column(nullable = false, unique = true)
    private String email;

    private String name;

    private String phoneNumber;

    @Column(nullable = false, unique = true)
    private String nationalID;

    @Column(nullable = false,updatable = false)
    private String applicantEmail;

    private Long planID;

}
