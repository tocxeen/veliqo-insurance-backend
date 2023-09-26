package com.veliqo.codeChallenge.applicant;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.util.Optional;

/**
 * Author Richard K Chifamba on 9/26/2023
 **/

@Repository
public interface ApplicantRepository extends JpaRepository<Applicant,Long> {

    Optional<Applicant> findByEmail(String email);

    @Modifying(clearAutomatically = true)
    @Query(value = "UPDATE Applicant a SET a.balance = :balance WHERE a.email = :email")
    int updateApplicantBalance(@Param("balance") BigDecimal balance, @Param("email") String email);

}
