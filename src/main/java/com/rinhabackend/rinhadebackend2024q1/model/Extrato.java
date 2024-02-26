package com.rinhabackend.rinhadebackend2024q1.model;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;

public record Extrato(
        Saldo saldo,
        @JsonProperty("ultimas_transacoes") List<Transacao> ultimasTransacoes) {
}
