package com.veliqo.codeChallenge.plan;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

/**
 * Author Richard K Chifamba on 10/3/2023
 **/

@Getter
@Setter
public class PlanDTO {

    private Long id;

    private String applicantEmail;

    private String policy;

    private List<String> beneficiaryEmail;

}
