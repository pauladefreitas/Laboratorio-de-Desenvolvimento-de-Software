package com.sistemademoedas.apisistemademoedas.model.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

public record GerenciadorMoedasRequestDTO(@NotNull @Positive Integer moedas,
                                          @NotNull Long idAluno,
                                          @NotBlank String descricao) {
}
