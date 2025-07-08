package com.estrutura.estado.model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import com.estrutura.estado.model.Partido;
import com.estrutura.estado.util.DatabaseConnection;

public class PartidoDAO {

    public void cadastrarPartido(Partido partido) throws SQLException {
        try (Connection conexao = DatabaseConnection.getConnection()) {
            String sql = "INSERT INTO partidos (sigla, nome_completo, orientacao) VALUES (?, ?, ?)";
            try (PreparedStatement stmt = conexao.prepareStatement(sql)) {
                stmt.setString(1, partido.getSigla());
                stmt.setString(2, partido.getNome_completo());
                stmt.setString(3, partido.getOrientacao());
            }
        }
    }
}
