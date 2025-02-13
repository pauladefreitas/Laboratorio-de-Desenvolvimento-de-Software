package com.sistemademoedas.apisistemademoedas.model.dto.request;

import jakarta.validation.constraints.NotBlank;

public record EmailRequestDTO (@NotBlank String email) {
}
