package com.sistemademoedas.apisistemademoedas.service;

import com.sistemademoedas.apisistemademoedas.exception.VantagemNotFoundException;
import com.sistemademoedas.apisistemademoedas.model.Vantagem;
import com.sistemademoedas.apisistemademoedas.model.dto.request.VantagemRequestDTO;
import com.sistemademoedas.apisistemademoedas.model.dto.response.VantagemResponseDTO;
import com.sistemademoedas.apisistemademoedas.repository.VantagemRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class VantagemService {

    @Autowired
    private VantagemRepository vantagemRepository;

    public Vantagem findByID(Long id){
        Optional<Vantagem> vantagem = vantagemRepository.findById(id);
        return vantagem.orElseThrow(() -> new RuntimeException("Vantagem não encontrada. Id" + id));
    }

    @Transactional
    public void create(Vantagem vantagem){
        vantagemRepository.save(vantagem);
    }

    public List<VantagemResponseDTO> getAll() {
        return vantagemRepository.findAll()
                .stream()
                .map(VantagemResponseDTO::fromEntity)
                .toList();
    }

    public List<VantagemResponseDTO> getAllByEmpresaParceiraId(Long empresa_parceira_id) {
        return vantagemRepository.findAllByEmpresaParceiraId(empresa_parceira_id)
                .stream()
                .map(VantagemResponseDTO::fromEntity)
                .toList();
    }

    public VantagemResponseDTO update(Long id, VantagemRequestDTO vantagemRequestDTO) {
        var vantagem = vantagemRepository.findById(id)
                .orElseThrow(() -> new VantagemNotFoundException("Vantagem não encontrada. Id " + id));
        vantagem.update(vantagemRequestDTO);
        vantagemRepository.save(vantagem);
        return VantagemResponseDTO.fromEntity(vantagem);
    }

    public void delete(Long id){
        vantagemRepository.findById(id);
        try {
            vantagemRepository.deleteById(id);
        } catch (Exception e) {
            throw new RuntimeException("Não é possivel excluir uma empresa parceira.");
        }
    }
}
