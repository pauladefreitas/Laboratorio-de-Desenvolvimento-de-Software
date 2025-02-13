package com.sistemamatricula;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Inheritance;
import jakarta.persistence.InheritanceType;

@Entity
@Inheritance(strategy = InheritanceType.JOINED)
public class Usuario {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", unique = true, nullable = false)
    protected Long id;

    protected String senha;
    protected Boolean logado; 

    public Usuario(String senha) {
        this.senha = senha;
        this.logado = false;
    }

    public Usuario() {
        
    }

    public void logar(String senha) {
        if (this.senha.equals(senha)) {
            this.logado = true;
            System.out.println("Usuário logado com sucesso.");
        } else {
            System.out.println("Senha incorreta. Não foi possível logar.");
        }
    }

    public void deslogar() {
        this.logado = false;
        System.out.println("Usuário deslogado com sucesso.");
    }

    public Boolean isLogado() {
        System.out.println("Verificando status de login: " + logado);
        return logado;
    }
}
