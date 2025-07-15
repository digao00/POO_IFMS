package com.estrutura.estado.model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import com.estrutura.estado.model.AlreadyCreatedPartidoExeption;
import com.estrutura.estado.model.Partido;
import com.estrutura.estado.model.PartidoNotFoundExeption;
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
                stmt.executeUpdate();
            }
        }
    }

    public ArrayList<Partido> listarPartidos() throws SQLException {
        ArrayList<Partido> partidos = new ArrayList<>();
        try (Connection conexao = DatabaseConnection.getConnection()) {
            String sql = "SELECT * FROM partidos ORDER BY sigla";
            try (PreparedStatement stmt = conexao.prepareStatement(sql)) {
                ResultSet rs = stmt.executeQuery();
                while (rs.next()) {
                    Partido p = new Partido(rs.getString(2), rs.getString(3), rs.getString(4));
                    p.setId(rs.getInt(1));
                    partidos.add(p);
                }
                return partidos;
            }
        }
    }

    public void alterarPartido(Partido partido) throws SQLException {
        try (Connection conexao = DatabaseConnection.getConnection()) {
            String sql = "UPDATE partidos SET sigla = ?, nome_completo = ?, orientacao = ? WHERE id_partido = ?";
            try (PreparedStatement stmt = conexao.prepareStatement(sql)) {
                stmt.setString(1, partido.getSigla());
                stmt.setString(2, partido.getNome_completo());
                stmt.setString(3, partido.getOrientacao());
                stmt.setInt(4, partido.getId());
                stmt.executeUpdate();
            }
        }
    }

    public Partido acharPartido(int id) throws SQLException, PartidoNotFoundExeption {
        try (Connection conexao = DatabaseConnection.getConnection()) {
            String sql = "SELECT * FROM partidos WHERE id_partido = ?";
            try (PreparedStatement stmt = conexao.prepareStatement(sql)) {
                stmt.setInt(1, id);
                ResultSet rs = stmt.executeQuery();
                if (!rs.next()) {
                    throw new PartidoNotFoundExeption("Partido não encontrado");
                }
                else {
                    Partido p = new Partido(rs.getString(2), rs.getString(3), rs.getString(4));
                    p.setId(rs.getInt(1));
                    return p;
                }
            }
        }
    }

    public void excluirPartido(Partido partido) throws SQLException {
        try (Connection conexao = DatabaseConnection.getConnection()) {
            
            String sql = "DELETE FROM partidos WHERE id_partido = ?";
            try (PreparedStatement stmt = conexao.prepareStatement(sql)) {
                stmt.setInt(1, partido.getId());
                stmt.executeUpdate();
            }
        }
    }
}
