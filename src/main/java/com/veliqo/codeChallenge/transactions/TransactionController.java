package com.veliqo.codeChallenge.transactions;

import com.veliqo.codeChallenge.exceptions.RecordExistException;
import com.veliqo.codeChallenge.exceptions.RecordNotFoundException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Author Richard K Chifamba on 9/24/2023
 **/
@Slf4j
@RestController
@RequestMapping("api/v1/veliqo/transaction")
public class TransactionController {

    private final TransactionService transactionService;

    @Autowired
    public TransactionController(TransactionService transactionService) {
        this.transactionService = transactionService;
    }

    @Autowired
    private TransactionDTOConverter converter;

    @PostMapping("/add")
    public ResponseEntity<TransactionDTO> registerBeneficiary(@RequestBody TransactionDTO transactionDTO) {
        TransactionDTO savedPolicy = transactionService.saveTransaction(converter.toEntity(transactionDTO)).orElseThrow(()->
                new RecordExistException("Failed to create transaction"));
        return new ResponseEntity<>(savedPolicy, HttpStatus.CREATED);
    }

    @GetMapping("/getTransactionByApplicantEmail/{email}")
    public ResponseEntity<TransactionDTO> getTransactionByApplicantEmail(@PathVariable String email) {
        log.debug("finding transaction with email {}", email);
        TransactionDTO transactionDTO = transactionService.findTransactionByApplicant(email).orElseThrow(()->
                new RecordNotFoundException(String.format("Transaction not found")));
        return new ResponseEntity<>(transactionDTO,HttpStatus.OK);
    }

    @GetMapping("/getTransactionById/{id}")
    public ResponseEntity<TransactionDTO> getTransactionById(@PathVariable Long id) {
        log.debug("finding transaction with id {}", id);
        TransactionDTO transactionDTO = transactionService.findTransactionById(id).orElseThrow(()->
                new RecordNotFoundException(String.format("Transaction not found")));
        return new ResponseEntity<>(transactionDTO,HttpStatus.OK);
    }

    @GetMapping("/getTransactions")
    public ResponseEntity<List<TransactionDTO>> getTransactions() {
        List<TransactionDTO> transactions = transactionService.findAllTransactions();
        return new ResponseEntity<>(transactions,HttpStatus.OK);
    }

}
