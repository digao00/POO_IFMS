package com.estrutura.estado.model;

public class Partido {
    private int id;
    private String sigla;
    private String nome_completo;
    private String orientacao;
    
    public Partido(String sigla, String nome_completo, String orientacao) {
        this.sigla = sigla;
        this.nome_completo = nome_completo;
        this.orientacao = orientacao;
    }
    
    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }

    public String getSigla() {
        return sigla;
    }
    public void setSigla(String sigla) {
        this.sigla = sigla;
    }
    
    public String getNome_completo() {
        return nome_completo;
    }
    public void setNome_completo(String nome_completo) {
        this.nome_completo = nome_completo;
    }
    
    public String getOrientacao() {
        return orientacao;
    }
    public void setOrientacao(String orientacao) {
        this.orientacao = orientacao;
    }

}
