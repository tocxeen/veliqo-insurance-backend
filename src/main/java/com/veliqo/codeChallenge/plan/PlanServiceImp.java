package com.veliqo.codeChallenge.plan;

import com.veliqo.codeChallenge.beneficiary.Beneficiary;
import com.veliqo.codeChallenge.beneficiary.BeneficiaryDTO;
import com.veliqo.codeChallenge.beneficiary.BeneficiaryService;
import com.veliqo.codeChallenge.exceptions.RecordExistException;
import com.veliqo.codeChallenge.exceptions.RecordNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 * Author Richard K Chifamba on 10/3/2023
 **/
@Service
public class PlanServiceImp implements PlanService{

    private final PlanRepository planRepository;

    @Autowired
    private  BeneficiaryService beneficiaryService;

    @Autowired
    private PlanDTOConverter converter;

    public PlanServiceImp(PlanRepository planRepository) {
        this.planRepository = planRepository;
    }

    @Override
    public Optional<PlanDTO> savePlan(PlanDTO planDTO) {
        List<Plan> plan = planRepository.findAllByApplicantEmail(planDTO.getApplicantEmail());

        plan.forEach((data)->{
            if(data.getPolicy().equals(planDTO.getPolicy())){
                throw new RecordExistException(String.format("Applicant %s cannot have the same plan twice", planDTO.getApplicantEmail()));
            }
        });

        Plan savedPlan = planRepository.save(converter.toEntity(planDTO));
        System.out.println("++++++Plan id "+ savedPlan.getId());
        if(savedPlan !=null){
            planDTO.getBeneficiaryEmail().forEach((data)->{
                BeneficiaryDTO beneficiary = beneficiaryService.findBeneficiaryByEmail(data).orElseThrow(()-> new RecordNotFoundException("benefifiary not found"));
                beneficiary.setPlanID(savedPlan.getId());
                beneficiaryService.updateBeneficiary(beneficiary);
            });
        }
        return Optional.of(converter.toDTO(savedPlan));
    }

//    @Override
//    public Optional<PlanDTO> findPlanByBeneficiaryEmail(String email) {
//        Optional<Plan> plan = planRepository.findByBeneficiaryEmail(email);
//        if(plan.isPresent()){
//            return Optional.of(converter.toDTO(plan.get()));
//        }
//        throw new RecordNotFoundException(String.format("No plans set for beneficiary %s found!",email));
//    }

    @Override
    public Optional<PlanDTO> findPlanByApplicantEmail(String email) {
        Optional<Plan> plan = planRepository.findByApplicantEmail(email);
        if(plan.isPresent()){
            return Optional.of(converter.toDTO(plan.get()));
        }
        throw new RecordNotFoundException(String.format("applicant %s has no plans!",email));
    }

    @Override
    public List<PlanDTO> findAllPlans() {
        List<Plan> plan = planRepository.findAll();
        List<PlanDTO> plansList = new ArrayList<>();
        if(plan.size()>0){
            plan.forEach((data)->{
                plansList.add(converter.toDTO(data));
            });
            return plansList;
        }
        throw new RecordNotFoundException(String.format("No plans found"));
    }

    @Override
    public Optional<PlanDTO> updatePlan(PlanDTO planDTO) {
        return Optional.empty();
    }

    @Override
    public List<PlanDTO> findAllApplicantPlans(String email) {
        List<Plan> plan = planRepository.findAllByApplicantEmail(email);
        List<PlanDTO> plans = new ArrayList<>();
        if(plan.size()>0){
            plan.forEach((data)->{
                plans.add(converter.toDTO(data));
            });
            return plans;
        }
        throw new RecordNotFoundException(String.format("Plans for applicant %s found!",email));
    }
}
