public class TrianguloRetangulo extends Retangulo {
    
    public TrianguloRetangulo() {}
    public TrianguloRetangulo(String nome, float base, float altura) {
        super(nome, base, altura);
    }

    @Override
    public float area() {
        return (getBase()*getAltura())/2;
    }
    @Override
    public float perimetro() {
        return (float)Math.sqrt(Math.pow(getAltura(), 2) + Math.pow(getBase(), 2)) + getAltura() + getBase();
    }
}
