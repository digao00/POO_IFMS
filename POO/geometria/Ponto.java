package geometria;

public class Ponto {
    private float x,y;
    public Ponto(float x, float y) {
        this.x = x;
        this.y = y;
    }
    public Ponto() {
        
    }

    public void setXY(float x, float y) {
        this.x = x;
        this.y = y;
    }

    public float getX() {
        return x;
    }

    public float getY() {
        return y;
    }

    public float distancia(Ponto p) {
        float c1 = Math.abs(p.x - this.x);
        float c2 = Math.abs(p.y - this.y);
        return (float)Math.sqrt((c1*c1)+(c2*c2));
    }

    public void print(String nome) {
        System.out.printf("\n%s = (%.2f,%.2f)",nome,x,y);
    }
}
