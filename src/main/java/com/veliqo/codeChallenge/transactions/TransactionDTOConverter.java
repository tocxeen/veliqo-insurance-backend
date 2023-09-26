package com.veliqo.codeChallenge.transactions;

import org.springframework.stereotype.Component;

/**
 * Author Richard K Chifamba on 9/24/2023
 **/

@Component
public class TransactionDTOConverter {

    public Transaction toEntity(TransactionDTO transactionDTO) {
        Transaction transaction = new Transaction();
        transaction.setAmount(transactionDTO.getAmount());
        transaction.setTransactionDoneBy(transactionDTO.getTransactionDoneBy());
        transaction.setCurrency(transactionDTO.getCurrency());
        transaction.setApplicantEmail(transactionDTO.getApplicantEmail());
        transaction.setDate(transactionDTO.getDate());
        return transaction;
    }

    public TransactionDTO toDTO(Transaction transaction){
        TransactionDTO transactionDTO = new TransactionDTO();
        transactionDTO.setAmount(transaction.getAmount());
        transactionDTO.setTransactionDoneBy(transaction.getTransactionDoneBy());
        transactionDTO.setCurrency(transaction.getCurrency());
        transactionDTO.setDate(transaction.getDate());
        transactionDTO.setApplicantEmail(transaction.getApplicantEmail());
        return transactionDTO;
    }
}
