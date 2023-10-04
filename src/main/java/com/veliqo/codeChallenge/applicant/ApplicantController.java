package com.veliqo.codeChallenge.applicant;

import com.veliqo.codeChallenge.exceptions.RecordExistException;
import com.veliqo.codeChallenge.exceptions.RecordNotFoundException;
import com.veliqo.codeChallenge.user.UserDTO;
import com.veliqo.codeChallenge.user.UserDTOConverter;
import com.veliqo.codeChallenge.user.UserServiceImp;
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
    private final UserServiceImp userService;

    @Autowired
    public ApplicantController(ApplicantService applicantService, UserServiceImp userService) {
        this.applicantService = applicantService;
        this.userService=userService;
    }

    @Autowired
    private ApplicantDTOConverter converter;

    @Autowired
    private UserDTOConverter userConverter;

    @PostMapping("/registerApplicantAccount")
    public ResponseEntity<UserDTO> registerApplicant(@RequestBody UserDTO user) {
        UserDTO savedUser = userService.saveUser(userConverter.toEntity(user)).orElseThrow(()->
                new RecordExistException("Failed to create customer"));
        return new ResponseEntity<>(savedUser, HttpStatus.CREATED);
    }

    @PostMapping("/register")
    public ResponseEntity<ApplicantDTO> registerApplicant(@RequestBody ApplicantDTO applicantDTO) {
        ApplicantDTO savedApplicant = applicantService.saveApplicant(converter.toEntity(applicantDTO)).orElseThrow(()->
                new RecordExistException("Failed to create applicant"));
        return new ResponseEntity<>(savedApplicant, HttpStatus.CREATED);
    }

    @PutMapping("/update")
    public ResponseEntity<ApplicantDTO> updateApplicant(@RequestBody ApplicantDTO applicantDTO) {
        ApplicantDTO savedApplicant = applicantService.updateApplicant(applicantDTO).orElseThrow(()->
                new RecordExistException("Failed to update applicant"));
        return new ResponseEntity<>(savedApplicant, HttpStatus.OK);
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
