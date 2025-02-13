package com.sistemademoedas.apisistemademoedas.controller;


import com.sistemademoedas.apisistemademoedas.model.dto.response.GerenciadorMoedasResponseDTO;
import com.sistemademoedas.apisistemademoedas.service.AlunoService;
import com.sistemademoedas.apisistemademoedas.service.ProfessorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/gerenciador_moedas")
public class GerenciadorMoedasController {

    @Autowired
    private ProfessorService professorService;

    @Autowired
    private AlunoService alunoService;

    @GetMapping("/professor/{professor_id}")
    public ResponseEntity<List<GerenciadorMoedasResponseDTO>> findAllTransactionsByProfessorId(@PathVariable Long professor_id) {
        return ResponseEntity.ok(professorService.getAllTransactionsByProfessorId(professor_id));
    }

    @GetMapping("/aluno/{userAuthId}")
    public ResponseEntity<List<GerenciadorMoedasResponseDTO>> findAllTransactionsByAlunoId(@PathVariable Long userAuthId) {
        return ResponseEntity.ok(alunoService.getAllTransactionsByAlunoId(userAuthId));
    }
}
