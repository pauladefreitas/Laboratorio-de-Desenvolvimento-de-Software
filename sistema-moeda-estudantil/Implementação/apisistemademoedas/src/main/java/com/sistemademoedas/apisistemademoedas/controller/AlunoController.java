package com.sistemademoedas.apisistemademoedas.controller;

import com.sistemademoedas.apisistemademoedas.model.Aluno;
import com.sistemademoedas.apisistemademoedas.model.dto.request.AlunoRequestDTO;
import com.sistemademoedas.apisistemademoedas.model.dto.request.GerenciadorVantagensRequestDTO;
import com.sistemademoedas.apisistemademoedas.model.dto.response.AlunoResponseDTO;
import com.sistemademoedas.apisistemademoedas.model.security.UserAuth;
import com.sistemademoedas.apisistemademoedas.service.AlunoService;
import com.sistemademoedas.apisistemademoedas.service.security.UserAuthService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/aluno")
public class AlunoController {

    @Autowired
    private AlunoService alunoService;

    @GetMapping("/{id}")
    public ResponseEntity<Aluno> findById(@PathVariable Long id) {
        Aluno obj = this.alunoService.findByID(id);
        return ResponseEntity.ok().body(obj);
    }

    @GetMapping
    public ResponseEntity<List<AlunoResponseDTO>> getAll() {
        return ResponseEntity.ok(alunoService.getAll());
    }

    @PostMapping
    public ResponseEntity<Void> create(@RequestBody Aluno obj, @RequestParam String senha) {
        this.alunoService.create(obj, senha);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(obj.getId()).toUri();
        return ResponseEntity.created(uri).build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<AlunoResponseDTO> update(@PathVariable Long id, @RequestBody AlunoRequestDTO obj) {
        return ResponseEntity.ok(alunoService.update(id, obj));
    }

    @GetMapping("/auth/{userAuthId}")
    public ResponseEntity<Aluno> getByUserAuthId(@PathVariable Long userAuthId){
        return ResponseEntity.ok(alunoService.getByUserAuthId(userAuthId));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        this.alunoService.delete(id);
        return ResponseEntity.noContent().build();
    }

    @PostMapping("/{id}/resgateVantagens")
    public ResponseEntity<Void> resgataVantagem(@PathVariable Long id,
                                                @RequestBody @Valid GerenciadorVantagensRequestDTO gerenciadorVantagensRequestDTO) {
        alunoService.resgataVantagem(id, gerenciadorVantagensRequestDTO.vantagemId());
        return ResponseEntity.ok().build();
    }
}
