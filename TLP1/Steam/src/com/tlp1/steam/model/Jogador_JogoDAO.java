package com.tlp1.steam.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.tlp1.steam.util.DatabaseConnection;

public class Jogador_JogoDAO {

	public void comprarJogo(Jogo jogo, Jogador jogador) throws SQLException, AlreadyPurchasedGameExeption {
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
			}
		}
	}

	public List<Jogo> mostrarBiblioteca(Jogador jogador) throws SQLException {
		ArrayList<Jogo> jogos = new ArrayList<>();
		try (Connection conexao = DatabaseConnection.getConnection()) {
			String sql = "SELECT jogos.id AS jogo_id, jogos.nome AS jogo_nome, jogador_jogos.id_jogo, jogador_jogos.id_jogador FROM public.jogos, public.jogador_jogos WHERE jogador_jogos.id_jogador = ? AND jogos.id = jogador_jogos.id_jogo";
			try (PreparedStatement stmt = conexao.prepareStatement(sql)) {
				stmt.setInt(1, jogador.getId());
				ResultSet rs = stmt.executeQuery();
				while (rs.next()) {
					Jogo jogo = new Jogo(rs.getString("jogo_nome"));
					jogo.setId(rs.getInt("jogo_id"));
					jogos.add(jogo);
				}
				return jogos;
			}
		}
	}

}
