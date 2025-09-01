package com.tlp2.steam.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.tlp2.steam.model.Jogador;

public interface JogadorRepository extends JpaRepository<Jogador, Long> {
    
}
