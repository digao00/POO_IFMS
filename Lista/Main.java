package Lista;

public class Main {
    public static void main(String[] args) {
        Estudante e1 = new Estudante(9308, "João", 9.2f);
        Estudante e2 = new Estudante(1378, "Ian", 8.3f);
        Estudante e3 = new Estudante(4356, "Thiago", 7.8f);

        Disciplina poo = new Disciplina("POO");
        poo.addEstudante(e1);
        poo.addEstudante(e3);
        poo.addEstudante(e2);
        poo.print();
    }
}
