package com.sistemademoedas.apisistemademoedas.model.dto.response;

import com.sistemademoedas.apisistemademoedas.model.EmpresaParceira;
import com.sistemademoedas.apisistemademoedas.model.Vantagem;
import lombok.Builder;

@Builder
public record VantagemResponseDTO(Long id,
                                  String nome,
                                  String descricao,
                                  Integer valor,
                                  String fotoUrl,
                                  EmpresaParceira empresaParceira) {
    public static VantagemResponseDTO fromEntity(Vantagem vantagem) {
        return VantagemResponseDTO.builder()
                .id(vantagem.getId())
                .nome(vantagem.getNome())
                .descricao(vantagem.getDescricao())
                .valor(vantagem.getValor())
                .fotoUrl(vantagem.getFotoUrl())
                .empresaParceira(vantagem.getEmpresaParceira())
                .build();
    }
}
