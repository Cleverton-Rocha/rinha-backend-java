package com.rinhabackend.rinhadebackend2024q1.model;

import java.math.BigDecimal;

import jakarta.validation.constraints.Digits;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.PositiveOrZero;
import jakarta.validation.constraints.Size;

public record TransacaoRequest(
                @NotNull @PositiveOrZero @Digits(integer = 12, fraction = 0) BigDecimal valor,
                @NotEmpty @Pattern(regexp = "[cd]") String tipo,
                @NotBlank @Size(max = 10) String descricao) {
}
