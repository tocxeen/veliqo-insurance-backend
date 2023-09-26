package com.veliqo.codeChallenge.beneficiary;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Getter;
import lombok.Setter;

/**
 * Author Richard K Chifamba on 9/26/2023
 **/
@Getter
@Setter
@JsonInclude(JsonInclude.Include.NON_NULL)
public class BeneficiaryDTO {

    private String email;

    private String name;

    private String phoneNumber;

    private String nationalID;

    private Long applicantID;

}
