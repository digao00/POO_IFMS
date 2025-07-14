package com.estrutura.estado;

import java.io.IOException;
import java.sql.SQLException;

import com.estrutura.estado.controller.PoliticaController;
import com.estrutura.estado.view.PoliticaView;

public class Politica {
    public static void main(String[] args) throws IOException, InterruptedException {
        PoliticaView view = new PoliticaView();
        
        try {
            PoliticaController controller = new PoliticaController(view);
            controller.iniciar();
        } catch (SQLException e) {
            view.mostrarMensagem("Erro ao iniciar a aplicação: " + e.getMessage());
        }
    }
}