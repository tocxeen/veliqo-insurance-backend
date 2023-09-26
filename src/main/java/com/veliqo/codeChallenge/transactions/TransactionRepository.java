package com.veliqo.codeChallenge.transactions;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

/**
 * Author Richard K Chifamba on 9/24/2023
 **/
@Repository
public interface TransactionRepository extends JpaRepository<Transaction,Long> {

    Optional<Transaction> findByApplicantEmail(String email);

}
