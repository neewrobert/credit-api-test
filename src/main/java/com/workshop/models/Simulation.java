package com.workshop.models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.math.BigDecimal;

@JsonIgnoreProperties(ignoreUnknown = true)
public record Simulation(

        String name,
        String cpf,
        String email,
        BigDecimal amount,
        int installments,
        boolean insurance
) {

}
