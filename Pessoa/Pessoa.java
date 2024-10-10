package Pessoa;

public class Pessoa {
    String nome;
    char sexo;
    float altura;
    float peso;

    public void print() {
        System.out.printf("\nNome: %s\nAltura: %.2f m\nPeso: %.2f kg\nSexo: %c\n",nome,altura,peso,sexo);
    }
}
