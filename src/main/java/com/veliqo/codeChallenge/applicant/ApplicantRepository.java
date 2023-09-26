package com.veliqo.codeChallenge.applicant;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

/**
 * Author Richard K Chifamba on 9/26/2023
 **/

@Repository
public interface ApplicantRepository extends JpaRepository<Applicant,Long> {

    Optional<Applicant> findByEmail(String email);

}
