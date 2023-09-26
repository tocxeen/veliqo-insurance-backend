package com.veliqo.codeChallenge.applicant;

import java.util.List;
import java.util.Optional;

public interface ApplicantService {

    Optional<ApplicantDTO> saveApplicant(Applicant applicant);

    List<ApplicantDTO> findAllApplicants();

    Optional<ApplicantDTO> findApplicantByEmail(String email);

    Optional<ApplicantDTO> updateApplicant(Applicant applicant);
}
