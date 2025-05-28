package com.tlp1.steam.model;

public class Jogador_Jogo {
    private int id_jogador;
    private int id_jogo;
    
    public Jogador_Jogo(int id_jogador, int id_jogo) {
        this.id_jogador = id_jogador;
        this.id_jogo = id_jogo;
    }
    
    public int getId_jogador() {
        return id_jogador;
    }
    public void setId_jogador(int id_jogador) {
        this.id_jogador = id_jogador;
    }
    public int getId_jogo() {
        return id_jogo;
    }
    public void setId_jogo(int id_jogo) {
        this.id_jogo = id_jogo;
    }
}
