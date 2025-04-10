package POO.Calcuuladora;

import java.util.InputMismatchException;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        while (true) {
            try {
                System.out.printf("\nDigite o primeiro numero: ");
                double a = scanner.nextDouble();
                scanner.nextLine();
                System.out.printf("\nDigite o segundo numero: ");
                double b = scanner.nextDouble();
                scanner.nextLine();
                System.out.println("1 - Adição");
                System.out.println("2 - Subtração");
                System.out.println("3 - Multiplicação");
                System.out.println("4 - Divisão");
                System.out.println("5 - Sair");
                int opValido = 0;
                while (opValido == 0) {
                    System.out.printf("\n-> ");
                    int op = scanner.nextInt();
                    scanner.nextLine();
                    double resultado = 0;
                    switch (op) {
                        case 1:
                            resultado = a + b;
                            opValido = 1;
                            break;
                        case 2:
                            resultado = a - b;
                            opValido = 1;
                            break;
                        case 3:
                            resultado = a * b;
                            opValido = 1;
                            break;
                        case 4:
                            resultado = a / b;
                            opValido = 1;
                            break;
                        case 5:
                            return;
                        default:
                            continue;
                    }
                    System.out.printf("\nResultado = %.2f", resultado);
                }
            }
            catch (InputMismatchException e) {
                System.out.println(e.getMessage());
                continue;
            }
        }
    }
}
