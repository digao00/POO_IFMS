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
    private SteamView view;
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
        view.pauseComMsg("");
    }

    public void comprarJogo(Jogador jogador) throws SQLException, IOException, InterruptedException {
        view.limparTela();
        view.msgf("Digite o nome do jogo que queira comprar: ");
        String jogo = view.lerString();
        List<Jogo> jogos = jogoDAO.procurarJogo(jogo);
        if (jogos.size() == 1) {
            view.limparTela();
            view.msgf("Tem certeza que deseja comprar %s?", jogos.get(0).getNome());
            String confirmacao = view.lerString();
            switch (confirmacao) {
                case "y", "Y":
                    try {
                        jogador_jogoDAO.comprarJogo(jogos.get(0), view, jogador);
                        return;
                    } catch (AlreadyPurchasedGameExeption e) {
                        view.pauseComMsg(e.getMessage());
                    }
                    break;
            
                case "n", "N":
                    view.pauseComMsg("");
                    break;

                default:
                    break;
            }
        }
        //else if jogos.size() = 0
        //else jogos.size() > 1
    }
}
