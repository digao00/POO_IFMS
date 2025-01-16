package Aviação;

public class PassagemEconômica extends Passagem{
    private boolean bagagem;

    public PassagemEconômica(float valor, String passageiro, Vôo voo, boolean bagagem) {
        super(valor, passageiro, voo);
        this.bagagem = bagagem;
    }

    public boolean isBagagem() {
        return bagagem;
    }

    public void setBagagem(boolean bagagem) {
        this.bagagem = bagagem;
    }

    @Override
    public void print() {
        super.print();
        if (bagagem) {
            System.out.println("\nCom bagagem");
        }
        else {
            System.out.println("\nSem Bagagem");
        }
    }
}
