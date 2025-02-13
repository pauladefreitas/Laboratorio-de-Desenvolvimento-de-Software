package com.sistemademoedas.apisistemademoedas.model.dto.request;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

public record AuthRequestDTO(@NotBlank @Email String email,
                             @NotBlank String senha) {
}
