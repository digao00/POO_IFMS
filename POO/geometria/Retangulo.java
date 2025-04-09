package geometria;

public class Retangulo {
    private Ponto p1;
    private Ponto p2;
    public Retangulo(Ponto p1, Ponto p2) {
        this.p1 = p1;
        this.p2 = p2;
    }
    float altura(){
        return (float)Math.abs(p1.getY() - p2.getY());

    }
    float base() {
        return (float)Math.abs(p1.getX() - p2.getX());
    }
    public float area() {
        return base()*altura();
    }

    public float perimetro() {
        return base()*2+altura()*2;
    }
}
