package com.tlp1.steam.model;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.tlp1.steam.util.DatabaseConnection;
import com.tlp1.steam.view.SteamView;

public class JogadorDAO {

    public Jogador criarConta(Jogador jogador, SteamView view) throws SQLException, IOException, InterruptedException {
        try (Connection conexao = DatabaseConnection.getConnection()) {
            String sql = "INSERT INTO jogador (nome, senha) VALUES (?, ?)";
            String sql2 = "SELECT * FROM jogador WHERE nome = ? ";
            try (PreparedStatement stmt2 = conexao.prepareStatement(sql2)) {
                stmt2.setString(1, jogador.getNome());
                ResultSet rs = stmt2.executeQuery();
                while (rs.next()) {
                    view.pauseComMsg("Nome de usuário indisponível.");
                    return null;
                }
                try (PreparedStatement stmt = conexao.prepareStatement(sql)) {
                    stmt.setString(1, jogador.getNome());
                    stmt.setString(2, jogador.getSenha());
                    stmt.executeUpdate();
                    try (ResultSet rs2 = stmt2.executeQuery()) {
                        while (rs2.next()) {
                            jogador.setId(rs2.getInt("id"));
                            view.pauseComMsg("Conta criada com sucesso.");
                            return jogador;
                        }
                        return null;
                    }
                }
            }
        }
    }

    public Jogador login(Jogador jogador, SteamView view) throws SQLException, IOException, InterruptedException {
        try (Connection conexao = DatabaseConnection.getConnection()) {
            String sql = "SELECT * FROM jogador WHERE nome = ? AND senha = ? ";
            try (PreparedStatement stmt = conexao.prepareStatement(sql)) {
                stmt.setString(1, jogador.getNome());
                stmt.setString(2, jogador.getSenha());
                ResultSet rs = stmt.executeQuery();
                while (rs.next()) {
                    jogador.setId(rs.getInt("id"));
                }
            }
            if (jogador.getId() == 0) {
                view.pauseComMsg("Nome ou senha errados.");
                return null;
            } else {
                view.pauseComMsg("Conta Logada.");
                return jogador;
            }
        }
    }

    public void deletarConta(Jogador jogador) throws SQLException {
        try (Connection conexao = DatabaseConnection.getConnection()) {
            String sql = "DELETE FROM jogador_jogos WHERE id_jogador = ?; DELETE FROM jogador WHERE id = ? ";
            try (PreparedStatement stmt = conexao.prepareStatement(sql)) {
                stmt.setInt(1, jogador.getId());
                stmt.setInt(2, jogador.getId());
                stmt.executeUpdate();
            }
        }
	}

    public void mudarSenha(Jogador jogador, String novaSenha) throws SQLException {
        try (Connection conexao = DatabaseConnection.getConnection()) {
            String sql = "UPDATE jogador SET senha = '" + novaSenha + "' WHERE id = ?";
            try (PreparedStatement stmt = conexao.prepareStatement(sql)) {
                stmt.setInt(1, jogador.getId());
                stmt.executeUpdate();
                jogador.setSenha(novaSenha);
            }
        }
    }

    public boolean mudarNome(Jogador jogador, String novoNome, SteamView view) throws SQLException, IOException, InterruptedException {
        try (Connection conexao = DatabaseConnection.getConnection()) {
            String sql = "UPDATE jogador SET nome = '" + novoNome + "' WHERE id = ?";
            String sql2 = "SELECT * FROM jogador WHERE nome = ? ";
            try (PreparedStatement stmt2 = conexao.prepareStatement(sql2)) {
                stmt2.setString(1, novoNome);
                ResultSet rs = stmt2.executeQuery();
                if (rs.next()) {
                    view.pauseComMsg("Nome de usuário indisponível.");
                    return false;
                }
                else {
                    try (PreparedStatement stmt = conexao.prepareStatement(sql)) {
                    stmt.setInt(1, jogador.getId());
                    stmt.executeUpdate();
                    jogador.setNome(novoNome);
                    return true;
                    }
                }
            }
        }
    }

}
