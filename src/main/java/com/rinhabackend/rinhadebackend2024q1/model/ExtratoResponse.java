package com.rinhabackend.rinhadebackend2024q1.model;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;

public record ExtratoResponse(
                @JsonProperty("saldo") Saldo saldo,
                @JsonProperty("ultimas_transacoes") List<ExtratoTransacao> ultimasTransacoes) {
}
