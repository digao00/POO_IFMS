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
    public void rmEstudanteRA(int ra) {
        int a = -1;
        for(int i = 0; i < listaEstudantes.size(); i++) {
            if (ra == listaEstudantes.get(i).getRa()) {
                a = i;
            }
        }
        listaEstudantes.remove(a);
    }
    public float mediaCR() {
        float crTotal = 0;
        for(int i = 0; i < listaEstudantes.size(); i++) {
            crTotal += listaEstudantes.get(i).getCr();
        }
        return crTotal / listaEstudantes.size();
    }
    public Estudante maiorCr() {
        Estudante maiorCr = listaEstudantes.get(0);
        for(int i = 0; i < listaEstudantes.size(); i++) {
            if (listaEstudantes.get(i).getCr() > maiorCr.getCr()) {
            maiorCr = listaEstudantes.get(i);
            }
        
        }
        return maiorCr;
    }

    public void print() {
        System.out.printf("Nome: %s\nEstudantes: \n", nome);
        for(int i = 0; i < listaEstudantes.size(); i++) {
            System.out.printf("\n[%d] ", i+1);
            listaEstudantes.get(i).print();
        }
    }
}
