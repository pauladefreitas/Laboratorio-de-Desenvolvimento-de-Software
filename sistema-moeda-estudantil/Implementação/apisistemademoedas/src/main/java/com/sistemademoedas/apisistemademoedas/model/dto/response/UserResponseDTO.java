package com.sistemademoedas.apisistemademoedas.model.dto.response;

import com.sistemademoedas.apisistemademoedas.model.InstituicaoEnsino;
import com.sistemademoedas.apisistemademoedas.model.User;
import com.sistemademoedas.apisistemademoedas.model.enums.RoleEnum;
import lombok.Builder;

@Builder
public record UserResponseDTO(Long id,
                              String nome,
                              String CPF,
                              RoleEnum role,
                              InstituicaoEnsino instituicaoEnsino) {
    public static UserResponseDTO fromEntity(User user) {
        return UserResponseDTO.builder()
                .nome(user.getNome())
                .id(user.getId())
                .CPF(user.getCPF())
                .role(user.getRole())
                .instituicaoEnsino(user.getInstituicaoEnsino())
                .build();
    }
}
