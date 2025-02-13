package com.sistemademoedas.apisistemademoedas.controller;

import com.sistemademoedas.apisistemademoedas.model.Vantagem;
import com.sistemademoedas.apisistemademoedas.model.dto.request.VantagemRequestDTO;
import com.sistemademoedas.apisistemademoedas.model.dto.response.VantagemResponseDTO;
import com.sistemademoedas.apisistemademoedas.service.VantagemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/vantagens")
public class VantagemController {

    @Autowired
    private VantagemService vantagemService;

    @GetMapping("/{empresa_parceira_id}")
    public ResponseEntity<List<VantagemResponseDTO>> getAllByEmpresaParceiraId(@PathVariable Long empresa_parceira_id) {
        return ResponseEntity.ok(vantagemService.getAllByEmpresaParceiraId(empresa_parceira_id));
    }

    @GetMapping
    public ResponseEntity<List<VantagemResponseDTO>> getAll() {
        return ResponseEntity.ok(vantagemService.getAll());
    }

    @GetMapping("/vantagem/{id}")
    public ResponseEntity<Vantagem> findById(@PathVariable Long id) {
        Vantagem obj = this.vantagemService.findByID(id);
        return ResponseEntity.ok().body(obj);
    }

    @PutMapping("/{id}")
    public ResponseEntity<VantagemResponseDTO> update(@PathVariable Long id, @RequestBody VantagemRequestDTO vantagemRequestDTO) {
        return ResponseEntity.ok(vantagemService.update(id, vantagemRequestDTO));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        this.vantagemService.delete(id);
        return ResponseEntity.noContent().build();
    }

}
