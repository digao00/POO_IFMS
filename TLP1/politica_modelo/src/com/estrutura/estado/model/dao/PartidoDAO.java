package com.estrutura.estado.model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.estrutura.estado.model.AlreadyCreatedPartidoExeption;
import com.estrutura.estado.model.Partido;
import com.estrutura.estado.util.DatabaseConnection;

public class PartidoDAO {

    public void cadastrarPartido(Partido partido) throws SQLException, AlreadyCreatedPartidoExeption{
        try (Connection conexao = DatabaseConnection.getConnection()) {
            String sql2 = "SELECT * FROM partidos WHERE sigla = ? OR nome_completo = ?";
            String sql = "INSERT INTO partidos (sigla, nome_completo, orientacao) VALUES (?, ?, ?)";
            try (PreparedStatement stmt2 = conexao.prepareStatement(sql2)) {
                ResultSet rs = stmt2.executeQuery();
                if (rs.next()) {
                    throw new AlreadyCreatedPartidoExeption("Sigla ou nome do partido invalidos");
                }
            }
            try (PreparedStatement stmt = conexao.prepareStatement(sql)) {
                stmt.setString(1, partido.getSigla());
                stmt.setString(2, partido.getNome_completo());
                stmt.setString(3, partido.getOrientacao());
            }
        }
    }

    public void listarPartidos() {
         
    }
}
