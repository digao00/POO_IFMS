package com.tlp1.steam.model;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.tlp1.steam.util.DatabaseConnection;
import com.tlp1.steam.view.SteamView;

public class SteamDAO {

    public int criarConta(Jogador player, SteamView view) throws SQLException, IOException, InterruptedException {
        try (Connection conexao = DatabaseConnection.getConnection()) {
            String sql = "INSERT INTO jogador (nome, senha) VALUES (?, ?)";
            String sql2 = "SELECT * FROM jogador WHERE nome = ? ";
            try (PreparedStatement stmt2 = conexao.prepareStatement(sql2)) {
                stmt2.setString(1, player.getNome());
                ResultSet rs = stmt2.executeQuery();
                while (rs.next()) {
                    view.pauseComMsg("Nome de usuário indisponível.");
                    return 0;
                }
                try (PreparedStatement stmt = conexao.prepareStatement(sql)) {
                    stmt.setString(1, player.getNome());
                    stmt.setString(2, player.getSenha());
                    stmt.executeUpdate();
                    try (ResultSet rs2 = stmt2.executeQuery()) {
                        while (rs2.next()) {
                            player.setId(rs2.getInt("id"));
                            view.pauseComMsg("Conta criada com sucesso.");
                            return 1;
                        }
                    }
                    return 0;
                }
            }
        }
    }
}
