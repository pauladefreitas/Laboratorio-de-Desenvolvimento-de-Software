package com.sistemademoedas.apisistemademoedas.service;

import com.sistemademoedas.apisistemademoedas.model.GerenciadorMoedas;
import com.sistemademoedas.apisistemademoedas.repository.GerenciadorMoedasRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class GerenciadorMoedasService {

    @Autowired
    private GerenciadorMoedasRepository gerenciadorMoedasRepository;

    public void create(GerenciadorMoedas gerenciadorMoedas) {
        gerenciadorMoedasRepository.save(gerenciadorMoedas);
    }

}
