package com.veliqo.codeChallenge.beneficiary;

import jakarta.transaction.Transactional;

import java.util.List;
import java.util.Optional;

public interface BeneficiaryService {

    Optional<BeneficiaryDTO> saveBeneficiary(Beneficiary beneficiary);

    Optional<BeneficiaryDTO> findBeneficiaryByEmail(String email);

    Optional<BeneficiaryDTO> findBeneficiaryById(Long id);

    List<BeneficiaryDTO> findAllBeneficiaries();

    Optional<BeneficiaryDTO> updateBeneficiary(Beneficiary beneficiary);
}
