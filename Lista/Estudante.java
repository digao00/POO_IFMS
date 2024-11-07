package Lista;

public class Estudante {
    private int ra;
    private String nome;
    private float cr;

    public Estudante(int ra, String nome, float cr) {
        this.ra = ra;
        this.nome = nome;
        this.cr = cr;
    }

    public int getRa() {
        return ra;
    }
    public void setRa(int ra) {
        this.ra = ra;
    }

    public String getNome() {
        return nome;
    }
    public void setNome(String nome) {
        this.nome = nome;
    }
    
    public float getCr() {
        return cr;
    }
    public void setCr(float cr) {
        this.cr = cr;
    }

    public void print() {
        System.out.printf("\nNome: %s\nRA: %d\nCR: %.2f", nome, ra, cr);
    }
}
