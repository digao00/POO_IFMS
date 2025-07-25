package com.estrutura.estado.controller;

import java.io.IOException;
import java.sql.SQLException;

import com.estrutura.estado.model.AlreadyCreatedPartidoExeption;
import com.estrutura.estado.model.Partido;
import com.estrutura.estado.model.PartidoBeingUsedException;
import com.estrutura.estado.model.PartidoNotFoundExeption;
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
        String nome = view.lerTexto("\nDigite o nome do partido: ");
        String sigla = view.lerTexto("\nDigite a sigla do partido: ");
        String orientacao = null;
        while (orientacao == null) {
            view.limparTela();
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
                    view.pauseComMsg("Selecione uma orientação válido");
                    break;
            }
        }
        Partido p = new Partido(sigla, nome, orientacao);
        if (p.getSigla().isEmpty() || p.getNome_completo().isEmpty()) {
            view.pauseComMsg("Sigla ou nome do partido invalidos");
            return;
        }
        try {
            dao.cadastrarPartido(p);
            view.pauseComMsg("Partido Cadastrado");
        } catch (AlreadyCreatedPartidoExeption e) {
            view.pauseComMsg(e.getMessage());
        }
    }

    public void listarPartidos() throws IOException, InterruptedException, SQLException {
        view.limparTela();
        view.mostrarMensagem("PARTIDOS");
        for (Partido partido : dao.listarPartidos()) {
            view.printf("\nID = %d\n", partido.getId());
            view.printf("Nome: %s\nSigla: %s\nOrientação: %s\n", partido.getNome_completo(), partido.getSigla(), partido.getOrientacao());
        }
        view.mostrarMensagem("\nPressione Enter para continuar...");
        view.lerTexto("");
    }

    public void alterarPartido() throws IOException, InterruptedException, SQLException {
        view.limparTela();
        int id = view.lerInteiro("\nDigite o id do partido que queira alternar: ");
        try {
            Partido partido = dao.acharPartido(id);
            view.limparTela();

            view.printf("\nNome atual: %s", partido.getNome_completo());
            String nome = view.lerTexto("\nDigite o novo nome do partido: ");
            partido.setNome_completo(nome);

            view.printf("\nSigla atual: %s", partido.getSigla());
            String sigla = view.lerTexto("\nDigite a sigla do partido: ");
            partido.setSigla(sigla);

            String orientacao = null;
            while (orientacao == null) {
                view.limparTela();
                view.printf("Orientação atual: %s", partido.getOrientacao());
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
                        view.limparTela();
                        view.mostrarMensagem("Selecione uma orientação válido");
                        break;
                }   
            }
            partido.setOrientacao(orientacao);
            dao.alterarPartido(partido);
            view.pauseComMsg("Partido alterado.");
        } catch (PartidoNotFoundExeption e) {
            view.pauseComMsg(e.getMessage());
            //return;
        }
    }

    public void excluirPartido() throws IOException, InterruptedException, SQLException {
        view.limparTela();
        int id = view.lerInteiro("\n Digite o id do partido que queira deletar: ");
        try {
            dao.excluirPartido(dao.acharPartido(id));
            view.pauseComMsg("Partido deletado.");
        } catch (PartidoNotFoundExeption e) {
            view.pauseComMsg(e.getMessage());
        } catch (PartidoBeingUsedException e) {
            view.pauseComMsg(e.getMessage());
        }
    }

    public void mostrarRepresentatividadePorOientacao() throws IOException, InterruptedException, SQLException {
        view.limparTela();
        int numRepresentantes = dao.numRepresentantes();
        view.printf("Número de Representantes: %d\n", numRepresentantes);

        //a linha 161 vai printar cada elemento do Map, igual um foreach. A classe Map tem um próprio método pra isso, como da pra ver
        dao.representantesPorOrientacao().forEach((orientacao, qtde) -> view.printf("%-17s\t%.1f%%\n", orientacao + ":", (float)qtde/numRepresentantes*100));

        view.lerTexto("\nPressione Enter para continuar.");
    }
}
