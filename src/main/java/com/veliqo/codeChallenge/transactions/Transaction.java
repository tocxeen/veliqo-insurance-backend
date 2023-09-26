package com.veliqo.codeChallenge.transactions;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.veliqo.codeChallenge.transactions.model.Currency;
import jakarta.persistence.*;
import lombok.Data;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDate;

/**
 * Author Richard K Chifamba on 9/24/2023
 **/


@Data
@Entity
@Table(	name = "transactions")
@JsonInclude(JsonInclude.Include.NON_NULL)
public class Transaction implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(nullable = false,updatable = false)
    private Long id;

    private Currency currency;

    private BigDecimal amount;

    private String applicantEmail;

    private String transactionDoneBy;

    @JsonFormat(pattern = "yyyy-MM-dd")
    private LocalDate date;



}
