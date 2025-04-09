package Aviação;

import java.util.ArrayList;

public class Aeroporto {
    private String nome;
    private String local;
    private ArrayList<Vôo> lista_voos = new ArrayList<>();

    public Aeroporto(String nome, String local) {
        this.nome = nome;
        this.local = local;
    }

    public String getNome() {
        return nome;
    }
    public void setNome(String nome) {
        this.nome = nome;
    }

    
    public String getLocal() {
        return local;
    }
    public void setLocal(String local) {
        this.local = local;
    }

    public void adicionarVoo(Vôo voo) {
        lista_voos.add(voo);
    }

    public Vôo getVoo(int i) {
        return lista_voos.get(i);
    }

    public int qtdeVoos() {
        return lista_voos.size();
    }
}
