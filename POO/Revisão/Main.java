import java.util.ArrayList;

public class Main {
    public static void main(String[] args) {
        // Objetos: são oque criamos a partir das classes para representar um objeto no mundo real.
        // Nós instanciamos objetos usando "new".
        Carro c1 = new Carro("BMW M3", "HR56J7", 0);

        // ArrayList: uma lista que podemos armazenar vários objetos.
        ArrayList<Carro> lista_carros = new ArrayList<>();
        lista_carros.add(c1);
    }
}
