package Aviação;

public class Main {
    public static void main(String[] args) {
        Aeroporto a1 = new Aeroporto("Aeroporto Internacional de São Paulo", "São Paulo");
        Aeroporto a2 = new Aeroporto("Boston Logan International Airport", "Boston");
        Avião av1 = new Avião("Boeing 737", 89);
        Vôo v1 = new Vôo(7485, "16/01/2025", a1, a2, av1);
        v1.adicionarPassagem(new PassagemEconômica(1200, "João Rodrigo", v1, false));
        v1.adicionarPassagem(new PassagemEconômica(1200, "Eduarda", v1, false));
        v1.adicionarPassagem(new PassagemExecutiva(3000, "Jaqueline", v1, true));
        v1.print();
    }
}
