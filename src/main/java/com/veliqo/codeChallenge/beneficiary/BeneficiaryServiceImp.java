package com.veliqo.codeChallenge.beneficiary;


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
public class BeneficiaryServiceImp implements BeneficiaryService {
    private final BeneficiaryRepository beneficiaryRepository;

    @Autowired
    public BeneficiaryServiceImp(BeneficiaryRepository beneficiaryRepository) {
        this.beneficiaryRepository = beneficiaryRepository;
    }

    @Autowired
    private BeneficiaryDTOConverter converter;

    @Override
    public Optional<BeneficiaryDTO> saveBeneficiary(Beneficiary beneficiary) {
        beneficiaryRepository.findByEmail(beneficiary.getEmail()).ifPresent(data -> {
            throw new RecordExistException(String.format("Beneficiary with email %s already exists", data.getEmail()));
        });
        return Optional.of(converter.toDTO(beneficiaryRepository.save(beneficiary)));
    }

    @Override
    public Optional<BeneficiaryDTO> findBeneficiaryByEmail(String email) {
        Optional<Beneficiary> beneficiaryDTO = beneficiaryRepository.findByEmail(email);
        if(beneficiaryDTO.isPresent()){
            return Optional.of(converter.toDTO(beneficiaryDTO.get()));
        }
        throw new RecordNotFoundException(String.format("Beneficiary not %s found!",email));
    }
    @Override
    public Optional<BeneficiaryDTO> findBeneficiaryById(Long id) {
        Optional<Beneficiary> beneficiaryDTO = beneficiaryRepository.findById(id);
        if(beneficiaryDTO.isPresent()){
            return Optional.of(converter.toDTO(beneficiaryDTO.get()));
        }
        throw new RecordNotFoundException(String.format("Beneficiary whose applicant's ID is %s does not exist!",id));
    }

    @Override
    public List<BeneficiaryDTO> findAllBeneficiaries() {
        List<Beneficiary> beneficiaryDTO = beneficiaryRepository.findAll();
        List<BeneficiaryDTO> beneficiaries = new ArrayList<>();
        if(beneficiaryDTO.size()>0){
            beneficiaryDTO.forEach((data)->{
                beneficiaries.add(converter.toDTO(data));
            });
            return beneficiaries;
        }
        throw new RecordNotFoundException(String.format("No data found"));
    }

    @Override
    public Optional<BeneficiaryDTO> updateBeneficiary(Beneficiary beneficiary){
        findBeneficiaryByEmail(beneficiary.getEmail());
        return Optional.of(converter.toDTO(beneficiaryRepository.save(beneficiary)));
    }


}
