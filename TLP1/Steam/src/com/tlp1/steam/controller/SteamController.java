package com.tlp1.steam.controller;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.tlp1.steam.model.AlreadyPurchasedGameExeption;
import com.tlp1.steam.model.Jogador;
import com.tlp1.steam.model.JogadorDAO;
import com.tlp1.steam.model.Jogador_JogoDAO;
import com.tlp1.steam.model.Jogo;
import com.tlp1.steam.model.JogoDAO;
import com.tlp1.steam.view.SteamView;

public class SteamController {
    private SteamView   view;
    private JogadorDAO jogadorDAO;
    private JogoDAO jogoDAO;
    private Jogador_JogoDAO jogador_jogoDAO;
    private LoginController login;

    public SteamController(SteamView view, JogadorDAO jogadorDAO, JogoDAO jogoDAO, Jogador_JogoDAO jogador_jogoDAO) {
        this.view = view;
        this.jogadorDAO = jogadorDAO;
        this.jogoDAO = jogoDAO;
        this.jogador_jogoDAO = jogador_jogoDAO;
        this.login = new LoginController(view, jogadorDAO);
    }

    public void inicio() throws IOException, InterruptedException, SQLException {
        try {
            Jogador jogador = login.inicio();
            if (jogador != null) {
                boolean io = true;
                while (io) {
                    view.menuIniciar();
                    int op = view.lerInt();
                    view.lerString();
                    switch (op) {
                        case 1:
                            mostralLoja();
                            break;
                        case 2:
                            try {
                                comprarJogo(jogador);
                            }
                            catch (AlreadyPurchasedGameExeption e) {
                                view.pauseComMsg(e.getMessage());
                            }
                            break;
                        case 3:
                            mostrarBiblioteca(jogador);
                            break;
                        case 4:
                            boolean mudou = false;
                            while (!mudou) {
                                view.menuPersonalizar();
                                int op2 = view.lerInt();
                                view.lerString();
                                switch (op2) {
                                    case 1:
                                        if (mudarSenha(jogador)) {
                                            view.pauseComMsg("Senha alterada.");
                                            mudou = true;
                                        }
                                        break;
                                    case 2:
                                        if (mudarNome(jogador)) {
                                            view.pauseComMsg("Nome de usuário alterado");
                                            mudou = true;
                                        }
                                        break;
                                    case 3:
                                        if (deletarConta(jogador)) {
                                            view.pauseComMsg("Conta deletada.");
                                            io = false;
                                            mudou = true;
                                        }
                                        break;
                                    case 0:
                                        mudou = true;
                                        break;
                                    default:
                                        view.pauseComMsg("Digite uma opção válida.");
                                        break;
                                }
                            }
                            break;
                        case 0:
                            view.limparTela();
                            view.msg("Saindo...");
                            io = false;
                            break;
                        default:
                            view.pauseComMsg("Digite uma opção válida.");
                            break;
                    }
                }
            }
        } finally {}
    }

    public void mostralLoja() throws SQLException, IOException, InterruptedException {
        view.limparTela();
        int i = 1;
        for (Jogo jogo : jogoDAO.mostrarLoja()) {
            view.msgf("\n%2d - %s", i, jogo.getNome());
            i++;
        }
        view.msg("\nPressione enter para continuar.");
        view.lerString();
    }

    public void comprarJogo(Jogador jogador) throws SQLException, IOException, InterruptedException, AlreadyPurchasedGameExeption {
        view.limparTela();
        view.msgf("Digite o nome do jogo que queira comprar (0 para voltar): ");
        String jogo = view.lerString();
        if (jogo.equals("0") || jogo.isEmpty()) {
            return;
        }
        List<Jogo> jogos = jogoDAO.procurarJogo(jogo);
        boolean io = true;
        while (io) {
            if (jogos.size() == 1) {
                view.limparTela();
                view.msgf("Tem certeza que deseja comprar %s? (Y/N)\n-> ", jogos.get(0).getNome());
                String confirmacao = view.lerString();
                switch (confirmacao) {
                    case "y", "Y":
                        jogador_jogoDAO.comprarJogo(jogos.get(0), jogador);
                        view.pauseComMsg("Jogo adicionado à sua biblioteca.");
                        io = false;
                        return;
                
                    case "n", "N":
                        view.pauseComMsg("");
                        io = false;
                        break;

                    default:
                        view.pauseComMsg("Digite uma opção válida.");
                        break;
                }
            }
            else if (jogos.size() > 1) {
                try {
                    view.limparTela();
                    for(int i = 0; i < jogos.size(); i++) {
                        view.msgf("\n%d - %s", i+1, jogos.get(i).getNome());
                    }
                    view.msgf("\nDigite o número ao lado do jogo que queira comprar (0 para voltar): ");
                    int j = view.lerInt();
                    view.lerString();
                    if (j == 0) {
                        return;
                    }
                    view.limparTela();
                    view.msgf("Tem certeza que deseja comprar %s? (Y/N)\n-> ", jogos.get(j-1).getNome());
                    String confirmacao = view.lerString();
                    switch (confirmacao) {
                        case "y", "Y":
                            jogador_jogoDAO.comprarJogo(jogos.get(j-1), jogador);
                            view.pauseComMsg("Jogo adicionado à sua biblioteca.");
                            return;
                        
                        case "n", "N":
                            view.lerString();
                            view.pauseComMsg("");
                            return;
                        
                        default:
                            view.pauseComMsg("Digite uma opção válida.");
                            break;
                    }
                } catch (IndexOutOfBoundsException e) {
                    view.pauseComMsg("Selecione uma opção válida.");
                }
            }
            else {
                view.pauseComMsg("Jogo não encontrado");
                io = false;
                return;
            }
        }
    }

    public boolean deletarConta(Jogador jogador) throws SQLException, IOException, InterruptedException {
        while (true) {
            view.limparTela();
			view.msgf("Você tem certeza que quer deletar sua conta? (y/n)\n-> ");
			String resposta = view.lerString();
			switch (resposta) {
				case "y", "Y":
                    view.limparTela();
                    view.msgf("Digite sua senha: ");
                    String senha = view.lerString();
                    if (senha.equals(jogador.getSenha())) {
                        jogador_jogoDAO.deletarConta(jogador);
                        return true;
                    }
                    else {
                        view.pauseComMsg("Senha incorreta.");
                        return false;
                    }
				case "n", "N":
					return false;
				default:
					view.pauseComMsg("Digite uma opção válida");
					break;
			}
		}
    }

    public boolean mudarSenha(Jogador jogador) throws SQLException, IOException, InterruptedException {
        view.limparTela();
        view.msgf("Digite sua senha: ");
        String senha = view.lerString();
        view.limparTela();
        if (senha.equals(jogador.getSenha())) {
            view.msg("Senha deve conter no mínimo 8 caractéres e sem espaços\n");
            view.msgf("Digite a nova senha: ");
            String novaSenha = view.lerString();
            if (novaSenha.isEmpty() || novaSenha.contains(" ") || novaSenha.length() < 8) {
                view.pauseComMsg("Senha inválida");
                return false;
            }
            else {
                jogadorDAO.mudarSenha(jogador, novaSenha);
                return true;
            }
        }
        else {
            view.pauseComMsg("Senha incorreta.");
            return false;
        }
    }

    public boolean mudarNome(Jogador jogador) throws SQLException, IOException, InterruptedException {
        view.limparTela();
        view.msgf("Digite sua senha: ");
        String senha = view.lerString();
        view.limparTela();
        if (senha.equals(jogador.getSenha())) {
            view.msg("Usuário deve conter no mínimo 3 carácteres e sem espaço\n");
            view.msgf("Digite o novo nome de usuário: ");
            String novoNome = view.lerString();
            if (novoNome.isEmpty() || novoNome.contains(" ") || novoNome.length() < 3) {
                view.pauseComMsg("Nome de usuário indisponível");
                return false;
            }
            else {
                return jogadorDAO.mudarNome(jogador, novoNome, view);
            }
        }
        else {
            view.pauseComMsg("Senha incorreta.");
            return false;
        }
    }

    public void mostrarBiblioteca(Jogador jogador) throws SQLException, IOException, InterruptedException {
        boolean io = true;
        while (io) {
            view.limparTela();
            int i = 1;
            ArrayList<Jogo> jogos = jogador_jogoDAO.mostrarBiblioteca(jogador);
            if (jogos.size() == 0) {
                view.pauseComMsg("Você não tem nenhum jogo na sua biblioteca.");
                return;
            }
            for (Jogo jogo : jogos) {
                view.msgf("\n%2d - %s", i, jogo.getNome());
                i++;
            }
            view.msg("\nSelecione o jogo para ver ações (0 para voltar)\n");
            view.msgf("-> ");
            int j = view.lerInt();
            view.lerString();
            if (j == 0) {
                io = true;
                return;
            }
            view.limparTela();

            while (true) {
                try {
                    view.menuJogo(jogos.get(j-1));
                    int op = view.lerInt();
                    view.lerString();
                    switch (op) {
                        case 0:
                            break;
                        case 1:
                            jogador_jogoDAO.reembolsarJogo(jogador, jogos.get(j-1));
                            view.pauseComMsg("Jogo reembolsado.");
                            return;
                        default:
                            view.pauseComMsg("Selecione uma opção válida.");
                            break;
                    }
                } catch (IndexOutOfBoundsException e) {
                    view.pauseComMsg("Selecione uma opção válida.");
                    break;
                }
            }
        }
    }

}
