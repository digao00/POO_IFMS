package com.tlp1.steam.model;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.tlp1.steam.util.DatabaseConnection;
import com.tlp1.steam.view.SteamView;

public class JogoDAO {

    public List<Jogo> mostrarLoja(SteamView view) throws SQLException, IOException, InterruptedException {
        List<Jogo> jogos = new ArrayList<>();
        try (Connection conexao = DatabaseConnection.getConnection()) {
            String sql = "SELECT * FROM jogos ORDER BY id ASC";
            try (PreparedStatement stmt = conexao.prepareStatement(sql); ResultSet rs = stmt.executeQuery()) {
                while (rs.next()) {
                    Jogo jogo = new Jogo(rs.getString("nome"));
                    jogo.setId(rs.getInt("id"));
                    jogos.add(jogo);
                }
                return jogos;
            }
        }
    }

    public List<Jogo> procurarJogo(String nome) throws SQLException, IOException {
        List<Jogo> jogos = new ArrayList<>();
        try (Connection conexao = DatabaseConnection.getConnection()) {
            String sql = "SELECT * FROM jogos WHERE nome LIKE '%"+ nome +"%'";
            try (PreparedStatement stmt = conexao.prepareStatement(sql); ResultSet rs = stmt.executeQuery()) {
                while (rs.next()) {
                    Jogo jogo = new Jogo(rs.getString("nome"));
                    jogo.setId(rs.getInt("id"));
                    jogos.add(jogo);
                }
                return jogos;
            }
        }
    }
}
