import java.util.ArrayList;

public class Main {
    public static void main(String[] args) {
        ArrayList<FiguraGeometrica> lista = new ArrayList<>();

        lista.add(new Pentagono("Pentagono", 10f));
        lista.add(new Circulo("Circulo", 2f));
        lista.add(new Quadrado("Quadrado", 5f));
        lista.add(new Retangulo("Retangulo", 5f, 3f));
        lista.add(new TrianguloEquilatero("Triangulo Equilatero", 5f));
        lista.add(new TrianguloRetangulo("Triangulo Retangulo", 5f, 3f));
        
        for (FiguraGeometrica figuraGeometrica : lista) {
            figuraGeometrica.print();
            System.out.println("");
        }
    }
}
