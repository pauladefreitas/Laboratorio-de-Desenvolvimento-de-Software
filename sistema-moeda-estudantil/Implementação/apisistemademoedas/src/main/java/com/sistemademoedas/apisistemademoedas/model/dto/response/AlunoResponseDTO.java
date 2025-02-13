package com.sistemademoedas.apisistemademoedas.model.dto.response;

import com.sistemademoedas.apisistemademoedas.model.Aluno;
import com.sistemademoedas.apisistemademoedas.model.InstituicaoEnsino;
import lombok.Builder;

@Builder
public record AlunoResponseDTO(String email,
                               String nome,
                               Long id,
                               String CPF,
                               String RG,
                               String endereco,
                               Integer saldoMoedas,
                               InstituicaoEnsino instituicaoEnsino,
                               String curso) {
    public static AlunoResponseDTO fromEntity(Aluno aluno) {
        return AlunoResponseDTO.builder()
                .email(aluno.getEmail())
                .nome(aluno.getNome())
                .id(aluno.getId())
                .RG(aluno.getRG())
                .CPF(aluno.getCPF())
                .endereco(aluno.getEndereco())
                .saldoMoedas(aluno.getSaldoMoedas())
                .curso(aluno.getCurso())
                .instituicaoEnsino(aluno.getInstituicaoEnsino())
                .build();
    }
}
