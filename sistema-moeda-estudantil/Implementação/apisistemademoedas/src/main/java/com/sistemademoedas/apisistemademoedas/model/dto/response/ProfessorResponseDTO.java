package com.sistemademoedas.apisistemademoedas.model.dto.response;

import com.sistemademoedas.apisistemademoedas.model.InstituicaoEnsino;
import com.sistemademoedas.apisistemademoedas.model.Professor;
import lombok.Builder;

@Builder
public record ProfessorResponseDTO(String departamento,
                                   Long id,
                                   String nome,
                                   String CPF,
                                   int saldoMoedas,
                                   InstituicaoEnsino instituicaoEnsino) {
    public static ProfessorResponseDTO fromEntity(Professor professor) {
        return ProfessorResponseDTO.builder()
                .departamento(professor.getDepartamento())
                .id(professor.getId())
                .nome(professor.getNome())
                .CPF(professor.getCPF())
                .saldoMoedas(professor.getSaldoMoedas())
                .instituicaoEnsino(professor.getInstituicaoEnsino())
                .build();
    }
}
