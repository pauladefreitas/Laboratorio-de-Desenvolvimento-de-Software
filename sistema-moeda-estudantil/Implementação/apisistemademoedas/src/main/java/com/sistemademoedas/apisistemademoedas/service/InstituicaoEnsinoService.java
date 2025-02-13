package com.sistemademoedas.apisistemademoedas.service;

import com.sistemademoedas.apisistemademoedas.exception.InstituicaoEnsinoNotFoundException;
import com.sistemademoedas.apisistemademoedas.model.InstituicaoEnsino;
import com.sistemademoedas.apisistemademoedas.model.dto.request.InstituicaoEnsinoRequestDTO;
import com.sistemademoedas.apisistemademoedas.model.dto.response.InstituicaoEnsinoResponseDTO;
import com.sistemademoedas.apisistemademoedas.repository.InstituicaoEnsinoRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class InstituicaoEnsinoService {

    @Autowired
    private InstituicaoEnsinoRepository instituicaoEnsinoRepository;

    public InstituicaoEnsino findByID(Long id){
        Optional<InstituicaoEnsino> instituicaoEnsino = instituicaoEnsinoRepository.findById(id);
        return instituicaoEnsino.orElseThrow(() -> new RuntimeException("Instituição de ensino não encontrada. Id" + id));
    }

    @Transactional
    public InstituicaoEnsino create(InstituicaoEnsino instituicaoEnsino){
        instituicaoEnsino.setId(null);
        instituicaoEnsino = this.instituicaoEnsinoRepository.save(instituicaoEnsino);
        return instituicaoEnsino;
    }

    public InstituicaoEnsinoResponseDTO update(Long id, InstituicaoEnsinoRequestDTO instituicaoEnsinoRequestDTO) {
        var instituicaoEnsino = instituicaoEnsinoRepository.findById(id)
                .orElseThrow(() -> new InstituicaoEnsinoNotFoundException("Instituição de ensino não encontrada. Id " + id));
        instituicaoEnsino.update(instituicaoEnsinoRequestDTO);
        instituicaoEnsinoRepository.save(instituicaoEnsino);
        return InstituicaoEnsinoResponseDTO.fromEntity(instituicaoEnsino);
    }

    public void delete(Long id){
        instituicaoEnsinoRepository.findById(id);
        try {
            instituicaoEnsinoRepository.deleteById(id);
        } catch (Exception e) {
            throw new RuntimeException("Não é possivel excluir uma instituição de ensino.");
        }
    }
}
