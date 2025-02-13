package com.sistemademoedas.apisistemademoedas.model.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

public record VantagemRequestDTO(@NotBlank String nome,
                                 @NotNull @Positive Integer valor,
                                 @NotBlank String descricao,
                                 @NotBlank String fotoUrl) { }
