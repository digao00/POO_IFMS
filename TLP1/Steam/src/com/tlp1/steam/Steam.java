package com.tlp1.steam;

import java.io.IOException;

import com.tlp1.steam.controller.LoginController;
import com.tlp1.steam.model.JogadorDAO;
import com.tlp1.steam.model.JogoDAO;
import com.tlp1.steam.view.SteamView;

public class Steam {
    public static void main(String[] args) throws IOException, InterruptedException {
        SteamView view = new SteamView();
		JogadorDAO jogadorDAO = new JogadorDAO();
        JogoDAO jogoDAO = new JogoDAO();
		
    }
}
