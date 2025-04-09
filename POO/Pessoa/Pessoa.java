package Pessoa;
// alerta: este código contém comentarios completamente desnecessarios

public class Pessoa {
    String nome;
    char sexo;
    float altura;
    float peso;

    // printa os atributos
    public void print() {
        System.out.printf("\nNome: %s\nAltura: %.2f m\nPeso: %.2f kg\nSexo: %c\nIMS: %.2f",nome,altura,peso,sexo, calcIMC());
    }

    // calcula o imc (indice de massa corporal)
    public float calcIMC() {
        return peso/(altura*altura);
    }
}
