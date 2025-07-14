package com.estrutura.estado.controller;

import java.io.IOException;
import java.sql.SQLException;

import com.estrutura.estado.view.PoliticaView;

public class PoliticaController {
    private PoliticaView view;
    private PartidoController partido = new PartidoController(view);
    
    public PoliticaController(PoliticaView view) {
        this.view = view;
    }
    
    public void iniciar() throws IOException, InterruptedException, SQLException{
        boolean sair = false;
        while (!sair) {
            view.mostrarMenuPrincipal();
            int opcao = view.lerOpcao();
            
            switch (opcao) {
                case 1:
                    menuCadastros();
                    break;
                case 2:
                    menuRepresentantes();
                    break;
                case 3:
                    menuRelatorios();
                    break;
                case 4:
                    sair = true;
                    view.mostrarMensagem("Saindo do sistema...");
                    break;
                default:
                    view.mostrarMensagem("Opção inválida!");
            }
        }
    }
    
    private void menuCadastros() throws IOException, InterruptedException, SQLException{
        boolean voltar = false;
        while (!voltar) {
            view.mostrarMenuCadastros();
            int opcao = view.lerOpcao();
            
            switch (opcao) {
                case 1:
                    submenuPoderEstado();
                    break;
                case 2:
                    submenuNivelGoverno();
                    break;
                case 3:
                    submenuCargoPolitico();
                    break;
                case 4:
                    submenuPartido();
                    break;
                case 5:
                    voltar = true;
                    break;
                default:
                    view.mostrarMensagem("Opção inválida!");
            }
        }
    }

    private void submenuPoderEstado() throws IOException, InterruptedException {
        boolean voltar = false;
        while (!voltar) {
            view.mostrarSubmenu("PODER DO ESTADO");
            int opcao = view.lerOpcao();
            
            switch (opcao) {
                case 1:
                    //cadastrarPoderEstado();
                    break;
                case 2:
                    //listarPoderesEstado();
                    break;
                case 3:
                    //alterarPoderEstado();
                    break;
                case 4:
                    //excluirPoderEstado();
                    break;
                case 5:
                    voltar = true;
                    break;
                default:
                    view.mostrarMensagem("Opção inválida!");
            }
        }
    }

    private void submenuNivelGoverno() throws IOException, InterruptedException {
        boolean voltar = false;
        while (!voltar) {
            view.mostrarSubmenu("NÍVEL DE GOVERNO");
            int opcao = view.lerOpcao();
            
            switch (opcao) {
                case 1:
                    //cadastrarNivelGoverno();
                    break;
                case 2:
                    //listarNiveisGoverno();
                    break;
                case 3:
                    //alterarNivelGoverno();
                    break;
                case 4:
                    //excluirNivelGoverno();
                    break;
                case 5:
                    voltar = true;
                    break;
                default:
                    view.mostrarMensagem("Opção inválida!");
            }
        }
    }

    private void submenuCargoPolitico() throws IOException, InterruptedException {
        boolean voltar = false;
        while (!voltar) {
            view.mostrarSubmenu("CARGO POLÍTICO");
            int opcao = view.lerOpcao();
            
            switch (opcao) {
                case 1:
                    //cadastrarCargoPolitico();
                    break;
                case 2:
                    //listarCargosPoliticos();
                    break;
                case 3:
                    //alterarCargoPolitico();
                    break;
                case 4:
                    //excluirCargoPolitico();
                    break;
                case 5:
                    voltar = true;
                    break;
                default:
                    view.mostrarMensagem("Opção inválida!");
            }
        }
    }

    private void submenuPartido() throws IOException, InterruptedException, SQLException { // meu
        boolean voltar = false;
        while (!voltar) {
            view.mostrarSubmenu("PARTIDO POLÍTICO");
            int opcao = view.lerOpcao();
            
            switch (opcao) {
                case 1:
                    partido.cadastrarPartido();
                    break;
                case 2:
                    //partido.listarPartidos();
                    break;
                case 3:
                    //partido.alterarPartido();
                    break;
                case 4:
                    //partido.excluirPartido();
                    break;
                case 5:
                    voltar = true;
                    break;
                default:
                    view.mostrarMensagem("Opção inválida!");
            }
        }
    }

    private void menuRepresentantes() {
        boolean voltar = false;
        while (!voltar) {
            view.mostrarMenuRepresentantes();
            int opcao = view.lerOpcao();
            
            switch (opcao) {
                case 1:
                    //cadastrarRepresentante();
                    break;
                case 2:
                    //listarRepresentantes();
                    break;
                case 3:
                    //listarPrefeitoEVice();
                    break;
                case 4:
                    //listarVereadores();
                    break;
                case 5:
                    //listarDeputadosEstaduais();
                    break;
                case 6:
                    //listarDeputadosFederais();
                    break;
                case 7:
                    //listarSenadores();
                    break;
                case 8:
                    voltar = true;
                    break;
                default:
                    view.mostrarMensagem("Opção inválida!");
            }
        }
    }

    private void menuRelatorios() throws IOException, InterruptedException {
        boolean voltar = false;
        while (!voltar) {
            view.mostrarMenuRelatorios();
            int opcao = view.lerOpcao();
            
            switch (opcao) {
                case 1:
                    //mostrarDistribuicaoPartidaria();
                    break;
                case 2:
                    //mostrarRepresentatividadeOrientacao(); -> meu
                    break;
                case 3:
                    //listarResponsabilidadesCargos();
                    break;
                case 4:
                    //mostrarHierarquiaPoder();
                    break;
                case 5:
                    //mostrarComparativoMandatos();
                    break;
                case 6:
                    //listarPoderesEstado();
                    break;
                case 7:
                    voltar = true;
                    break;
                default:
                    view.mostrarMensagem("Opção inválida!");
            }
        }
    }
    
}