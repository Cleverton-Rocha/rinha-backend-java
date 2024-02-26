package com.rinhabackend.rinhadebackend2024q1.model;

import java.time.LocalDateTime;

import com.fasterxml.jackson.annotation.JsonProperty;

public record Transacao(
        Integer valor,
        TipoTransacao tipo,
        String descricao,
        @JsonProperty("realizada_em") LocalDateTime realizada_em) {
}
