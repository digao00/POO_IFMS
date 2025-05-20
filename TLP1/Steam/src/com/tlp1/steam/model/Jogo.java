package com.tlp1.steam.model;

public class Jogo {
    private int id;
    private String nome;

    public Jogo(String nome) {
        this.nome = nome;
    }

    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }
    public void setNome(String nome) {
        this.nome = nome;
    }

    public void print() {
        System.out.printf("\n%d - %s", id, nome);
    }
}
