package com.veliqo.codeChallenge.applicant;

import com.veliqo.codeChallenge.exceptions.RecordExistException;
import com.veliqo.codeChallenge.exceptions.RecordNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
    public Optional<ApplicantDTO> updateApplicant(Applicant applicant){
        findApplicantByEmail(applicant.getEmail());
        return Optional.of(converter.toDTO(applicantRepository.save(applicant)));
    }


}
