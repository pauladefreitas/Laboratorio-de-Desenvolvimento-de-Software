package com.sistemademoedas.apisistemademoedas.controller;

import com.sistemademoedas.apisistemademoedas.model.EmpresaParceira;
import com.sistemademoedas.apisistemademoedas.model.dto.request.EmpresaParceiraRequestDTO;
import com.sistemademoedas.apisistemademoedas.model.dto.request.VantagemRequestDTO;
import com.sistemademoedas.apisistemademoedas.model.dto.response.EmpresaParceiraResponseDTO;
import com.sistemademoedas.apisistemademoedas.model.dto.response.VantagemResponseDTO;
import com.sistemademoedas.apisistemademoedas.service.EmpresaParceiraService;
import com.sistemademoedas.apisistemademoedas.service.VantagemService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/empresas_parceiras")
public class EmpresaParceiraController {

    @Autowired
    private EmpresaParceiraService empresaParceiraService;

    @Autowired
    private VantagemService vantagemService;

    @GetMapping("/{id}")
    public ResponseEntity<EmpresaParceira> findById(@PathVariable Long id) {
        EmpresaParceira obj = this.empresaParceiraService.findByID(id);
        return ResponseEntity.ok().body(obj);
    }

    @GetMapping
    public ResponseEntity<List<EmpresaParceiraResponseDTO>> getAll() {
        return ResponseEntity.ok(empresaParceiraService.getAll());
    }

    @PostMapping
    public ResponseEntity<EmpresaParceiraResponseDTO> create(@RequestBody @Valid EmpresaParceiraRequestDTO obj, @RequestParam String senha) {
        return ResponseEntity.status(HttpStatus.CREATED).body(empresaParceiraService.create(obj, senha));
    }

    @PutMapping("/{id}")
    public ResponseEntity<EmpresaParceiraResponseDTO> update(@PathVariable Long id, @RequestBody EmpresaParceiraRequestDTO empresaParceiraRequestDTO) {
        return ResponseEntity.ok(empresaParceiraService.update(id, empresaParceiraRequestDTO));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        this.empresaParceiraService.delete(id);
        return ResponseEntity.noContent().build();
    }

    @PostMapping("/{id}/vantagens")
    public ResponseEntity<Void> createVantagem(@RequestParam Long id,
                                               @RequestBody @Valid VantagemRequestDTO vantagemRequestDTO) {
        empresaParceiraService.addVantagem(id, vantagemRequestDTO);
        return ResponseEntity.ok().build();
    }
}
