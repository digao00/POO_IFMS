public class Carro extends Automóvel{ 
    // "extends" é usado para dizer que a classe "Carro" é filha da classe "Automóvel", chamamos isso de Herança
    // a classe filha recebe todos os atributos e métodos da classe mãe
    public Carro(String modelo, String placa, int quilometragem) {
        super(modelo, placa, quilometragem);
    }
}
