import java.util.ArrayList;

public class Principal {
    public static void main(String[] args) {
        ArrayList<FiguraGeometrica> lista = new ArrayList<>();

        lista.add(new Pentagono("pintogono", 10f));
        lista.add(new Circulo("cu", 2f));
        lista.add(new Quadrado("kadralho", 5f));
        lista.add(new Retangulo("reto", 5f, 3f));
        lista.add(new TrianguloEquilatero("tri equino", 5f));
        lista.add(new TrianguloRetangulo("tres retos", 5f, 3f));
        
        for (FiguraGeometrica figuraGeometrica : lista) {
            figuraGeometrica.print();
            System.out.println("");
        }
        
    }
}
