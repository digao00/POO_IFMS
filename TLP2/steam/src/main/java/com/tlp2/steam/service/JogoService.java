package com.tlp2.steam.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tlp2.steam.model.Jogo;
import com.tlp2.steam.repository.JogoRepository;

@Service
public class JogoService {

    @Autowired
    private JogoRepository jogoRepository;

    public List<Jogo> listarJogos() {
        return jogoRepository.findAll();
    }
}
