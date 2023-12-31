package com.veliqo.codeChallenge.transactions;


import com.veliqo.codeChallenge.applicant.ApplicantDTO;
import com.veliqo.codeChallenge.applicant.ApplicantService;
import com.veliqo.codeChallenge.exceptions.RecordNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 * Author Richard K Chifamba on 9/24/2023
 **/

@Service
public class TransactionServiceImp implements TransactionService {
    private final TransactionRepository transactionRepository;
    private final ApplicantService applicantService;

    @Autowired
    public TransactionServiceImp(TransactionRepository transactionRepository,ApplicantService applicantService) {
        this.transactionRepository = transactionRepository;
        this.applicantService = applicantService;
    }

    @Autowired
    private TransactionDTOConverter converter;

    @Override
    public Optional<TransactionDTO> saveTransaction(Transaction transaction) {

        Transaction trans = transactionRepository.save(transaction);
        if(trans!=null){
           BigDecimal balance = applicantService.updateApplicantBalance(transaction);
            return Optional.of(converter.toDTO(trans));
        }
        throw new RuntimeException("Failed to save transaction");
    }

    @Override
    public Optional<TransactionDTO> findTransactionByApplicant(String email) {
        Optional<Transaction> beneficiaryDTO = transactionRepository.findByApplicantEmail(email);
        if(beneficiaryDTO.isPresent()){
            return Optional.of(converter.toDTO(beneficiaryDTO.get()));
        }
        throw new RecordNotFoundException(String.format("Transaction whose applicant's email is %s does not exist!",email));
    }

    @Override
    public Optional<TransactionDTO> findTransactionById(Long id) {
        Optional<Transaction> beneficiaryDTO = transactionRepository.findById(id);
        if(beneficiaryDTO.isPresent()){
            return Optional.of(converter.toDTO(beneficiaryDTO.get()));
        }
        throw new RecordNotFoundException(String.format("Transaction whose applicant's id is %s does not exist!",id));
    }

    @Override
    public List<TransactionDTO> findAllTransactions() {
        List<Transaction> transactionDTO = transactionRepository.findAll();
        List<TransactionDTO> transactions = new ArrayList<>();
        if(transactionDTO.size()>0){
            transactionDTO.forEach((data)->{
                transactions.add(converter.toDTO(data));
            });
            return transactions;
        }
        throw new RecordNotFoundException(String.format("No data found"));
    }

    @Override
    public Optional<TransactionDTO> updateTransaction(Transaction transaction){
        transactionRepository.findById(transaction.getId());
        return Optional.of(converter.toDTO(transactionRepository.save(transaction)));
    }
}
