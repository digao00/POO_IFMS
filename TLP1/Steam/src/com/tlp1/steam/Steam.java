package com.tlp1.steam;

import java.io.IOException;
import java.sql.SQLException;

import com.tlp1.steam.controller.SteamController;
import com.tlp1.steam.model.JogadorDAO;
import com.tlp1.steam.model.JogoDAO;
import com.tlp1.steam.view.SteamView;

public class Steam {
    public static void main(String[] args) throws IOException, InterruptedException {
        SteamView view = new SteamView();
		JogadorDAO jogadorDAO = new JogadorDAO();
        JogoDAO jogoDAO = new JogoDAO();
        SteamController controller = new SteamController(view, jogadorDAO, jogoDAO);

        try {
            controller.inicio();
        } catch (SQLException e) {
            System.out.println("Erro com o banco de dados: " + e.getMessage());
        }
		
    }
}
