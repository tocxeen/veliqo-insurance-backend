package com.veliqo.codeChallenge.applicant;

import com.veliqo.codeChallenge.exceptions.RecordExistException;
import com.veliqo.codeChallenge.exceptions.RecordNotFoundException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Author Richard K Chifamba on 9/26/2023
 **/

@Slf4j
@RestController
@RequestMapping("api/v1/veliqo/applicant")
public class ApplicantController {

    private final ApplicantService applicantService;

    @Autowired
    public ApplicantController(ApplicantService applicantService) {
        this.applicantService = applicantService;
    }

    @Autowired
    private ApplicantDTOConverter converter;

    @PostMapping("/register")
    public ResponseEntity<ApplicantDTO> registerApplicant(@RequestBody ApplicantDTO applicantDTO) {
        ApplicantDTO savedApplicant = applicantService.saveApplicant(converter.toEntity(applicantDTO)).orElseThrow(()->
                new RecordExistException("Failed to create applicant"));
        return new ResponseEntity<>(savedApplicant, HttpStatus.CREATED);
    }

    @GetMapping("/getApplicantByEmail/{email}")
    public ResponseEntity<ApplicantDTO> getApplicantByEmail(@PathVariable String email) {
        log.debug("finding user with email {}", email);
        ApplicantDTO applicant = applicantService.findApplicantByEmail(email).orElseThrow(()->
                new RecordNotFoundException(String.format("Applicant not found")));
        return new ResponseEntity<>(applicant,HttpStatus.OK);
    }

    @GetMapping("/getApplicants")
    public ResponseEntity<List<ApplicantDTO>> getApplicants() {
        List<ApplicantDTO> applicant = applicantService.findAllApplicants();
        return new ResponseEntity<>(applicant,HttpStatus.OK);
    }
}
