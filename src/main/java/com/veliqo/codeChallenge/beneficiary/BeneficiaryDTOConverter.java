package com.veliqo.codeChallenge.beneficiary;

import org.springframework.stereotype.Component;

/**
 * Author Richard K Chifamba on 9/24/2023
 **/
@Component
public class BeneficiaryDTOConverter {

    public Beneficiary toEntity(BeneficiaryDTO beneficiaryDTO) {
        Beneficiary beneficiary = new Beneficiary();
        beneficiary.setEmail(beneficiaryDTO.getEmail());
        beneficiary.setName(beneficiaryDTO.getName());
        beneficiary.setApplicantID(beneficiaryDTO.getApplicantID());
        beneficiary.setNationalID(beneficiaryDTO.getNationalID());
        beneficiary.setPhoneNumber(beneficiaryDTO.getPhoneNumber());
        return beneficiary;
    }

    public BeneficiaryDTO toDTO(Beneficiary beneficiary){
        BeneficiaryDTO beneficiaryDTO = new BeneficiaryDTO();
        beneficiaryDTO.setEmail(beneficiary.getEmail());
        beneficiaryDTO.setName(beneficiary.getName());
        beneficiaryDTO.setApplicantID(beneficiary.getApplicantID());
        beneficiaryDTO.setNationalID(beneficiary.getNationalID());
        beneficiaryDTO.setPhoneNumber(beneficiary.getPhoneNumber());
        return beneficiaryDTO;
    }
}
