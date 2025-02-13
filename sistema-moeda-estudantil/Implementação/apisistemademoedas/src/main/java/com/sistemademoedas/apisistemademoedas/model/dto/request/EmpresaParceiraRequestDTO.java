package com.sistemademoedas.apisistemademoedas.model.dto.request;

import com.sistemademoedas.apisistemademoedas.model.Vantagem;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

import java.util.List;

public record EmpresaParceiraRequestDTO(@NotBlank String nome,
                                        @NotBlank @Email String email,
                                        List<Vantagem> vantagens) { }
