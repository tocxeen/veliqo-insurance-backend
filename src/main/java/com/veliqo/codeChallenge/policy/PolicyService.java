package com.veliqo.codeChallenge.policy;

import java.util.List;
import java.util.Optional;

public interface PolicyService {

    Optional<PolicyDTO> savePolicy(Policy policy);

    Optional<PolicyDTO> findPolicyById(Long id);

    Optional<PolicyDTO> findPolicyByName(String name);

    List<PolicyDTO> findAllPolicies();

    Optional<PolicyDTO> updatePolicy(PolicyDTO policy);
}
