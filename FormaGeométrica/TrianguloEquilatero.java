public class TrianguloEquilatero extends FormaRegular {
    
    public TrianguloEquilatero() {}
    public TrianguloEquilatero(String nome, float lado) {
        super(nome, lado);
    }

    @Override
    public float area() {
        float altura = (float)Math.sqrt(Math.pow(getLado(), 2) - Math.pow(getLado()/2, 2));
        return (getLado()*altura)/2;
    }
    @Override
    public float perimetro() {
        return getLado()*3;
    }
}
