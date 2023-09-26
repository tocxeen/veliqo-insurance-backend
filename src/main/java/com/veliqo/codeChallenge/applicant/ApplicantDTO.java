package com.veliqo.codeChallenge.applicant;

import com.veliqo.codeChallenge.applicant.models.Married;
import com.veliqo.codeChallenge.applicant.models.Sex;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDate;

/**
 * Author Richard K Chifamba on 9/22/2023
 **/

@Getter
@Setter
public class ApplicantDTO {

    private String email;

    private String street;

    private String city;

    private String zipCode;

    private String country;

    private LocalDate dob;

    private Sex sex;

    private Married married;

    private BigDecimal balance;

}
