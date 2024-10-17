package produto;

public class Main {
    public static void main(String[] args) {
        Produto penis = new Produto();
        penis.setCodigo(1002);
        penis.setDescricao("penis");
        penis.setPreco(45f);
        penis.setValidade("11/09/2001");
        penis.print();
    }
    
}