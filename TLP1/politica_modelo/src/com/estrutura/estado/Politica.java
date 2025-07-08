package com.estrutura.estado;

import com.estrutura.estado.controller.PoliticaController;
import com.estrutura.estado.view.PoliticaView;

public class Politica {
    public static void main(String[] args) {
        PoliticaView view = new PoliticaView();
        
        try {
            PoliticaController controller = new PoliticaController(view);
            controller.iniciar();
        } catch (Exception e) {
            view.mostrarMensagem("Erro ao iniciar a aplicação: " + e.getMessage());
        }
    }
}