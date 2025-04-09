
/*
    Classe: é a base para instanciar qualquer objeto, ou seja,
    se eu quero instanciar um automóvel, eu tenho que utilizar a classe "Automóvel".
*/
public class Automóvel{ // << Criando a Classe
    
    // Construtores são utilizados para você poder instanciar objetos de uma classe
        public Automóvel(String modelo, String placa, int quilometragem) {
        this.modelo = modelo;
        this.placa = placa;
        this.quilometragem = quilometragem;
    }

    //  Atributos: são as características da classe, por exemplo,
    //  para a classe Automóvel, os atributos podem ser: placa, modelo, quilometragem, etc.
    
    // Criando os Atributos 
    private String modelo;
    private String placa;
    private int quilometragem;
    //^^^ Private/Public são chamados de modificadores de acesso, é oque dá ou tira o acesso de outras classes

    /*
        Métodos: são os comportamentos e ações de uma classe
    */
    public void print() { // Um método que imprime as informações da classe
        System.out.printf("\nModelo: %s\nPlaca: %s\nQuilometragem: %d", modelo, placa, quilometragem);
    }

    
    //  Métodos "Set" são criados para poder modificar atributos que são privados e para o dev conseguir criar qualquer tipo de restrição.
    //  Métodos "Get" são criados para conseguir pegar a informação de um atributo que é privado.
    public String getModelo() {
        return modelo;
    }

    public void setModelo(String modelo) {
        this.modelo = modelo;
    }


}
