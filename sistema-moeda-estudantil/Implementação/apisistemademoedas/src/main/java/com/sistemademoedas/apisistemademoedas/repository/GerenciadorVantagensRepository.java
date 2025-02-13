package com.sistemademoedas.apisistemademoedas.repository;

import com.sistemademoedas.apisistemademoedas.model.GerenciadorVantagens;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface GerenciadorVantagensRepository extends JpaRepository<GerenciadorVantagens, Long> {
  List<GerenciadorVantagens> findAllByAlunoUserAuthId(Long id);
}
