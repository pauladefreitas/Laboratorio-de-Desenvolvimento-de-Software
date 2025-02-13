package com.sistemademoedas.apisistemademoedas.controller;


import com.sistemademoedas.apisistemademoedas.model.dto.response.GerenciadorVantagensResponseDTO;
import com.sistemademoedas.apisistemademoedas.service.AlunoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/gerenciador_vantagens")
public class GerenciadorVantagensController {

    @Autowired
    private AlunoService alunoService;

    @GetMapping("/{userAuthId}")
    public ResponseEntity<List<GerenciadorVantagensResponseDTO>> findAllVantagensByAlunoId(@PathVariable Long userAuthId) {
        return ResponseEntity.ok(alunoService.getAllVantagensByAlunoId(userAuthId));
    }
}