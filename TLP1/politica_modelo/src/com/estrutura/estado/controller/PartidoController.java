package com.estrutura.estado.controller;

import java.io.IOException;
import java.sql.SQLException;

import com.estrutura.estado.model.AlreadyCreatedPartidoExeption;
import com.estrutura.estado.model.Partido;
import com.estrutura.estado.model.dao.PartidoDAO;
import com.estrutura.estado.view.PoliticaView;

public class PartidoController {
    private PoliticaView view;
    private PartidoDAO dao = new PartidoDAO();

    public PartidoController(PoliticaView view) {
        this.view = view;
    }

    public void cadastrarPartido() throws IOException, InterruptedException, SQLException {
        view.limparTela();
        String nome = view.lerTexto("\n Digite o nome do partido: ");
        String sigla = view.lerTexto("\nDigite a sigla do partido: ");
        String orientacao = null;
        while (orientacao == null) {
            view.mostrarMensagem("\nSelecione a orientação do partido:");
            view.orientacoes();
            int op = view.lerOpcao();
            switch (op) {
                case 1:
                    orientacao = "EXTREMA-ESQUERDA";
                    break;
                case 2:
                    orientacao = "ESQUERDA";
                    break;
                case 3:
                    orientacao = "CENTRO-ESQUERDA";
                    break;
                case 4:
                    orientacao = "CENTRO";
                    break;
                case 5:
                    orientacao = "CENTRO-DIREITA";
                    break;
                case 6:
                    orientacao = "DIREITA";
                    break;
                case 7:
                    orientacao = "EXTREMA-DIREITA";
                    break;
                default:
                    view.mostrarMensagem("Selecione um partido válido");
                    break;
            }
        }
        Partido p = new Partido(sigla, nome, orientacao);
        try {
            dao.cadastrarPartido(p);
        } catch (AlreadyCreatedPartidoExeption e) {
            view.pauseComMsg(e.getMessage());
        }
    }
}
