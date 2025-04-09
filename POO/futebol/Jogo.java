public class Jogo {    
    
    private Time timeA;    
    private Time timeB;
    private int pontosA;
    private int pontosB;

    public Jogo(Time timeA, Time timeB) {
        pontosA = pontosB = 0;
    }
    public void print() {
        System.out.printf("\nJogo entre %s X %s\n", timeA.getNome(), timeB.getNome());
        if (vencedor() == null) {
            System.out.println("Jogo empatado");
        }
        else {
            System.out.printf("\nVencedor: %s", vencedor().getNome());
        }
    }

    public void fezPonto(Time time) {
        if (time == timeA) {
            pontosA++;
        }
        if (time == timeB) {
            pontosB++;
        } 
        else {
            System.out.println("time não existe");
        }
    }

    public void acabou() {
        if (pontosA > pontosB) {
            timeA.ganhar();
            timeB.perder();
        }
        else if (pontosA < pontosB) {
            timeA.perder();
            timeB.ganhar();
        }
        else {
            timeA.empatar();
            timeB.empatar();
        }
    }

    public Time vencedor() {
        if (pontosA > pontosB) {
            return timeA;
        }
        else if (pontosA < pontosB) {
            return timeB;
        }
        else {
            return null;
        }

    }
}