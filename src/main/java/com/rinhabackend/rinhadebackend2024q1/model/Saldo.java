package com.rinhabackend.rinhadebackend2024q1.model;

import java.time.LocalDateTime;

import com.fasterxml.jackson.annotation.JsonProperty;

public record Saldo(
        Integer total,
        @JsonProperty("data_extrato") LocalDateTime dataExtrato,
        Integer limite) {
}
