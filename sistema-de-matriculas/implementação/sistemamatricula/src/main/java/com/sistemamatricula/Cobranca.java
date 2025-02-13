package com.sistemamatricula;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class Cobranca {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "cobranca_id", unique = true, nullable = false)
    private Long id;

    public void notificarCobranca(Aluno aluno, Disciplina disciplina) {
        if (aluno == null || disciplina == null) {
            System.out.println("Aluno ou disciplina não fornecidos para a cobrança.");
        } else {
            System.out.println("Notificando cobrança para o aluno: " + aluno.getNome() + " na disciplina: " + disciplina.getNome());
        }
    }
}