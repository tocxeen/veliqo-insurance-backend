package com.veliqo.codeChallenge.beneficiary;

import com.veliqo.codeChallenge.exceptions.RecordExistException;
import com.veliqo.codeChallenge.exceptions.RecordNotFoundException;
import com.veliqo.codeChallenge.policy.PolicyDTO;
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
@RequestMapping("api/v1/veliqo/beneficiary")
public class BeneficiaryController {

    private final BeneficiaryService beneficiaryService;

    @Autowired
    public BeneficiaryController(BeneficiaryService beneficiaryService) {
        this.beneficiaryService = beneficiaryService;
    }

    @Autowired
    private BeneficiaryDTOConverter converter;

    @PostMapping("/register")
    public ResponseEntity<BeneficiaryDTO> registerBeneficiary(@RequestBody BeneficiaryDTO beneficiaryDTO) {
        BeneficiaryDTO savedUser = beneficiaryService.saveBeneficiary(converter.toEntity(beneficiaryDTO)).orElseThrow(()->
                new RecordExistException("Failed to create beneficiary"));
        return new ResponseEntity<>(savedUser, HttpStatus.CREATED);
    }

    @GetMapping("/getBeneficiaryByEmail/{email}")
    public ResponseEntity<BeneficiaryDTO> getBeneficiaryByEmail(@PathVariable String email) {
        log.debug("finding beneficiary with email {}", email);
        BeneficiaryDTO beneficiary = beneficiaryService.findBeneficiaryByEmail(email).orElseThrow(()->
                new RecordNotFoundException(String.format("Beneficiary not found")));
        return new ResponseEntity<>(beneficiary,HttpStatus.OK);
    }

    @GetMapping("/getApplicantBeneficiaries/{email}")
    public ResponseEntity<List<BeneficiaryDTO>> getApplicantBeneficiaries(@PathVariable String email) {
        log.debug("finding applicant beneficiary using applicantID {}", email);
        List<BeneficiaryDTO> beneficiaries = beneficiaryService.findApplicantBeneficiaries(email);
        return new ResponseEntity<>(beneficiaries,HttpStatus.OK);
    }

//    getBeneficiaryByPlanId
    @GetMapping("/getBeneficiariesByPlanID/{id}")
    public ResponseEntity<List<BeneficiaryDTO>> getBeneficiariesByPlanID(@PathVariable Long id) {
        log.debug("finding applicant beneficiary using applicantID {}", id);
        List<BeneficiaryDTO> beneficiaries = beneficiaryService.getBeneficiariesByPlanId(id);
        return new ResponseEntity<>(beneficiaries,HttpStatus.OK);
    }

    @GetMapping("/getBeneficiaryByID/{id}")
    public ResponseEntity<BeneficiaryDTO> getBeneficiaryByID(@PathVariable Long id) {
        log.debug("finding beneficiary with id {}", id);
        BeneficiaryDTO user = beneficiaryService.findBeneficiaryById(id).orElseThrow(()->
                new RecordNotFoundException(String.format("Beneficiary not found")));
        return new ResponseEntity<>(user,HttpStatus.OK);
    }

    @GetMapping("/getBeneficiaries")
    public ResponseEntity<List<BeneficiaryDTO>> getBeneficiaries() {
        List<BeneficiaryDTO> beneficiaries = beneficiaryService.findAllBeneficiaries();
        return new ResponseEntity<>(beneficiaries,HttpStatus.OK);
    }

    @PutMapping("/update")
    public ResponseEntity<BeneficiaryDTO> updateBeneficiary(@RequestBody BeneficiaryDTO beneficiaryDTO) {
        BeneficiaryDTO savedBeneficiary = beneficiaryService.updateBeneficiary(beneficiaryDTO).orElseThrow(()->
                new RecordExistException("Failed to update beneficiary"));
        return new ResponseEntity<>(savedBeneficiary, HttpStatus.OK);
    }


}
