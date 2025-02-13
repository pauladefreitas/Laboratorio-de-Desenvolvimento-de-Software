package com.sistemademoedas.apisistemademoedas.repository;

import com.sistemademoedas.apisistemademoedas.model.Aluno;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface AlunoRepository extends JpaRepository<Aluno,Long> {

    Optional<Aluno> findByUserAuth_Id(Long userAuthId);

    Optional<Aluno> findByUserAuthId(Long id);
}
