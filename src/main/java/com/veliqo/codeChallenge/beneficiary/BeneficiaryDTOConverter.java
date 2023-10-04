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
        beneficiary.setApplicantEmail(beneficiaryDTO.getApplicantEmail());
        beneficiary.setNationalID(beneficiaryDTO.getNationalID());
        beneficiary.setPhoneNumber(beneficiaryDTO.getPhoneNumber());
        beneficiary.setPlanID(beneficiaryDTO.getPlanID());
        return beneficiary;
    }

    public BeneficiaryDTO toDTO(Beneficiary beneficiary){
        BeneficiaryDTO beneficiaryDTO = new BeneficiaryDTO();
        beneficiaryDTO.setEmail(beneficiary.getEmail());
        beneficiaryDTO.setName(beneficiary.getName());
        beneficiaryDTO.setApplicantEmail(beneficiary.getApplicantEmail());
        beneficiaryDTO.setNationalID(beneficiary.getNationalID());
        beneficiaryDTO.setPhoneNumber(beneficiary.getPhoneNumber());
        beneficiaryDTO.setPlanID(beneficiary.getPlanID());
        return beneficiaryDTO;
    }
}
