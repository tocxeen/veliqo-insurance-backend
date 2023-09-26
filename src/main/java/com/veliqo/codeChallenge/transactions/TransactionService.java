package com.veliqo.codeChallenge.transactions;

import java.util.List;
import java.util.Optional;

public interface TransactionService {

    Optional<TransactionDTO> saveTransaction(Transaction transaction);

    Optional<TransactionDTO> findTransactionByApplicant(String email);

    Optional<TransactionDTO> findTransactionById(Long id);

    List<TransactionDTO> findAllTransactions();

    Optional<TransactionDTO> updateTransaction(Transaction transaction);
}
