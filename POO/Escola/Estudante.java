package Escola;

public class Estudante {
    private String cpf;
    private String nome;
    private short idade;

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }


    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }


    public int getIdade() {
        return idade;
    }

    public void setIdade(short idade) {
        this.idade = idade;
    }


    public Estudante(String cpf, String nome, short idade) {
        this.cpf = cpf;
        this.nome = nome;
        this.idade = idade;
    }

    public void print() {
        System.out.printf("\nNome: %s\nCPF: %s\nIdade: %d", nome,cpf,idade);
    }
}