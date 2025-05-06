package com.tlp1.steam;

import java.io.IOException;

import com.tlp1.steam.controller.SteamController;
import com.tlp1.steam.model.Jogador;
import com.tlp1.steam.model.SteamDAO;
import com.tlp1.steam.view.SteamView;

public class Steam {
    public static void main(String[] args) throws IOException, InterruptedException {
        SteamView view = new SteamView();
		SteamDAO dao = new SteamDAO();
		Jogador player = new Jogador();
		SteamController controller = new SteamController(view, dao, player);

        controller.inicio();
    }
}
