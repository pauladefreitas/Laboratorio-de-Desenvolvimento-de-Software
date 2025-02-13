package com.sistemademoedas.apisistemademoedas.model;

import com.sistemademoedas.apisistemademoedas.model.security.UserAuth;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.OneToOne;
import net.minidev.json.annotate.JsonIgnore;
import org.springframework.beans.BeanUtils;

import com.sistemademoedas.apisistemademoedas.model.dto.request.AlunoRequestDTO;

import jakarta.persistence.Column;
import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
@DiscriminatorValue("ALUNO")
public class Aluno extends User {

    @Column(unique = true)
    private String email;
    private String RG;
    private String endereco;
    private Integer saldoMoedas;

    private String curso;

    @OneToOne
    @JsonIgnore
    @Schema(hidden = true)
    private UserAuth userAuth;

    public static Aluno fromRequest(AlunoRequestDTO alunoRequestDTO, InstituicaoEnsino instituicaoEnsino) {
        Aluno aluno = new Aluno();
        aluno.setInstituicaoEnsino(instituicaoEnsino);
        BeanUtils.copyProperties(alunoRequestDTO, aluno);
        return aluno;
    }

    public void update(AlunoRequestDTO alunoRequestDTO) {
        this.CPF = alunoRequestDTO.CPF() != null ? alunoRequestDTO.CPF() : this.CPF;
        this.nome = alunoRequestDTO.nome() != null ? alunoRequestDTO.nome() : this.nome;
        this.curso = alunoRequestDTO.curso() != null ? alunoRequestDTO.curso() : this.curso;
        this.RG = alunoRequestDTO.RG() != null ? alunoRequestDTO.RG() : this.RG;
        this.email = alunoRequestDTO.email() != null ? alunoRequestDTO.email() : this.email;
        this.saldoMoedas = alunoRequestDTO.saldoMoedas() != null ? alunoRequestDTO.saldoMoedas() : this.saldoMoedas;
        if (alunoRequestDTO.instituicaoEnsino() != null) {
            this.instituicaoEnsino.update(alunoRequestDTO.instituicaoEnsino());
        }
        this.endereco = alunoRequestDTO.endereco() != null ? alunoRequestDTO.endereco() : this.endereco;
    }
}