package com.veliqo.codeChallenge.policy;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

/**
 * Author Richard K Chifamba on 9/24/2023
 **/
@Repository
public interface PolicyRepository extends JpaRepository<Policy,Long> {

    Optional<Policy> findByName(String name);
}
