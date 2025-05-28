package com.tlp1.steam.controller;

import java.io.IOException;
import java.sql.SQLException;

import com.tlp1.steam.model.JogadorDAO;
import com.tlp1.steam.model.Jogo;
import com.tlp1.steam.model.JogoDAO;
import com.tlp1.steam.view.SteamView;

public class SteamController {
    private SteamView view;
    private JogadorDAO jogadorDAO;
    private JogoDAO jogoDAO;
    private LoginController login;

    public SteamController(SteamView view, JogadorDAO jogadorDAO, JogoDAO jogoDAO) {
        this.view = view;
        this.jogadorDAO = jogadorDAO;
        this.jogoDAO = jogoDAO;
        this.login = new LoginController(view, jogadorDAO);
    }

    public void inicio() throws IOException, InterruptedException, SQLException {
        try {
            if (login.inicio()) {
                boolean io = true;
                while (io) {
                    view.menuIniciar();
                    int op = view.lerInt();
                    switch (op) {
                        case 1:
                            mostralLoja();
                            break;
                        case 2:
                            // comprarJogo()
                            break;
                        case 3:
                            // mostrarBiblioteca()
                            break;
                        case 4:
                            // personalizar
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
        for (Jogo jogo : jogoDAO.mostrarLoja(view)) {
            int i = 1;
            System.out.printf("\n%2d - %s", i, jogo.getNome());
            i++;
        }
        view.pauseComMsg("Pressione Enter para continuar...");
    }

}
