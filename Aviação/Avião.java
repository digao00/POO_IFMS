package Aviação;

public class Avião {
    private String modelo;
    private int capacidade;

    public Avião(String modelo, int capacidade) {
        this.modelo = modelo;
        this.capacidade = capacidade;
    }
    
    public String getModelo() {
        return modelo;
    }
    public void setModelo(String modelo) {
        this.modelo = modelo;
    }

    public int getCapacidade() {
        return capacidade;
    }
    public void setCapacidade(int capacidade) {
        this.capacidade = capacidade;
    }
}
