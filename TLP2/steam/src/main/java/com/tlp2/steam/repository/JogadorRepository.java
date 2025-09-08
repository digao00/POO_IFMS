package com.tlp2.steam.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.tlp2.steam.model.Jogador;

public interface JogadorRepository extends JpaRepository<Jogador, Long> {
    @Override
    default <S extends Jogador> S save(S entity) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'save'");
    }
}
