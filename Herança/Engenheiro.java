package Herança;

public class Engenheiro extends Funcionario{
    private int crea;

    public Engenheiro(String nome, String cpf, int crea) {
        super(nome, cpf);
        this.crea = crea;
    }

    public int getCrea() {
        return crea;
    }

    public void setCrea(int crea) {
        this.crea = crea;
    }

    @Override
    public String toString() {
        return super.toString() + "\nCREA: " + crea;
    }
}