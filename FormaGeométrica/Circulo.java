public class Circulo extends FiguraGeometrica {
    
    private float raio;

    public Circulo() {}
    public Circulo(String nome, float raio) {
        super(nome);
        this.raio = raio;
    }

    public float getRaio() {
        return raio;
    }
    public void setRaio(float raio) {
        this.raio = raio;
    }

    @Override
    public float area() {
        return (float)Math.PI * (raio*raio);
    }
    @Override
    public float perimetro() {
        return 2*(float)Math.PI * raio;
    }
    @Override
    public void print() {
        System.out.println("Círculo:");
        super.print();
        System.out.printf("\nRaio: %.2f", raio);
    }
}
