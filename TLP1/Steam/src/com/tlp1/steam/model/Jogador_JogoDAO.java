package com.tlp1.steam.model;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.tlp1.steam.util.DatabaseConnection;
import com.tlp1.steam.view.SteamView;

public class Jogador_JogoDAO {

    public void comprarJogo(Jogo jogo, SteamView view, Jogador jogador) 
            throws SQLException, IOException, InterruptedException, AlreadyPurchasedGameExeption {

        try (Connection conexao = DatabaseConnection.getConnection()) {
	    	String sql3 = "SELECT * FROM jogador_jogos WHERE id_jogador = ? AND id_jogo = ? ";
	    	String sql = "INSERT INTO jogador_jogos (id_jogo, id_jogador) VALUES (?, ?)";

	    	try (PreparedStatement stmt3 = conexao.prepareStatement(sql3)) {
	    		stmt3.setInt(1, jogador.getId());
	    		stmt3.setInt(2, jogo.getId());
	    		ResultSet rs2 = stmt3.executeQuery();
	    		if (rs2.next()) {
	    			throw new AlreadyPurchasedGameExeption("Jogo selecionado ja está disponivel em sua biblioteca");
	    		}
	    	}
	    	
			try (PreparedStatement stmt = conexao.prepareStatement(sql)) {
				stmt.setInt(1, jogo.getId());
				stmt.setInt(2, jogador.getId());
				stmt.executeUpdate();
				view.pauseComMsg("Jogo adicionado à sua biblioteca.");
			}
	    }
    }
}
