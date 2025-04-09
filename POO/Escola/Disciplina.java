package Escola;

import java.util.ArrayList;

public class Disciplina {
    private String nome;
    private String prof;
    private int numEstudantes;
    private ArrayList<Estudante> listaEstudantes;
    
    public Disciplina(String nome, String prof, int numEstudantes) {
        this.nome = nome;
        this.prof = prof;
        this.numEstudantes = numEstudantes;
        this.listaEstudantes = new ArrayList<>();
    }

    public ArrayList<Estudante> getListaEstudantes() {
        return listaEstudantes;
    }
    public void setListaEstudantes(ArrayList<Estudante> listaEstudantes) {
        this.listaEstudantes = listaEstudantes;
    }
    
    public String getNome() {
        return nome;
    }
    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getProf() {
        return prof;
    }
    public void setProf(String prof) {
        this.prof = prof;
    }

    public int getNumEstudantes() {
        return numEstudantes;
    }
    public void setNumEstudantes(int numEstudantes) {
        this.numEstudantes = numEstudantes;
    }


    public void addEstudante(Estudante estudante) {
        listaEstudantes.add(estudante);
    }

    public void rmEstudante(int i) {
        listaEstudantes.remove(i);
    }

    public void print() {
        System.out.print("\n----------------");
        System.out.printf("\nDisciplina: %s\nProf: %s\nNumero de Estudantes: %d", nome, prof, numEstudantes);
        for (Estudante estudante : listaEstudantes) {
            estudante.print();
        }
    }
}
