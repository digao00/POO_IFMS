package Lista;

import java.util.ArrayList;

public class Disciplina {
    private String nome;
    private ArrayList<Estudante> listaEstudantes = new ArrayList<>();

    public Disciplina(String nome) {
        this.nome = nome;
    }

    public void addEstudante(Estudante e) {
        listaEstudantes.add(e);
    }
    public void rmEstudante(int i) {
        listaEstudantes.remove(i);
    }

    public void print() {
        System.out.printf("Nome: %s\nEstudantes: \n", nome);
        for(int i = 0; i < listaEstudantes.size(); i++) {
            System.out.printf("\n[%d] ", i);
            listaEstudantes.get(i).print();
        }
    }
}
