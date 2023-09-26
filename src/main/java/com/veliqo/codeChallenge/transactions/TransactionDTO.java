package com.veliqo.codeChallenge.transactions;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.veliqo.codeChallenge.transactions.model.Currency;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDate;

/**
 * Author Richard K Chifamba on 9/26/2023
 **/
@Getter
@Setter
@JsonInclude(JsonInclude.Include.NON_NULL)
public class TransactionDTO {

    private Currency currency;

    private BigDecimal amount;

    private String applicantEmail;

    private String transactionDoneBy;

    private LocalDate date;

}
