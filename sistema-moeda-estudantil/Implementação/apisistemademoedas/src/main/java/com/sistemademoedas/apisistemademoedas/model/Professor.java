package com.sistemademoedas.apisistemademoedas.model;

import com.sistemademoedas.apisistemademoedas.model.dto.request.ProfessorRequestDTO;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.beans.BeanUtils;

@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
@DiscriminatorValue("PROFESSOR")
public class Professor extends User {

    private String departamento;
    private Integer saldoMoedas;

    public static Professor fromRequest(ProfessorRequestDTO professorRequestDTO, InstituicaoEnsino instituicaoEnsino) {
        Professor professor = new Professor();
        BeanUtils.copyProperties(professorRequestDTO, professor);
        professor.setInstituicaoEnsino(instituicaoEnsino);
        return professor;
    }

    public void update(ProfessorRequestDTO professorRequestDTO) {
        this.departamento = professorRequestDTO.departamento() != null ? professorRequestDTO.departamento() : this.departamento;
        this.saldoMoedas = professorRequestDTO.saldoMoedas() != 0 ? professorRequestDTO.saldoMoedas() : this.saldoMoedas;

        if (professorRequestDTO.instituicaoEnsino() != null) {
            this.instituicaoEnsino.update(professorRequestDTO.instituicaoEnsino());
        }
    }

}
