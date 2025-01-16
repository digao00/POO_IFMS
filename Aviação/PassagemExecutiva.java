package Aviação;

public class PassagemExecutiva extends Passagem {
    private boolean refeicao;

    public PassagemExecutiva(float valor, String passageiro, Vôo voo, boolean refeicao) {
        super(valor, passageiro, voo);
        this.refeicao = refeicao;
    }

    public boolean isRefeicao() {
        return refeicao;
    }

    public void setRefeicao(boolean refeicao) {
        this.refeicao = refeicao;
    }

    @Override
    public float getValor() {
        return super.getValor()*1.2f;
    }

    @Override
    public void print() {
        super.print();
        if (refeicao) {
            System.out.println("\nCom refeição");
        }
        else {
            System.out.println("\nSem refeição");
        }
    }
}
