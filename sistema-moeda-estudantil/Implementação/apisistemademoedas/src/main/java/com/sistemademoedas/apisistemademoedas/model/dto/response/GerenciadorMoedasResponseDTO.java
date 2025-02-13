package com.sistemademoedas.apisistemademoedas.model.dto.response;

import com.sistemademoedas.apisistemademoedas.model.Aluno;
import com.sistemademoedas.apisistemademoedas.model.GerenciadorMoedas;
import com.sistemademoedas.apisistemademoedas.model.Professor;
import lombok.Builder;

@Builder
public record GerenciadorMoedasResponseDTO(Long id,
                                           Integer moedas,
                                           Aluno aluno,
                                           String descricao,
                                           String dataTransacao,
                                           Professor professor) {
    public static GerenciadorMoedasResponseDTO fromEntity(GerenciadorMoedas gerenciadorMoedas){
        return GerenciadorMoedasResponseDTO.builder()
                .id(gerenciadorMoedas.getId())
                .dataTransacao(gerenciadorMoedas.getDataTransacao())
                .descricao(gerenciadorMoedas.getDescricao())
                .moedas(gerenciadorMoedas.getMoedas())
                .aluno(gerenciadorMoedas.getAluno())
                .professor(gerenciadorMoedas.getProfessor())
                .build();
    }
}
