package Herança;

public class Diretor extends Funcionario{
    private String cargo;

    public Diretor(String nome, String cpf, String cargo) {
        super(nome, cpf);
        this.cargo = cargo;
    }

    public String getCargo() {
        return cargo;
    }

    public void setCargo(String cargo) {
        this.cargo = cargo;
    }

    @Override
    public String toString() {
        return super.toString() + "\nCargo: " + cargo;
    }
}
