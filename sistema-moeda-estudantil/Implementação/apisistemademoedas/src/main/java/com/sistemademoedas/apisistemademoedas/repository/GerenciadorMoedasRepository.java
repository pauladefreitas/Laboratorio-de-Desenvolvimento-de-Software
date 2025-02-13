package com.sistemademoedas.apisistemademoedas.repository;

import com.sistemademoedas.apisistemademoedas.model.GerenciadorMoedas;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface GerenciadorMoedasRepository extends JpaRepository<GerenciadorMoedas, Long> {

    List<GerenciadorMoedas> findAllByProfessorId(Long id);
    List<GerenciadorMoedas> findAllByAlunoUserAuthId(Long userAUthId);

}
