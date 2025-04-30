package com.tlp1.steam.controller;

import java.io.IOException;
import java.sql.SQLException;

import com.tlp1.steam.model.Jogador;
import com.tlp1.steam.model.SteamDAO;
import com.tlp1.steam.view.SteamView;

public class SteamController {
    private SteamView view;
    private SteamDAO dao;
    private Jogador player;

    public SteamController(SteamView view, SteamDAO dao, Jogador player) {
        this.dao = dao;
        this.view = view;
        this.player = player;
    }

    public void inicio() throws IOException, InterruptedException {
        try {
            boolean io = true;
            while (io) {
                view.menuLogin();
                int op = view.lerInt();
                view.lerString();

                switch (op) {
                    case 1:
                        if (criarConta()) {
                            io = false;
                        }
                        break;
                    case 2:
                        if (login()) {
                            io = false;
                        }
                        break;
                    default:
                        view.pauseComMsg("Digite uma opção válida.");
                        break;
                }
            }
            
        } catch (SQLException e) {
            System.out.println("Erro ao conectar ao banco de dados: " + e.getMessage());
        }
    }

    public boolean criarConta() throws SQLException, IOException, InterruptedException {
        view.msg("Usuário deve conter no mínimo 3 carácteres e sem espaço\nSenha deve conter no mínimo 8 caractéres e sem espaços\n");
        view.msg("\nDigite um nome de usuário: ");
        String nome = view.lerString();
        view.msg("\nDigite uma senha: ");
        String senha = view.lerString();

        if (nome.isEmpty() || senha.isEmpty() || nome.contains(" ") || senha.contains(" ") || senha.length() < 8
                || nome.length() < 3) {
            view.pauseComMsg("Nome de usuário indisponível.");
            return false;
        }

        player.setNome(nome);
        player.setSenha(senha);
        return dao.criarConta(player, view);
    }

    private boolean login() throws SQLException, IOException, InterruptedException {
        view.limparTela();
        view.msg("\nNome: ");
        String nomeLogin = view.lerString();
        view.msg("\nSenha: ");
        String senhaLogin = view.lerString();

        player.setNome(nomeLogin);
        player.setSenha(senhaLogin);
        return dao.login(player, view);
    }

}
