package com.veliqo.codeChallenge.plan;

import java.util.List;
import java.util.Optional;

public interface PlanService {

    Optional<PlanDTO> savePlan(PlanDTO plan);

//    Optional<PlanDTO> findPlanByBeneficiaryEmail(String email);

    Optional<PlanDTO> findPlanByApplicantEmail(String email);

    List<PlanDTO> findAllPlans();

    Optional<PlanDTO> updatePlan(PlanDTO planDTO);

    List<PlanDTO> findAllApplicantPlans(String email);
}
