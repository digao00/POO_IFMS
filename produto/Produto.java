package produto;

public class Produto {

    private int codigo;

    public int getCodigo() {
        return codigo;
    }

    public void setCodigo(int codigo) {
        if (codigo < 1000) {
            System.out.println("\nERRO: Código inválido");
        }
        else {
            this.codigo = codigo;
        }
    }


    private String descricao;

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    private String validade;
    
    public String getValidade() {
        return validade;
    }

    public void setValidade(String validade) {
        this.validade = validade;
    }

    private float preco;

    public float getPreco() {
        return preco;
    }
    
    public void setPreco(float preco) {
        if (preco < 0) {
            System.out.println("\nERRO: Preço inválido");
        }
        else {
            this.preco = preco;
        }
    }

    //imprimir atributos
    public void print() {
        System.out.printf("\nCódigo: %d\nDescrição: %s\nValidade: %s\nPreço: R$%.2f\n",codigo,descricao,validade,preco);
    }

}