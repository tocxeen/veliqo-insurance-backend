package com.veliqo.codeChallenge.applicant;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.veliqo.codeChallenge.applicant.models.Married;
import com.veliqo.codeChallenge.applicant.models.Sex;
import jakarta.persistence.*;
import lombok.Data;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDate;

/**
 * Author Richard K Chifamba on 9/23/2023
 **/

@Data
@Entity
@Table(name="applicant",
        uniqueConstraints = {
                @UniqueConstraint(columnNames = "email")
        })

public class Applicant implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(nullable = false,updatable = false)
    private Long id;

    @Column(nullable = false, unique = true)
    private String email;

    private String street;

    private String city;

    private String zipCode;

    private String country;

    @JsonFormat(pattern = "yyyy-MM-dd")
    private LocalDate dob;

    @Enumerated(EnumType.STRING)
    private Sex sex;

    @Enumerated(EnumType.STRING)
    private Married married;

    private BigDecimal balance;

    @JsonFormat(pattern = "yyyy-MM-dd")
    private LocalDate lastUpdate;

    @JsonFormat(pattern = "yyyy-MM-dd")
    private LocalDate dateCreated;
}
