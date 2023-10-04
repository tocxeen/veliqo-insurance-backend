package com.veliqo.codeChallenge.policy;

import com.fasterxml.jackson.annotation.JsonInclude;
import jakarta.persistence.*;
import lombok.Data;

import java.io.Serializable;
import java.math.BigDecimal;


/**
 * Author Richard K Chifamba on 9/24/2023
 **/


@Data
@Entity
@Table(	name = "policies")
@JsonInclude(JsonInclude.Include.NON_NULL)
public class Policy implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(nullable = false,updatable = false)
    private Long id;

    @Column(nullable = false, unique = true)
    private String name;

    private String currency;

    private BigDecimal amount;

}
