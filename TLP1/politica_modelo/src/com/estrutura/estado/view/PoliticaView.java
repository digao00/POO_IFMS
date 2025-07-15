package com.estrutura.estado.view;

import java.io.IOException;
import java.util.Scanner;

public class PoliticaView {
    private Scanner scanner;

    public PoliticaView() {
        scanner = new Scanner(System.in);
    }

    // Métodos de Menu Principal
    public void mostrarMenuPrincipal() throws IOException, InterruptedException {
        limparTela();
        System.out.println("\n==== SISTEMA DE INFORMAÇÕES POLÍTICAS ====");
        System.out.println("1. Cadastros Básicos");
        System.out.println("2. Representantes Políticos");
        System.out.println("3. Relatórios e Análises");
        System.out.println("4. Sair");
    }

    // Métodos para Cadastros Básicos
    public void mostrarMenuCadastros() throws IOException, InterruptedException {
        limparTela();
        System.out.println("\n--- CADASTROS BÁSICOS ---");
        System.out.println("1. Poder do Estado");
        System.out.println("2. Nível de Governo");
        System.out.println("3. Cargo Político");
        System.out.println("4. Partido"); // meu
        System.out.println("5. Voltar");
    }

    public void mostrarSubmenu(String titulo) throws IOException, InterruptedException {
        limparTela();
        System.out.println("\n--- " + titulo + " ---"); //partidos
        System.out.println("1. Cadastrar");
        System.out.println("2. Listar");
        System.out.println("3. Alterar");
        System.out.println("4. Excluir");
        System.out.println("5. Voltar");
    }

    // Métodos para Representantes Políticos
    public void mostrarMenuRepresentantes() {
        System.out.println("\n--- REPRESENTANTES POLÍTICOS ---");
        System.out.println("1. Cadastrar Representante");
        System.out.println("2. Listar Todos Representantes");
        System.out.println("3. Listar Prefeito e Vice");
        System.out.println("4. Listar Vereadores");
        System.out.println("5. Listar Deputados Estaduais");
        System.out.println("6. Listar Deputados Federais");
        System.out.println("7. Listar Senadores");
        System.out.println("8. Voltar");
    }

    // Métodos para Relatórios e Análises
    public void mostrarMenuRelatorios() throws IOException, InterruptedException {
        limparTela();
        System.out.println("\n--- RELATÓRIOS E ANÁLISES ---");
        System.out.println("1. Distribuição Partidária");
        System.out.println("2. Representatividade por Orientação"); // meu
        System.out.println("3. Responsabilidades por Cargo");
        System.out.println("4. Listar Poderes do Estado");
        System.out.println("5. Voltar");
    }

    public void orientacoes() {
        System.out.println("1 - Extrema-esquerda");
        System.out.println("2 - Esquerda");
        System.out.println("3 - Centro-esquerda");
        System.out.println("4 - Centro");
        System.out.println("5 - Centro-direita");
        System.out.println("6 - Direita");
        System.out.println("7 - Extrema-direita");
    }

    // Métodos de Entrada de Dados
    public int lerOpcao() {
        return lerInteiro("Escolha uma opção:");
    }

    public int lerInteiro(String prompt) {
        while (true) {
            System.out.print(prompt + " ");
            try {
                int value = scanner.nextInt();
                scanner.nextLine(); // Consume the rest of the line
                return value;
            } catch (java.util.InputMismatchException e) {
                scanner.nextLine(); // Consume the invalid input
                return -1;
            }
        }
    }

    public String lerTexto(String prompt) {
        System.out.printf(prompt + " ");
        return scanner.nextLine();
    }

    public void mostrarMensagem(String mensagem) {
        System.out.println(mensagem);
    }

    public void printf(String format, Object ... args) {
        System.out.printf(format, args);
    }

    public void pauseComMsg(String msg) throws IOException, InterruptedException {
		limparTela();
		System.out.println(msg);
		System.out.println("Pressione Enter para continuar...");
		scanner.nextLine();
		limparTela();
	}

	public void limparTela() throws IOException, InterruptedException {
		new ProcessBuilder("cmd", "/c", "cls").inheritIO().start().waitFor();
	}

}