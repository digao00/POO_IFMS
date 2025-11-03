package com.tlp2.steam.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tlp2.steam.exception.JogadorNotFoundException;
import com.tlp2.steam.model.Jogador;
import com.tlp2.steam.repository.JogadorRepository;

@Service
public class JogadorService {

    @Autowired
    private JogadorRepository jogadorRepository;

    public Jogador criarJogador(Jogador jogador) {
        return jogadorRepository.save(jogador);
    }

    public Jogador buscarJogadorPorId(Long id) throws JogadorNotFoundException {
        Optional<Jogador> opt = jogadorRepository.findById(id);
        if (opt.isPresent()) {
            return opt.get();
        }
        else {
            throw new JogadorNotFoundException("Jogador " + id + " não existe");
        }
    }

    public void apagarJogador(Long id) throws JogadorNotFoundException {
        Jogador jogador = buscarJogadorPorId(id);
        jogadorRepository.delete(jogador);
    }
}
