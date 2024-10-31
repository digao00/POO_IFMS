package geometria;

public class Main {
    public static void main(String[] args) {
        Ponto a = new Ponto(3f,3f);
        Ponto b = new Ponto(2f,2f);
        Retangulo r = new Retangulo(a, b);
        float d = a.distancia(b);
        a.print("a");
        b.print("b");
        System.out.println("\nDistancia entre A e B: " + d);
        System.out.println("Area do retangulo: " + r.area());
        System.out.println("Perimetro do retangulo: " + r.perimetro());
    }
}
