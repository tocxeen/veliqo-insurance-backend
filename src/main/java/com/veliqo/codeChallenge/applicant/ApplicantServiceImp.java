package com.veliqo.codeChallenge.applicant;

import com.veliqo.codeChallenge.exceptions.RecordExistException;
import com.veliqo.codeChallenge.exceptions.RecordNotFoundException;
import com.veliqo.codeChallenge.transactions.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 * Author Richard K Chifamba on 9/26/2023
 **/
@Service
public class ApplicantServiceImp implements ApplicantService {

    private final ApplicantRepository applicantRepository;

    @Autowired
    public ApplicantServiceImp(ApplicantRepository applicantRepository) {
        this.applicantRepository = applicantRepository;
    }

    @Autowired
    private ApplicantDTOConverter converter;

    @Override
    public Optional<ApplicantDTO> saveApplicant(Applicant applicant) {
        applicantRepository.findByEmail(applicant.getEmail()).ifPresent(applicantData -> {
            throw new RecordExistException(String.format("Applicant with email %s already exists", applicantData.getEmail()));
        });
        return Optional.of(converter.toDTO(applicantRepository.save(applicant)));
    }


    @Override
    public List<ApplicantDTO> findAllApplicants() {
        List<Applicant> applicantDTO = applicantRepository.findAll();
        List<ApplicantDTO> res = new ArrayList<>();
        if(applicantDTO.size()>0){
            applicantDTO.forEach((applicant)->{
                res.add(converter.toDTO(applicant));
            });
            return res;
        }
        throw new RecordNotFoundException(String.format("No data found"));
    }

    @Override
    public Optional<ApplicantDTO> findApplicantByEmail(String email) {
        Optional<Applicant> applicantDTO = applicantRepository.findByEmail(email);
        if(applicantDTO.isPresent()){
            return Optional.of(converter.toDTO(applicantDTO.get()));
        }
        throw new RecordNotFoundException(String.format("Applicant not %s found!",email));
    }

    @Override
    public Optional<ApplicantDTO> updateApplicant(ApplicantDTO applicantDTO){
       Applicant applicant = applicantRepository.findByEmail(applicantDTO.getEmail()).orElseThrow(()-> new RecordNotFoundException("Applicant not found"));
       applicant.setCity(applicantDTO.getCity());
       applicant.setCountry(applicantDTO.getCountry());
       applicant.setStreet(applicantDTO.getStreet());
       applicant.setSex(applicantDTO.getSex());
       applicant.setDob(applicantDTO.getDob());
       applicant.setZipCode(applicantDTO.getZipCode());
       applicant.setMarriageStatus(applicantDTO.getMarriageStatus());
        return Optional.of(converter.toDTO(applicantRepository.save(applicant)));
    }

    @Override
    public BigDecimal updateApplicantBalance(Transaction transaction) {
        Optional<Applicant> applicant = applicantRepository.findByEmail(transaction.getApplicantEmail());
        if(applicant.isPresent()){
            BigDecimal newBal = applicant.get().getBalance().add(transaction.getAmount());
            applicantRepository.updateApplicantBalance(newBal,applicant.get().getEmail());
            Optional<Applicant> updateApplicant = applicantRepository.findByEmail(transaction.getApplicantEmail());
            return updateApplicant.get().getBalance();
        }
        throw new RecordNotFoundException(String.format("Applicant %s not found!",transaction.getApplicantEmail()));
    }
}
