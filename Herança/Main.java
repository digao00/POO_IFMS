package Herança;

public class Main {
    public static void main(String[] args) {
        Secretário s1 = new Secretário("jao", "958.347.234-01", "Vendas");
        Engenheiro e1 = new Engenheiro("cácio", "654.879.564-23", 4356);
        Diretor d1 = new Diretor("oswaldo", "776.486.123-11", "Regional");
        System.out.println(e1.toString());
        System.out.println("\n" + s1.toString());
        System.out.println("\n" + d1.toString());
    }
}
