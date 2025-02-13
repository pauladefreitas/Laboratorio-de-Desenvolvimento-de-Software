package com.sistemademoedas.apisistemademoedas.model.dto.request;

import com.sistemademoedas.apisistemademoedas.model.InstituicaoEnsino;
import com.sistemademoedas.apisistemademoedas.model.enums.RoleEnum;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record AlunoRequestDTO(@NotBlank @Email String email,
                              @NotBlank String nome,
                              @NotNull String CPF,
                              @NotBlank String RG,
                              @NotBlank String endereco,
                              @NotNull InstituicaoEnsinoRequestDTO instituicaoEnsino,
                              @NotBlank String curso,
                              @NotNull Integer saldoMoedas,
                              @NotNull RoleEnum role) {
}
