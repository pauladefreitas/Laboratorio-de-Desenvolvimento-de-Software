package com.sistemademoedas.apisistemademoedas.model;

import com.sistemademoedas.apisistemademoedas.model.dto.request.GerenciadorMoedasRequestDTO;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.beans.BeanUtils;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "tb_gerenciador_moedas")
public class GerenciadorMoedas {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "aluno_id")
    private Aluno aluno;

    @ManyToOne
    @JoinColumn(name = "professor_id", nullable = true)
    private Professor professor;
    private Integer moedas;
    private String dataTransacao;
    private String descricao;

    @PrePersist
    public void prePersist() {
        LocalDateTime agora = LocalDateTime.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss");
        this.dataTransacao = agora.format(formatter);
    }

    public static GerenciadorMoedas fromRequest(GerenciadorMoedasRequestDTO gerenciadorMoedasRequestDTO, Professor professor, Aluno aluno) {
        GerenciadorMoedas gerenciadorMoedas = new GerenciadorMoedas();
        gerenciadorMoedas.setAluno(aluno);
        gerenciadorMoedas.setProfessor(professor);
        gerenciadorMoedas.setMoedas(gerenciadorMoedasRequestDTO.moedas());
        gerenciadorMoedas.setDescricao(gerenciadorMoedasRequestDTO.descricao());
        BeanUtils.copyProperties(gerenciadorMoedasRequestDTO, gerenciadorMoedas);
        return gerenciadorMoedas;
    }
}
