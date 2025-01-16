package Aviação;

import java.util.ArrayList;

public class Vôo {
    private int numero;
    private String data;
    private Aeroporto origem;
    private Aeroporto destino;
    private Avião aviao;
    private ArrayList<Passagem> lista_passagens = new ArrayList<>();

    public Vôo(int numero, String data, Aeroporto origem, Aeroporto destino, Avião aviao) {
        this.numero = numero;
        this.data = data;
        this.origem = origem;
        this.destino = destino;
        this.aviao = aviao;
        origem.adicionarVoo(this);
        destino.adicionarVoo(this);
    }

    public Avião getAviao() {
        return aviao;
    }

    public void setAviao(Avião aviao) {
        this.aviao = aviao;
    }

    public Aeroporto getOrigem() {
        return origem;
    }
    public void setOrigem(Aeroporto origem) {
        this.origem = origem;
    }

    public Aeroporto getDestino() {
        return destino;
    }
    public void setDestino(Aeroporto destino) {
        this.destino = destino;
    }
    
    public String getData() {
        return data;
    }
    public void setData(String data) {
        this.data = data;
    }

    public int getNumero() {
        return numero;
    }
    public void setNumero(int numero) {
        this.numero = numero;
    }

    public void adicionarPassagem(Passagem passagem) {
        if (qtdeVagas() == 0) {
            System.out.println("Erro: Vagas já estão preenchidas");
        }
        else {
            if (passagem.getVoo().getNumero() == this.numero) {
                lista_passagens.add(passagem);
            }
            else {
                System.out.println("Erro: Passagem não corresponde com vôo");
            }
        }
    }

    public Passagem getPassagem(int i) {
        if (i < lista_passagens.size() && i >= 0) {
            return lista_passagens.get(i);
        }
        else {
            System.out.println("Erro: Ìndice inexistente");
            return null;
        }
    }

    public int qtdeVagas() {
        return aviao.getCapacidade() - lista_passagens.size();
    }

    public void print() {
        System.out.printf("\nNúmero: %d\nData: %s\nOrigem: %s\nDestino: %s\nAvião: %s\n", numero, data, origem.getNome(), destino.getNome(), aviao.getModelo());
        for (Passagem passagem : lista_passagens) {
            passagem.print();
        }
    }
}
