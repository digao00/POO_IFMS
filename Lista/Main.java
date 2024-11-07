package Lista;

import java.util.ArrayList;

public class Main {
    public static void main(String[] args) {
        Estudante e1 = new Estudante(9308, "João", 9.2f);
        Estudante e2 = new Estudante(1378, "Ian", 8.3f);
        Estudante e3 = new Estudante(4356, "Thiago", 7.8f);
        ArrayList<Estudante> listaE = new ArrayList<>();
        listaE.add(e1);
        listaE.add(e2);
        listaE.add(e3);
        for (int i = 0; i < listaE.size(); i++) {
            listaE.get(i).print();
            System.out.println("");
        }
    }
}
