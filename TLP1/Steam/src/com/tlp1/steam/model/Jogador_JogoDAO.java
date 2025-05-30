package com.tlp1.steam.model;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

import com.tlp1.steam.util.DatabaseConnection;
import com.tlp1.steam.view.SteamView;

public class Jogador_JogoDAO {

    private static void comprarJogo(Jogo jogo, SteamView view, Jogador jogador) 
            throws SQLException, IOException, InterruptedException, InvalidIdExeption, AlreadyPurchasedGameExeption {

        try (Connection conexao = DatabaseConnection.getConnection()) {
	    	String sql3 = "SELECT * FROM jogador_jogos WHERE id_jogador = ? AND id_jogo = ? ";
	    	String sql2 = "INSERT INTO jogador_jogos (id_jogo, id_jogador) VALUES (?, ?)";
	    	String sql = "SELECT * FROM jogos WHERE id = ? ";

	    	try (PreparedStatement stmt3 = conexao.prepareStatement(sql3)) {
	    		stmt3.setInt(1, jogador.getId());
	    		stmt3.setInt(2, jogo.getId());
	    		ResultSet rs2 = stmt3.executeQuery();
	    		if (rs2.next()) {
	    			throw new AlreadyPurchasedGameExeption("Jogo selecionado ja está disponivel em sua biblioteca");
	    		}
	    	}
	    	try (PreparedStatement stmt = conexao.prepareStatement(sql)) {
	    		stmt.setInt(1, jogo);
	    		ResultSet rs = stmt.executeQuery();
	    		if (!rs.next()) {
	    			throw new InvalidIdExeption("Erro: ID inválido");
	    		}
	    		int confirmation = 0;
	    		while (confirmation == 0) {
	    			limparTela();
	    			System.out.printf("\nTem certeza que deseja comprar %s? (y/n)\n", rs.getString("nome"));
	    			String resposta = scanner.nextLine();
	    			switch (resposta) {
	    				case "y", "Y":
	    					confirmation = 1;
	    					break;
	    				case "n", "N":
	    					return;
	    				default:
	    					pauseComMsg(scanner, "Digite uma opção válida.");
	    					break;
	    			}
	    		}
	    		try (PreparedStatement stmt2 = conexao.prepareStatement(sql2)) {
	    			limparTela();
	    			stmt2.setInt(1, jogo);
	    			stmt2.setInt(2, userID);
	    			stmt2.executeUpdate();
	    			pauseComMsg(scanner, "Jogo adicionado à sua biblioteca.");
	    		}
	    	}
	    }
    }
}
