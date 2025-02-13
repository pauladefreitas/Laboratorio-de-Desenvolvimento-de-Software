package com.sistemamatricula;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

@Entity
public class Aluno extends Usuario {

    @ManyToOne
    @JoinColumn(name = "curso_id")
    private Curso curso;

    @Column(name= "nome")
    private String nome;

    @Column(name= "matricula")
    private String matricula;

    public Aluno(String senha, String nome, String matricula, Curso curso) {
        super(senha); 
        this.nome = nome;
        this.matricula = matricula;
        this.curso = curso;
    }

    public Aluno(){
        
    }

    public Curso getCurso() {
        return curso;
    }

    public String getNome() {
        return nome;
    }

    public String getMatricula() {
        return matricula;
    }

    @Override
    public String toString() {
        return "Aluno: Nome = " + nome + ";\nMatrícula = " + matricula + ";\nCurso = " + curso.getNome();
    }
}
