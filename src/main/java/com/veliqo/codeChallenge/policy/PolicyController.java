package com.veliqo.codeChallenge.policy;

import com.veliqo.codeChallenge.exceptions.RecordExistException;
import com.veliqo.codeChallenge.exceptions.RecordNotFoundException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Author Richard K Chifamba on 9/24/2023
 **/
@Slf4j
@RestController
@RequestMapping("api/v1/veliqo/policy")
public class PolicyController {

    private final PolicyService policyService;

    @Autowired
    public PolicyController(PolicyService policyService) {
        this.policyService = policyService;
    }

    @Autowired
    private PolicyDTOConverter converter;

    @PostMapping("/register")
    public ResponseEntity<PolicyDTO> registerBeneficiary(@RequestBody PolicyDTO policyDTO) {
        PolicyDTO savedPolicy = policyService.savePolicy(converter.toEntity(policyDTO)).orElseThrow(()->
                new RecordExistException("Failed to create policy"));
        return new ResponseEntity<>(savedPolicy, HttpStatus.CREATED);
    }

    @GetMapping("/getPolicyByID/{id}")
    public ResponseEntity<PolicyDTO> getPolicyByID(@PathVariable Long id) {
        log.debug("finding policy with id {}", id);
        PolicyDTO policyDTO = policyService.findPolicyById(id).orElseThrow(()->
                new RecordNotFoundException(String.format("Policy not found")));
        return new ResponseEntity<>(policyDTO,HttpStatus.OK);
    }

    @GetMapping("/getPolicies")
    public ResponseEntity<List<PolicyDTO>> getPolicies() {
        List<PolicyDTO> beneficiaries = policyService.findAllPolicies();
        return new ResponseEntity<>(beneficiaries,HttpStatus.OK);
    }


}
