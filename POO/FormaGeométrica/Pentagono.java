public class Pentagono extends FormaRegular {

    public Pentagono() {}
    public Pentagono(String nome, float lado) {
        super(nome, lado);
    }

    @Override
    public float area() {
        float a = (getLado()/2)/(float)Math.tan(36 * Math.PI/180);
        return (5*getLado()*a)/2;
    }
    @Override
    public float perimetro() {
        return getLado()*5;
    }
}
