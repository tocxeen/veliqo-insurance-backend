package com.veliqo.codeChallenge.plan;

import org.springframework.stereotype.Component;

/**
 * Author Richard K Chifamba on 10/3/2023
 **/


@Component
public class PlanDTOConverter {

    public PlanDTO toDTO(Plan plan){
        PlanDTO planDTO = new PlanDTO();

        planDTO.setId(plan.getId());
        planDTO.setApplicantEmail(plan.getApplicantEmail());
        planDTO.setPolicy(plan.getPolicy());
        return  planDTO;
    }

    public Plan toEntity(PlanDTO planDTO){
        Plan plan = new Plan();

        plan.setId(planDTO.getId());
        plan.setApplicantEmail(planDTO.getApplicantEmail());
        plan.setPolicy(planDTO.getPolicy());
        return  plan;
    }
}
