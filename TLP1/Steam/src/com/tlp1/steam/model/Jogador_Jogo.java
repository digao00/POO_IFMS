package com.tlp1.steam.model;

public class Jogador_Jogo {
    private Jogador jogador;
    private Jogo jogo;

    public Jogador_Jogo(Jogador jogador, Jogo jogo) {
        this.jogador = jogador;
        this.jogo = jogo;
    }
    
    public Jogador getJogador() {
        return jogador;
    }
    public void setJogador(Jogador jogador) {
        this.jogador = jogador;
    }
    public Jogo getJogo() {
        return jogo;
    }
    public void setJogo(Jogo jogo) {
        this.jogo = jogo;
    }

}
