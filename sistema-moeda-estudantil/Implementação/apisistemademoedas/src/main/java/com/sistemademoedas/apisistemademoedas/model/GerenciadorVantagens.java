package com.sistemademoedas.apisistemademoedas.model;

import com.sistemademoedas.apisistemademoedas.model.dto.request.GerenciadorVantagensRequestDTO;
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
@Table(name = "tb_gerenciador_vantagens")
public class GerenciadorVantagens {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "aluno_id")
    private Aluno aluno;

    @ManyToOne
    @JoinColumn(name = "vantagem_id", nullable = true)
    private Vantagem vantagem;

    private String dataTransacao;

    @PrePersist
    public void prePersist() {
        LocalDateTime agora = LocalDateTime.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss");
        this.dataTransacao = agora.format(formatter);
    }
    public static GerenciadorVantagens fromRequest(GerenciadorVantagensRequestDTO gerenciadorVantagensRequestDTO, Vantagem vantagem, Aluno aluno) {
        GerenciadorVantagens gerenciadorVantagens = new GerenciadorVantagens();
        gerenciadorVantagens.setAluno(aluno);
        gerenciadorVantagens.setVantagem(vantagem);
        BeanUtils.copyProperties(gerenciadorVantagensRequestDTO, gerenciadorVantagens);
        return gerenciadorVantagens;
    }
}
