package com.tlp1.steam.view;

import java.io.IOException;
import java.util.InputMismatchException;
import java.util.Scanner;

import com.tlp1.steam.model.Jogo;

public class SteamView {
    private Scanner scanner;
    
    public SteamView() {
        scanner = new Scanner(System.in);
    }

    public void menuLogin() throws IOException, InterruptedException {
        limparTela();
        System.out.println("\n###### Steam ######\n");
        System.out.println("1 - Criar conta");
        System.out.println("2 - Login");
        System.out.printf("\n-> ");
    }

    public void menuIniciar() throws IOException, InterruptedException {
        limparTela();
        System.out.println("\n\t###### Steam ######");
        System.out.println("\n\tEscolha uma opção:\n");
        System.out.println("|\t1 - Mostrar Loja\t|");
        System.out.println("|\t2 - Comprar jogo\t|");
        System.out.println("|\t3 - Sua biblioteca\t|");
        System.out.println("|\t4 - Personalizar\t|");
        System.out.println("|\t0 - Sair\t\t|");
        System.out.printf("\n-> ");
    }

    public void menuPersonalizar() throws IOException, InterruptedException {
        limparTela();
        System.out.println("0 - Voltar");
        System.out.println("1 - Mudar senha");
        System.out.println("2 - Mudar nome de usuário");
        System.out.println("3 - Deletar conta");
        System.out.printf("\n-> ");
    }

    public void menuJogo(Jogo jogo) throws IOException, InterruptedException {
        limparTela();
        System.out.println(jogo.getNome());
        System.out.println("0 - Voltar");
        System.out.println("1 - Reembolsar");
        System.out.printf("\n-> ");
    }

    public void msg(String msg) {
        System.out.printf(msg);
    }

    public void msgf(String msg, Object ... args) {
        System.out.printf(msg, args);
    }

    public int lerInt() throws IOException, InterruptedException {
        try {
            return scanner.nextInt();
        } catch (InputMismatchException e) {
            //pauseComMsg("Digite uma opção válida.");
            return -1;
        }
    }

    public String lerString() throws IOException, InterruptedException {
        return scanner.nextLine().trim();
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
