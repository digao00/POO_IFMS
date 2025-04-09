package Herança;

public class Gerente extends Funcionario{
    private String setor;

    public Gerente(String nome, String cpf, String setor) {
        super(nome, cpf);
        this.setor = setor;
    }

    public String getSetor() {
        return setor;
    }

    public void setSetor(String setor) {
        this.setor = setor;
    }

    @Override
    public String toString() {
        return super.toString() + "\nSetor: " + setor;
    }
}
