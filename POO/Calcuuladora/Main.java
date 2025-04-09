package POO.Calcuuladora;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        while (true) {
            try {
                scanner.nextLine();
                System.out.printf("\nDigite o primeiro numero: ");
                double a = scanner.nextDouble();
                scanner.nextLine();
                System.out.printf("\nDigite o segundo numero: ");
                double b = scanner.nextDouble();
                System.out.println("1 - Adição");
                System.out.println("2 - Subtração");
                System.out.println("3 - Multiplicação");
                System.out.println("4 - Divisão");
                System.out.println("5 - Sair");
                int op = scanner.nextInt();
                switch (op) {
                    case value:
                        
                        break;
                
                    default:
                        break;
                }
            }    
        }
    }
}
