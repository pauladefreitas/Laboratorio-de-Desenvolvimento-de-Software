package com.sistemademoedas.apisistemademoedas.model.dto.response;

import com.sistemademoedas.apisistemademoedas.model.EmpresaParceira;
import com.sistemademoedas.apisistemademoedas.model.Vantagem;
import lombok.Builder;

import java.util.List;

@Builder
public record EmpresaParceiraResponseDTO(Long id,
                                         String nome,
                                         List<Vantagem> vantagens) {
    public static EmpresaParceiraResponseDTO fromEntity(EmpresaParceira empresaParceira) {
        return EmpresaParceiraResponseDTO.builder()
                .id(empresaParceira.getId())
                .nome(empresaParceira.getNome())
                .vantagens(empresaParceira.getVantagens())
                .build();
    }
}
