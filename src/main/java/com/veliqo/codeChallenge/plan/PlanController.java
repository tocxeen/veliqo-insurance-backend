package com.veliqo.codeChallenge.plan;

import com.veliqo.codeChallenge.beneficiary.BeneficiaryDTO;
import com.veliqo.codeChallenge.exceptions.RecordExistException;
import com.veliqo.codeChallenge.policy.PolicyDTO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Author Richard K Chifamba on 10/3/2023
 **/
@Slf4j
@RestController
@RequestMapping("api/v1/veliqo/plan")
public class PlanController {

    private final PlanService planService;

    @Autowired
    private PlanDTOConverter converter;

    public PlanController(PlanService planService) {
        this.planService = planService;
    }


    @PostMapping("/register")
    public ResponseEntity<PlanDTO> registerPlan(@RequestBody PlanDTO planDTO) {
        PlanDTO savedPlan = planService.savePlan(planDTO).orElseThrow(()->
                new RecordExistException("Failed to create plan"));
        return new ResponseEntity<>(savedPlan, HttpStatus.CREATED);
    }

    @GetMapping("/getApplicantPlans/{email}")
    public ResponseEntity<List<PlanDTO>> getApplicantPlans(@PathVariable String email) {
        List<PlanDTO> plans = planService.findAllApplicantPlans(email);
        return new ResponseEntity<>(plans,HttpStatus.OK);
    }

}
