public class Retangulo extends FiguraGeometrica {
    
    private float base;
    private float altura;

    public Retangulo() {}
    public Retangulo(String nome, float base, float altura) {
        super(nome);
        this.base = base;
        this.altura = altura;
    }

    public float getBase() {
        return base;
    }
    public void setBase(float base) {
        this.base = base;
    }
    
    public float getAltura() {
        return altura;
    }
    public void setAltura(float altura) {
        this.altura = altura;
    }

    @Override
    public float area() {
        return base*altura;
    }

    @Override
    public float perimetro() {
        return altura + altura + base + base;
    }

    @Override
    public void print() {
        super.print();
        System.out.printf("\nBase: %.2f\nAltura: %.2f", base, altura);
    }
}
