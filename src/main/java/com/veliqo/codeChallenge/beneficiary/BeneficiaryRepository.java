package com.veliqo.codeChallenge.beneficiary;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

/**
 * Author Richard K Chifamba on 9/24/2023
 **/
@Repository
public interface BeneficiaryRepository extends JpaRepository<Beneficiary,Long> {

    Optional<Beneficiary> findByEmail(String email);

    Optional<Beneficiary> findByApplicantEmail(String email);

    List<Beneficiary> findAllByApplicantEmail(String email);

    List<Beneficiary> findAllByPlanID(Long id);

}
