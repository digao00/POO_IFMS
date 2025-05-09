package com.tlp1.steam.view;

import java.io.IOException;
import java.util.InputMismatchException;
import java.util.Scanner;

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
        System.out.println("\nEscolha uma opção:");
        System.out.println("1 - Mostrar Loja");
        System.out.println("2 - Comprar jogo");
        System.out.println("3 - Sua biblioteca");
        System.out.println("4 - Personalizar");
        System.out.println("0 - Sair");
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
            pauseComMsg("Digite uma opção válida.");
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
