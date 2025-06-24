package com.tlp1.steam.model;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.tlp1.steam.util.DatabaseConnection;
import com.tlp1.steam.view.SteamView;

public class JogadorDAO {

    public Jogador criarConta(Jogador player, SteamView view) throws SQLException, IOException, InterruptedException {
        try (Connection conexao = DatabaseConnection.getConnection()) {
            String sql = "INSERT INTO jogador (nome, senha) VALUES (?, ?)";
            String sql2 = "SELECT * FROM jogador WHERE nome = ? ";
            try (PreparedStatement stmt2 = conexao.prepareStatement(sql2)) {
                stmt2.setString(1, player.getNome());
                ResultSet rs = stmt2.executeQuery();
                while (rs.next()) {
                    view.pauseComMsg("Nome de usuário indisponível.");
                    return null;
                }
                try (PreparedStatement stmt = conexao.prepareStatement(sql)) {
                    stmt.setString(1, player.getNome());
                    stmt.setString(2, player.getSenha());
                    stmt.executeUpdate();
                    try (ResultSet rs2 = stmt2.executeQuery()) {
                        while (rs2.next()) {
                            player.setId(rs2.getInt("id"));
                            view.pauseComMsg("Conta criada com sucesso.");
                            return player;
                        }
                        return null;
                    }
                }
            }
        }
    }

    public Jogador login(Jogador player, SteamView view) throws SQLException, IOException, InterruptedException {
        try (Connection conexao = DatabaseConnection.getConnection()) {
            String sql = "SELECT * FROM jogador WHERE nome = ? AND senha = ? ";
            try (PreparedStatement stmt = conexao.prepareStatement(sql)) {
                stmt.setString(1, player.getNome());
                stmt.setString(2, player.getSenha());
                ResultSet rs = stmt.executeQuery();
                while (rs.next()) {
                    player.setId(rs.getInt("id"));
                }
            }
            if (player.getId() == 0) {
                view.pauseComMsg("Nome ou senha errados.");
                return null;
            } else {
                view.pauseComMsg("Conta Logada.");
                return player;
            }
        }
    }

    public void deletarConta(Jogador player, SteamView view) throws SQLException, IOException, InterruptedException {
        try (Connection conexao = DatabaseConnection.getConnection()) {
            String sql = "DELETE FROM jogador_jogos WHERE id_jogador = ?; DELETE FROM jogador WHERE id = ? ";
            try (PreparedStatement stmt = conexao.prepareStatement(sql)) {
                stmt.setInt(1, player.getId());
                stmt.setInt(2, player.getId());
                stmt.executeUpdate();
                view.pauseComMsg("Conta deletada.");
            }
        }
	}

}
