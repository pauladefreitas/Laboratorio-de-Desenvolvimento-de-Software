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
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;

@Entity
public class Disciplina {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "disciplina_id", unique = true, nullable = false)
    private Long id;

    @Column(name = "nome")
    private String nome;

    @Column(name = "cargaHoraria")
    private int cargaHoraria;

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinColumn(name = "disciplina_id")
    private List<Aluno> alunos;

    @ManyToOne
    @JoinColumn(name = "professor_id")
    private Professor professor;

    @Column(name = "ativa")
    private boolean ativa;

    @ManyToOne
    @JoinColumn(name = "curso_id", nullable = false) 
    private Curso curso;

    @Column(name = "obrigatorio")
    private boolean obrigatorio;

    @Column(name = "numCreditos")
    private int numCreditos;

    public Disciplina(String nome, int cargaHoraria, Professor professor, Curso curso) {
        this.nome = nome;
        this.cargaHoraria = cargaHoraria;
        this.professor = professor;
        this.ativa = false;
        this.alunos = new ArrayList<>();
        this.curso = curso;
    }

    public Disciplina() {

    }

    public String getNome() {
        return nome;
    }

    public int getCargaHoraria() {
        return cargaHoraria;
    }

    public List<Aluno> getAlunos() {
        return alunos;
    }

    public Professor getProfessor() {
        return professor;
    }

    public Curso getCurso() {
        return curso;
    }

    public boolean isObrigatorio() {
        return obrigatorio;
    }

    public boolean isAtiva() {
        return ativa = true;
    }

    public int getnumCreditos() {
        return numCreditos;
    }

    public void addAluno(Aluno aluno) {
        System.out.println("Adicionando aluno " + aluno.getNome() + " à disciplina: " + nome);
        alunos.add(aluno);
    }

    public void removerAluno(Aluno aluno) {
        if (alunos.remove(aluno)) {
            System.out.println("Removendo aluno " + aluno.getNome() + " da disciplina: " + nome);
        } else {
            System.out.println("Aluno " + aluno.getNome() + " não está matriculado na disciplina: " + nome);
        }
    }

    public void periodoMatricula() {
        if (alunos.size() >= 3) {
            this.ativa = true;
            System.out.println("Disciplina " + nome + " será ofertada no próximo semestre.");
        } else {
            this.ativa = false;
            System.out.println("Disciplina " + nome + " não atingiu o número mínimo de alunos e será cancelada.");
        }
    }

    public void setProfessor(Professor professor) {
        this.professor = professor;
        if (!professor.getDisciplinas().contains(this)) {
            professor.getDisciplinas().add(this);
        }
    }

    @Override
    public String toString() {
        return "Disciplina: Nome = " + nome + ";\nCarga horária = " + cargaHoraria + ";\nProfessor = " + professor.getNome() + ";\nAtiva = " + ativa;
    }
}
