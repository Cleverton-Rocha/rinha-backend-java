package com.rinhabackend.rinhadebackend2024q1.model;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public record TransacaoRequest(
    @NotNull Integer valor,
    @NotEmpty TipoTransacao tipo,
    @NotBlank @Size(max = 10) String descricao) {
}
