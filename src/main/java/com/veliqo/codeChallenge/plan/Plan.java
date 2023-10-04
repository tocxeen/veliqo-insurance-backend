package com.veliqo.codeChallenge.plan;

import com.fasterxml.jackson.annotation.JsonInclude;
import jakarta.persistence.*;
import lombok.Data;

/**
 * Author Richard K Chifamba on 10/3/2023
 **/

@Data
@Entity
@Table(	name = "plan")
@JsonInclude(JsonInclude.Include.NON_NULL)
public class Plan {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(nullable = false,updatable = false)
    private Long id;

    private String applicantEmail;

    private String policy;


}
