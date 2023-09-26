package com.veliqo.codeChallenge.applicant;

import com.veliqo.codeChallenge.transactions.Transaction;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

public interface ApplicantService {

    Optional<ApplicantDTO> saveApplicant(Applicant applicant);

    List<ApplicantDTO> findAllApplicants();

    Optional<ApplicantDTO> findApplicantByEmail(String email);

    Optional<ApplicantDTO> updateApplicant(Applicant applicant);

    BigDecimal updateApplicantBalance(Transaction transaction);
}
