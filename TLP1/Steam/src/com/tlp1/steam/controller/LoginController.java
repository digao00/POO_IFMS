package com.tlp1.steam.controller;

import java.io.IOException;
import java.sql.SQLException;

import com.tlp1.steam.model.Jogador;
import com.tlp1.steam.model.JogadorDAO;
import com.tlp1.steam.view.SteamView;

public class LoginController {
    private SteamView view;
    private JogadorDAO dao;

    public LoginController(SteamView view, JogadorDAO dao) {
        this.dao = dao;
        this.view = view;
    }

    public Jogador inicio() throws IOException, InterruptedException, SQLException{
        try {
            boolean io = true;
            while (io) {
                view.menuLogin();
                int op = view.lerInt();
                view.lerString();

                Jogador jogador = null;
                switch (op) {
                    case 1:
                        jogador = criarConta();
                        if (jogador != null) {
                            io = false;
                            return jogador;
                        }
                        break;
                    case 2:
                        jogador = login();
                        if (jogador != null) {
                            io = false;
                            return jogador;
                        }
                        break;
                    default:
                        view.pauseComMsg("Digite uma opção válida.");
                        break;
                }
            }

        } finally {}
        return null;
    }

    private Jogador criarConta() throws SQLException, IOException, InterruptedException {
        view.lerString();
        view.limparTela();
        view.msg("Usuário deve conter no mínimo 3 carácteres e sem espaço\nSenha deve conter no mínimo 8 caractéres e sem espaços\n");
        view.msg("\nDigite um nome de usuário: ");
        String nome = view.lerString();
        view.msg("\nDigite uma senha: ");
        String senha = view.lerString();

        if (nome.isEmpty() || senha.isEmpty() || nome.contains(" ") || senha.contains(" ") || senha.length() < 8
                || nome.length() < 3) {
            view.pauseComMsg("Nome de usuário indisponível.");
            return null;
        }

        Jogador player = new Jogador(nome, senha);
        return dao.criarConta(player, view);
    }

    private Jogador login() throws SQLException, IOException, InterruptedException {
        view.limparTela();
        view.msg("\nNome: ");
        String nomeLogin = view.lerString();
        view.msg("\nSenha: ");
        String senhaLogin = view.lerString();

        Jogador player = new Jogador(nomeLogin, senhaLogin);
        return dao.login(player, view);
    }

}
