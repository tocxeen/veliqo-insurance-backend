package com.veliqo.codeChallenge.policy;


import com.veliqo.codeChallenge.exceptions.RecordExistException;
import com.veliqo.codeChallenge.exceptions.RecordNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 * Author Richard K Chifamba on 9/24/2023
 **/

@Service
public class PolicyServiceImp implements PolicyService {
    private final PolicyRepository policyRepository;

    @Autowired
    public PolicyServiceImp(PolicyRepository policyRepository) {
        this.policyRepository = policyRepository;
    }

    @Autowired
    private PolicyDTOConverter converter;

    @Override
    public Optional<PolicyDTO> savePolicy(Policy policy) {
        policyRepository.findByName(policy.getName()).ifPresent(data -> {
            throw new RecordExistException(String.format("Policy with name %s already exists", data.getName()));
        });
        return Optional.of(converter.toDTO(policyRepository.save(policy)));
    }


    @Override
    public Optional<PolicyDTO> findPolicyById(Long id) {
        Optional<Policy> beneficiaryDTO = policyRepository.findById(id);
        if(beneficiaryDTO.isPresent()){
            return Optional.of(converter.toDTO(beneficiaryDTO.get()));
        }
        throw new RecordNotFoundException(String.format("Beneficiary whose applicant's ID is %s does not exist!",id));
    }

    @Override
    public Optional<PolicyDTO> findPolicyByName(String name) {
        Optional<Policy> beneficiaryDTO = policyRepository.findByName(name);
        if(beneficiaryDTO.isPresent()){
            return Optional.of(converter.toDTO(beneficiaryDTO.get()));
        }
        throw new RecordNotFoundException(String.format("Policy  %s does not exist!",name));
    }

    @Override
    public List<PolicyDTO> findAllPolicies() {
        List<Policy> policyDTO = policyRepository.findAll();
        List<PolicyDTO> policies = new ArrayList<>();
        if(policyDTO.size()>0){
            policyDTO.forEach((data)->{
                policies.add(converter.toDTO(data));
            });
            return policies;
        }
        throw new RecordNotFoundException(String.format("No data found"));
    }

    @Override
    public Optional<PolicyDTO> updatePolicy(PolicyDTO policyDTO){
        Policy policy = policyRepository.findByName(policyDTO.getName()).orElseThrow(()-> new RecordNotFoundException("Policy not found"));
        policy.setCurrency(policyDTO.getCurrency());
        policy.setAmount(policyDTO.getAmount());
        return Optional.of(converter.toDTO(policyRepository.save(policy)));
    }


}
