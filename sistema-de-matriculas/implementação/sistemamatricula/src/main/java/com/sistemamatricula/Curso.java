package com.sistemamatricula;

import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;

@Entity
public class Curso {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "curso_id", unique = true, nullable = false)
    private Long id;

    @Column
    private String nome;

    @Column
    private int numCreditos;

    @OneToMany(mappedBy = "curso", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Disciplina> disciplinas;    

    public Curso(String nome, int numCreditos) {
        this.nome = nome;
        this.numCreditos = numCreditos;
        this.disciplinas = new ArrayList<>();
    }

    public Curso() {

    }

    public String getNome() {
        return nome;
    }

    public int getNumCreditos() {
        return numCreditos;
    }

    public List<Disciplina> getDisciplinas() {
        return disciplinas;
    }

    public void adicionarDisciplina(Disciplina disciplina) {
        System.out.println("Adicionando disciplina: " + disciplina.getNome() + " ao curso: " + nome);
        disciplinas.add(disciplina); 
    }

    public void removerDisciplina(Disciplina disciplina) {
        if (disciplinas.remove(disciplina)) {
            System.out.println("Removendo disciplina: " + disciplina.getNome() + " do curso " + nome);
        } else {
            System.out.println("Disciplina " + disciplina.getNome() + " não está no curso " + nome);
        }
    }

    @Override
    public String toString() {
        return "Curso: Nome = " + nome + ".\n O número de créditos é " + numCreditos + ";\nAs disciplinas que o compõem são: " + disciplinas;
    }
}
