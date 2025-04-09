public class Time {
    //costrutores
    public Time() {zerarValores();}
    public Time(String nome) {
        this.nome = nome;
        zerarValores();
    }

    //gets e setters
    private String nome;
    public String getNome() {
        return nome;
    }
    public void setNome(String nome) {
        this.nome = nome;
    }

    private int numVitorias;
    public int getNumVitorias() {
        return numVitorias;
    }
    public void setNumVitorias(int numVitorias) {
        this.numVitorias = numVitorias;
    }

    private int numDerrotas;
    public int getNumDerrotas() {
        return numDerrotas;
    }
    public void setNumDerrotas(int numDerrotas) {
        this.numDerrotas = numDerrotas;
    }

    private int numEmpates;
    public int getNumEmpates() {
        return numEmpates;
    }
    public void setNumEmpates(int numEmpates) {
        this.numEmpates = numEmpates;
    }

    private int pontos;
    public int getPontos() {
        return pontos;
    }
    public void setPontos(int pontos) {
        this.pontos = pontos;
    }

    //metodos
    private void zerarValores() {
        this.numVitorias = 0;
        this.numDerrotas = 0;
        this.numEmpates = 0;
        this.pontos = 0;
    }
    public int ganhar() {
        pontos+=3;
        numVitorias++;
        return pontos;
    }
    public int empatar() {
        pontos++;
        numEmpates++;
        return pontos;
    }
    public int perder() {
        numDerrotas++;
        return pontos;
    }
    public void imprimir() {
        System.out.printf("\nTime: %s\nVitórias: %d\nDerrotas: %d\nEmpates: %d\nPartidas: %d\nPontuação: %d",nome, numVitorias,numDerrotas,numEmpates,numDerrotas + numVitorias + numEmpates, pontos);
    }
}
