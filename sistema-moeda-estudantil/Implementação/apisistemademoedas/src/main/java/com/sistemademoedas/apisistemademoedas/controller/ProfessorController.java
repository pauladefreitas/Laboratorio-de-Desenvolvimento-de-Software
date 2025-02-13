package com.sistemademoedas.apisistemademoedas.controller;

import com.sistemademoedas.apisistemademoedas.model.GerenciadorMoedas;
import com.sistemademoedas.apisistemademoedas.model.Professor;
import com.sistemademoedas.apisistemademoedas.model.dto.request.GerenciadorMoedasRequestDTO;
import com.sistemademoedas.apisistemademoedas.model.dto.request.ProfessorRequestDTO;
import com.sistemademoedas.apisistemademoedas.model.dto.response.GerenciadorMoedasResponseDTO;
import com.sistemademoedas.apisistemademoedas.model.dto.response.ProfessorResponseDTO;
import com.sistemademoedas.apisistemademoedas.service.ProfessorService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/professor")
public class ProfessorController {

    @Autowired
    private ProfessorService professorService;

    @GetMapping("/{id}")
    public ResponseEntity<Professor> findById(@PathVariable Long id) {
        Professor obj = this.professorService.findByID(id);
        return ResponseEntity.ok().body(obj);
    }

    @PostMapping
    public ResponseEntity<Void> create(@RequestBody Professor obj) {
        this.professorService.create(obj);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(obj.getId()).toUri();
        return ResponseEntity.created(uri).build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<ProfessorResponseDTO> update(@PathVariable Long id, @RequestBody ProfessorRequestDTO professorRequestDTO) {
        return ResponseEntity.ok(professorService.update(id, professorRequestDTO));
    }

    @PostMapping("/{id}/transacao")
    public ResponseEntity<Void> enviaMoeda(@RequestParam Long id,
                                           @RequestBody @Valid GerenciadorMoedasRequestDTO gerenciadorMoedasRequestDTO) {
        professorService.enviaMoedas(id, gerenciadorMoedasRequestDTO);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        this.professorService.delete(id);
        return ResponseEntity.noContent().build();
    }


}
