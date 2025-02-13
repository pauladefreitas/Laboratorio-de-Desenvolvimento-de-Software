package com.sistemademoedas.apisistemademoedas.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.sistemademoedas.apisistemademoedas.model.dto.request.UserRequestDTO;
import com.sistemademoedas.apisistemademoedas.model.enums.RoleEnum;
import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "tb_users")
@DiscriminatorColumn(name = "user_type", discriminatorType = DiscriminatorType.STRING)
@Inheritance(strategy = InheritanceType.JOINED)
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    protected String nome;

    @Column(unique = true)
    protected String CPF;

    @Enumerated
    private RoleEnum role;

    @ManyToOne
    @JoinColumn(name = "instituicoes_id")
    @JsonBackReference
    protected InstituicaoEnsino instituicaoEnsino;

    public void update(UserRequestDTO userRequestDTO) {
        this.CPF = userRequestDTO.CPF() != null ? userRequestDTO.CPF() : this.CPF;
        this.nome = userRequestDTO.nome() != null ? userRequestDTO.nome() : this.nome;
    }
}
