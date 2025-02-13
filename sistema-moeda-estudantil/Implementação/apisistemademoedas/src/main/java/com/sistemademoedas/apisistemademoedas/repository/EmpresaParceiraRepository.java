package com.sistemademoedas.apisistemademoedas.repository;

import com.sistemademoedas.apisistemademoedas.model.EmpresaParceira;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EmpresaParceiraRepository extends JpaRepository<EmpresaParceira,Long> {
}
