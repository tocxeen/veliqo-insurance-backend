package com.veliqo.codeChallenge.applicant;

import org.springframework.stereotype.Component;

/**
 * Author Richard K Chifamba on 9/26/2023
 **/

@Component
public class ApplicantDTOConverter {

    public Applicant toEntity(ApplicantDTO applicantDTO){
        Applicant applicant = new Applicant();

        applicant.setCity(applicantDTO.getCity());
        applicant.setCountry(applicantDTO.getCountry());
        applicant.setDob(applicantDTO.getDob());
        applicant.setEmail(applicantDTO.getEmail());
        applicant.setMarried(applicantDTO.getMarried());
        applicant.setSex(applicantDTO.getSex());
        applicant.setStreet(applicantDTO.getStreet());
        applicant.setZipCode(applicantDTO.getZipCode());
        return applicant;
    }

    public ApplicantDTO toDTO(Applicant applicant) {
        ApplicantDTO applicantDTO = new ApplicantDTO();

        applicantDTO.setCity(applicant.getCity());
        applicantDTO.setCountry(applicant.getCountry());
        applicantDTO.setDob(applicant.getDob());
        applicantDTO.setEmail(applicant.getEmail());
        applicantDTO.setMarried(applicant.getMarried());
        applicantDTO.setSex(applicant.getSex());
        applicantDTO.setStreet(applicant.getStreet());
        applicantDTO.setZipCode(applicant.getZipCode());
        return applicantDTO;
    }
}
