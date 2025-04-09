package geometria;

public class Circulo {
    private Ponto centro;
    private float raio;

    public Circulo(Ponto centro, float raio) {
        this.centro = centro;
        this.raio = raio;
    }

    public float area() {
        return (float)Math.PI*(raio*raio);
    }
    public boolean intercecta(Circulo circulo) {
        return centro.distancia(this.centro) < raio + this.raio;
    }
    public void print() {
        System.out.printf("\nRaio: %.2f\nCentro: (%.2f,%.2f)", raio, centro.getX(), centro.getY());
    }

    public Ponto getCentro() {
        return centro;
    }
    public void setCentro(Ponto centro) {
        this.centro = centro;
    }
    
    public float getRaio() {
        return raio;
    }
    public void setRaio(float raio) {
        this.raio = raio;
    }
}
