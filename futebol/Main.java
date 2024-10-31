public class Main {
    public static void main(String[] args) {
        Time a = new Time("corintinas");
        Time b = new Time("Palmelado");

        Jogo jogo = new Jogo(a,b);
        jogo.fezPonto(b);
        jogo.fezPonto(a);
        jogo.fezPonto(a);
        jogo.acabou();
        jogo.print();
    }
}
