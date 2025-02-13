package com.sistemademoedas.apisistemademoedas.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.sistemademoedas.apisistemademoedas.model.dto.request.VantagemRequestDTO;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.beans.BeanUtils;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "tb_vantagens")
public class Vantagem {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nome;
    private String descricao;
    private Integer valor;
    private String fotoUrl;

    @ManyToOne
    @JoinColumn(name = "empresa_parceira_id", nullable = false)
    @JsonBackReference
    private EmpresaParceira empresaParceira;

    public static Vantagem fromRequest(VantagemRequestDTO vantagemRequestDTO, EmpresaParceira empresaParceira) {
        Vantagem vantagem = new Vantagem();
        vantagem.setEmpresaParceira(empresaParceira);
        BeanUtils.copyProperties(vantagemRequestDTO, vantagem);
        return vantagem;
    }

    public void update(VantagemRequestDTO vantagemRequestDTO) {
        this.nome = vantagemRequestDTO.nome() != null ? vantagemRequestDTO.nome() : this.nome;
        this.descricao = vantagemRequestDTO.descricao() != null ? vantagemRequestDTO.descricao() : this.descricao;
        this.valor = vantagemRequestDTO.valor() != null ? vantagemRequestDTO.valor() : this.valor;
    }
}
