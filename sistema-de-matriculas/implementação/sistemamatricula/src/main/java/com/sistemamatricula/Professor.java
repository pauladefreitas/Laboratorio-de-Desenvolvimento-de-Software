package com.sistemamatricula;

import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.OneToMany;

@Entity
public class Professor extends Usuario {

    @Column(name = "nome")
    private String nome;

    @OneToMany(mappedBy = "professor", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Disciplina> disciplinas;

    public Professor(String nome, String senha) {
        super(senha);
        this.nome = nome;
        this.disciplinas = new ArrayList<Disciplina>();
    }

    public Professor() {
        
    }

    public String getNome() {
        return nome;
    }

    public List<Disciplina> getDisciplinas() {
        return disciplinas;
    }

    public List<Aluno> listarAlunosMatriculados(Disciplina disciplina) {
        if (disciplinas.contains(disciplina)) {
            return disciplina.getAlunos();
        } else {
            System.out.println("O professor não leciona esta disciplina.");
            return null;
        }
    }

    public void addDisciplinas(Disciplina disciplina) {
        disciplinas.add(disciplina); 
    }

    @Override
    public String toString() {
        return "Professor: \n Nome = " + nome + ".\nDisciplinas que leciona = " + disciplinas;
    }
}
