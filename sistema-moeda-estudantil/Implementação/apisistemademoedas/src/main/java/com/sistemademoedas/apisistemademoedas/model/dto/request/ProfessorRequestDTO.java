package com.sistemademoedas.apisistemademoedas.model.dto.request;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record ProfessorRequestDTO(@NotBlank String departamento,
                                  @NotBlank @Email String email,
                                  @NotBlank String nome,
                                  @NotNull String CPF,
                                  @NotNull Integer saldoMoedas,
                                  @NotNull InstituicaoEnsinoRequestDTO instituicaoEnsino) {}
