package com.tlp2.steam.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.tlp2.steam.model.Jogo;

public interface JogoRepository extends JpaRepository<Jogo, Long> {

}
