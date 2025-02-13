package com.sistemademoedas.apisistemademoedas.model;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.sistemademoedas.apisistemademoedas.exception.ProfessorNotFoundException;
import com.sistemademoedas.apisistemademoedas.model.dto.request.InstituicaoEnsinoRequestDTO;
import com.sistemademoedas.apisistemademoedas.model.dto.request.ProfessorRequestDTO;
import com.sistemademoedas.apisistemademoedas.model.dto.response.InstituicaoEnsinoResponseDTO;
import com.sistemademoedas.apisistemademoedas.model.enums.RoleEnum;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.beans.BeanUtils;

import java.util.List;

@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "tb_instituicoes")
public class InstituicaoEnsino {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nome;

    @OneToMany(mappedBy = "instituicaoEnsino")
    @JsonManagedReference
    private List<Professor> professores;

    @Enumerated
    RoleEnum role;

    public static InstituicaoEnsino fromRequest(InstituicaoEnsinoRequestDTO instituicaoEnsinoRequestDTO) {
        InstituicaoEnsino instituicaoEnsino = new InstituicaoEnsino();
        BeanUtils.copyProperties(instituicaoEnsinoRequestDTO, instituicaoEnsino);
        return instituicaoEnsino;
    }

    public void update(InstituicaoEnsinoRequestDTO instituicaoEnsinoRequestDTO) {
        this.nome = instituicaoEnsinoRequestDTO.nome() != null ? instituicaoEnsinoRequestDTO.nome() : this.nome;

        if (instituicaoEnsinoRequestDTO.professores() != null) {
            for (ProfessorRequestDTO professorRequestDTO : instituicaoEnsinoRequestDTO.professores()) {
                if (professorRequestDTO != null) {
                    Professor existingProfessor = this.professores.stream()
                            .filter(p -> p.getNome().equals(professorRequestDTO.nome()))
                            .findFirst()
                            .orElse(null);

                    if (existingProfessor != null) {
                        existingProfessor.update(professorRequestDTO);
                    } else {
                        throw new ProfessorNotFoundException("Professor com o nome " + professorRequestDTO.nome() + " não foi encontrado.");
                    }
                } else {
                    throw new ProfessorNotFoundException("ProfessorRequestDTO is null.");
                }
            }
        }
    }

}