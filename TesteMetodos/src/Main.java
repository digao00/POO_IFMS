import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        Baskara function = new Baskara();
        @SuppressWarnings("resource")
        Scanner read = new Scanner(System.in);
        function.setCoef(read.nextDouble(), read.nextDouble(), read.nextDouble());
        System.out.println(function.delta());
        System.out.println(Math.sqrt(function.delta()));
        if (function.delta() < 0) {
            System.out.println("Essa função não tem raiz");
        }
        else if (function.delta() == 0) {
            System.out.println("x1 = x2 = " + function.baskaraX1());
        }
        else {
            System.out.println("x1 = " + function.baskaraX1() + "\nx2 = " + function.baskaraX2());
        }
    }

}