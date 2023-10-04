package com.veliqo.codeChallenge.plan;

import com.veliqo.codeChallenge.beneficiary.Beneficiary;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

/**
 * Author Richard K Chifamba on 10/3/2023
 **/

public interface PlanRepository extends JpaRepository<Plan,Long> {

//    Optional<Plan> findByBeneficiaryEmail(String email);

    Optional<Plan> findByApplicantEmail(String email);

    List<Plan> findAllByApplicantEmail(String email);

}
