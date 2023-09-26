package com.veliqo.codeChallenge.policy;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

/**
 * Author Richard K Chifamba on 9/26/2023
 **/
@Getter
@Setter
@JsonInclude(JsonInclude.Include.NON_NULL)
public class PolicyDTO {

    private String name;

    private BigDecimal amount;

}
