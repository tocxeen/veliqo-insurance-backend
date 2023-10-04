package com.veliqo.codeChallenge.beneficiary;

import java.util.List;
import java.util.Optional;

public interface BeneficiaryService {

    Optional<BeneficiaryDTO> saveBeneficiary(Beneficiary beneficiary);

    Optional<BeneficiaryDTO> findBeneficiaryByEmail(String email);

    List<BeneficiaryDTO> findApplicantBeneficiaries(String email);

    Optional<BeneficiaryDTO> findBeneficiaryById(Long id);

    List<BeneficiaryDTO> findAllBeneficiaries();

    List<BeneficiaryDTO> getBeneficiariesByPlanId(Long id);

    Optional<BeneficiaryDTO> updateBeneficiary(BeneficiaryDTO beneficiary);
}
