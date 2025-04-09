public abstract class FormaRegular extends FiguraGeometrica {
    
    private float lado;

    public FormaRegular() {}
    public FormaRegular(String nome, float lado) {
        super(nome);
        this.lado = lado;
    }

    public float getLado() {
        return lado;
    }
    public void setLado(float lado) {
        this.lado = lado;
    }
    @Override
    public void print() {
        super.print();
        System.out.printf("\nLado: %.2f", lado);
    }
}
