package com.sistemademoedas.apisistemademoedas.service;

import com.sistemademoedas.apisistemademoedas.exception.ProfessorNotFoundException;
import com.sistemademoedas.apisistemademoedas.model.GerenciadorMoedas;
import com.sistemademoedas.apisistemademoedas.model.Professor;
import com.sistemademoedas.apisistemademoedas.model.dto.request.GerenciadorMoedasRequestDTO;
import com.sistemademoedas.apisistemademoedas.model.dto.request.ProfessorRequestDTO;
import com.sistemademoedas.apisistemademoedas.model.dto.response.GerenciadorMoedasResponseDTO;
import com.sistemademoedas.apisistemademoedas.model.dto.response.ProfessorResponseDTO;
import com.sistemademoedas.apisistemademoedas.repository.GerenciadorMoedasRepository;
import com.sistemademoedas.apisistemademoedas.repository.ProfessorRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

@Service
public class ProfessorService {

    @Autowired
    private ProfessorRepository professorRepository;

    @Autowired
    private AlunoService alunoService;

    @Autowired
    private GerenciadorMoedasService gerenciadorMoedasService;

    @Autowired
    private GerenciadorMoedasRepository gerenciadorMoedasRepository;

    public Professor findByID(Long id){
        Optional<Professor> professor = professorRepository.findById(id);
        return professor.orElseThrow(() -> new RuntimeException("Professor não encontrado. Id" + id));
    }

    @Transactional
    public Professor create(Professor professor){
        professor.setId(null);
        professor = this.professorRepository.save(professor);
        return professor;
    }

    public ProfessorResponseDTO update(Long id, ProfessorRequestDTO professorRequestDTO) {
        var professor = professorRepository.findById(id)
                .orElseThrow(() -> new ProfessorNotFoundException("Professor não encontrado. Id " + id));
        professor.update(professorRequestDTO);
        professorRepository.save(professor);
        return ProfessorResponseDTO.fromEntity(professor);
    }

    public void delete(Long id){
        professorRepository.findById(id);
        try {
            professorRepository.deleteById(id);
        } catch (Exception e) {
            throw new RuntimeException("Não é possivel excluir um professor.");
        }
    }

    @Transactional
    public void enviaMoedas(Long id, GerenciadorMoedasRequestDTO gerenciadorMoedasRequestDTO) {
        var professor = professorRepository.findById(id)
                .orElseThrow(() -> new NoSuchElementException("Professor não encontrado. Id " + id));

        var aluno = alunoService.findByID(gerenciadorMoedasRequestDTO.idAluno());

        if (professor.getSaldoMoedas() < gerenciadorMoedasRequestDTO.moedas()) {
            throw new IllegalArgumentException("Saldo insuficiente. O saldo atual do professor é " + professor.getSaldoMoedas() + ".");
        }

        professor.setSaldoMoedas(professor.getSaldoMoedas() - gerenciadorMoedasRequestDTO.moedas());
        aluno.setSaldoMoedas(aluno.getSaldoMoedas() + gerenciadorMoedasRequestDTO.moedas());

        GerenciadorMoedas gerenciadorMoedas = GerenciadorMoedas.fromRequest(gerenciadorMoedasRequestDTO, professor, aluno);
        gerenciadorMoedasService.create(gerenciadorMoedas);
    }

    public List<GerenciadorMoedasResponseDTO> getAllTransactionsByProfessorId(Long id) {
        return gerenciadorMoedasRepository.findAllByProfessorId(id)
                .stream()
                .map(GerenciadorMoedasResponseDTO::fromEntity)
                .toList();
    }
}
