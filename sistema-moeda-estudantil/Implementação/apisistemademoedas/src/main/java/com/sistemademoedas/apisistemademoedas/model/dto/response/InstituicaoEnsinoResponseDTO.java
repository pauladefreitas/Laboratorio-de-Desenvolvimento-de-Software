package com.sistemademoedas.apisistemademoedas.model.dto.response;

import com.sistemademoedas.apisistemademoedas.model.InstituicaoEnsino;
import com.sistemademoedas.apisistemademoedas.model.Professor;
import lombok.Builder;

import java.util.List;

@Builder
public record InstituicaoEnsinoResponseDTO(String nome,
                                           Long id,
                                           List<Professor> professores) {
    public static InstituicaoEnsinoResponseDTO fromEntity(InstituicaoEnsino instituicaoEnsino) {
        return InstituicaoEnsinoResponseDTO.builder()
                .id(instituicaoEnsino.getId())
                .nome(instituicaoEnsino.getNome())
                .professores(instituicaoEnsino.getProfessores())
                .build();
    }
}