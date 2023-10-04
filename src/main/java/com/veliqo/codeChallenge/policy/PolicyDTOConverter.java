package com.veliqo.codeChallenge.policy;

import org.springframework.stereotype.Component;

/**
 * Author Richard K Chifamba on 9/24/2023
 **/

@Component
public class PolicyDTOConverter {

    public Policy toEntity(PolicyDTO policyDTO) {
        Policy policy = new Policy();
        policy.setCurrency(policyDTO.getCurrency());
        policy.setAmount(policyDTO.getAmount());
        policy.setName(policyDTO.getName());
        return policy;
    }

    public PolicyDTO toDTO(Policy policy){
        PolicyDTO policyDTO = new PolicyDTO();
        policyDTO.setCurrency(policy.getCurrency());
        policyDTO.setAmount(policy.getAmount());
        policyDTO.setName(policy.getName());
        return policyDTO;
    }
}
