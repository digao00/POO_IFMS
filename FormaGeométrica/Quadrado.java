public class Quadrado extends FormaRegular {
    
    public Quadrado() {}
    public Quadrado(String nome, float lado) {
        super(nome, lado);
    }

    @Override
    public float area() {
        return getLado()*getLado();
    }
    @Override
    public float perimetro() {
        return getLado()*4;
    }
}
