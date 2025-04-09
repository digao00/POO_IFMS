package Aviação;

public abstract class Passagem {
    private float valor;
    private String passageiro;
    private Vôo voo;
    
    public Passagem(float valor, String passageiro, Vôo voo) {
        this.valor = valor;
        this.passageiro = passageiro;
        this.voo = voo;
    }

    public float getValor() {
        return valor;
    }
    public void setValor(float valor) {
        this.valor = valor;
    }

    public String getPassageiro() {
        return passageiro;
    }
    public void setPassageiro(String passageiro) {
        this.passageiro = passageiro;
    }

    public Vôo getVoo() {
        return voo;
    }
    public void setVoo(Vôo voo) {
        this.voo = voo;
    }
    public void print() {
        System.out.printf("\nValor: %.2f\nPassageiro: %s", getValor(), passageiro);
    }
}
