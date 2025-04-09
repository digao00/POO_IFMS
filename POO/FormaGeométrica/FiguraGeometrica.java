public abstract class FiguraGeometrica {

    private String nome;

    public FiguraGeometrica() {}
    public FiguraGeometrica(String nome) {
        this.nome = nome;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public abstract float area();
    public abstract float perimetro();
    
    public void print() {
        System.out.printf("\nFigura: %s\nNome: %s\nÁrea: %.2f\nPerímetro: %.2f", getClass().getName().replaceAll("([a-z])([A-Z])", "$1 $2"), nome, area(), perimetro());
    }
}