package com.tlp1.steam.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import com.tlp1.steam.util.DatabaseConnection;

public class Jogador_JogoDAO {

	public void comprarJogo(Jogador_Jogo jj) throws SQLException, AlreadyPurchasedGameExeption {
		try (Connection conexao = DatabaseConnection.getConnection()) {
			String sql3 = "SELECT * FROM jogador_jogos WHERE id_jogador = ? AND id_jogo = ? ";
			String sql = "INSERT INTO jogador_jogos (id_jogo, id_jogador) VALUES (?, ?)";

			try (PreparedStatement stmt3 = conexao.prepareStatement(sql3)) {
				stmt3.setInt(1, jj.getJogador().getId());
				stmt3.setInt(2, jj.getJogo().getId());
				ResultSet rs2 = stmt3.executeQuery();

				if (rs2.next()) {
					throw new AlreadyPurchasedGameExeption("Jogo selecionado ja está disponivel em sua biblioteca");
				}
			}
			try (PreparedStatement stmt = conexao.prepareStatement(sql)) {
				stmt.setInt(1, jj.getJogo().getId());
				stmt.setInt(2, jj.getJogador().getId());
				stmt.executeUpdate();
			}
		}
	}

	public ArrayList<Jogo> mostrarBiblioteca(Jogador jogador) throws SQLException {
		ArrayList<Jogo> jogos = new ArrayList<>();
		String sql = "SELECT jogos.id AS jogo_id, jogos.nome AS jogo_nome, jogador_jogos.id_jogo, jogador_jogos.id_jogador " + 
		"FROM public.jogos, public.jogador_jogos WHERE jogador_jogos.id_jogador = ? AND jogos.id = jogador_jogos.id_jogo";

		try (Connection conexao = DatabaseConnection.getConnection()) {	
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

	public void deletarConta(Jogador jogador) throws SQLException {
		String sql = "DELETE FROM jogador_jogos WHERE id_jogador = ?; DELETE FROM jogador WHERE id = ? ";

		try (Connection conexao = DatabaseConnection.getConnection()) {
			try (PreparedStatement stmt = conexao.prepareStatement(sql)) {
				stmt.setInt(1, jogador.getId());
				stmt.setInt(2, jogador.getId());
				stmt.executeUpdate();
			}
		}
	}

	public void reembolsarJogo(Jogador_Jogo jj) throws SQLException {
		String sql = "DELETE FROM jogador_jogos WHERE id_jogador = ? AND id_jogo = ?";

		try (Connection conexao = DatabaseConnection.getConnection()) {
			try (PreparedStatement stmt = conexao.prepareStatement(sql)) {
				stmt.setInt(1, jj.getJogador().getId());
				stmt.setInt(2, jj.getJogo().getId());
				stmt.executeUpdate();
			}
		}
	}
}
