package Pessoa;

public class Main {

    public static void main(String[] args) {
        Pessoa p = new Pessoa();
        Pessoa r = new Pessoa();
        r.nome = "Eduarda";
        r.altura = 1.60f;
        r.peso = 60f;
        r.sexo = 'F';
        r.print();

        p.nome = "João";
        p.altura = 1.69f;
        p.peso = 55f;
        p.sexo = 'M';
        p.print();
    }
}
