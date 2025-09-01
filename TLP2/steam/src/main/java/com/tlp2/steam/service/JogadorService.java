package com.tlp2.steam.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tlp2.steam.model.Jogador;
import com.tlp2.steam.repository.JogadorRepository;

@Service
public class JogadorService {

    @Autowired
    private JogadorRepository jogadorRepository;

    public Jogador criarJogador(Jogador jogador) {
        return jogadorRepository.save(jogador);
    }
}
