package primeiro_java;

import java.util.Scanner;

public class PrimeiroJava {
    
    public static void main(String[] args) {
        Scanner read = new Scanner(System.in);
        //somar tres num
        System.out.print("Digite 3 numeros\n->");
        int a = read.nextInt();
        int b = read.nextInt();
        int c = read.nextInt();
        System.out.println("soma = " + somarTres(a,b,c));

        //ver se é par 
        System.out.println("Digite um numero: ");
        int x = read.nextInt();
        if(seEhPar(x)) {
            System.out.println("É par");
        }
        else {
            System.out.println("É impar");
        }

        /*dividir dois reais
        float y = read.nextFloat();
        float z = read.nextFloat();
        System.out.println(dividirDois(y,z));*/
        System.out.println(dividirDois(read.nextFloat(),read.nextFloat()));
    }
    
    public static float dividirDois(float a,float b) {
        if (b == 0) {
            return 0;
        }
        return a / b;
    }

    public static boolean seEhPar(int a) {
        //verifica se o numero "a" é par ou não
        return a % 2 == 0;
    }

    public static int somarDois(int a, int b) {
        // soma dois valores
        return a + b;
    }

    public static int somarTres(int a, int b, int c) {
        // tecla do "mais" quebrou aqui
        return somarDois(somarDois(a,b),c);
    }

}