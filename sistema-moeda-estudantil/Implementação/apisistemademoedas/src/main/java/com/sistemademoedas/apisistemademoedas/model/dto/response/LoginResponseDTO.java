package com.sistemademoedas.apisistemademoedas.model.dto.response;

import com.sistemademoedas.apisistemademoedas.model.enums.RoleEnum;

public record LoginResponseDTO(Long id, String email, String tokenJWT, RoleEnum role) {
}
