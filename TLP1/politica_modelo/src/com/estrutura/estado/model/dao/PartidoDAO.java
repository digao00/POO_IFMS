package com.estrutura.estado.model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;


import com.estrutura.estado.model.AlreadyCreatedPartidoExeption;
import com.estrutura.estado.model.Partido;
import com.estrutura.estado.model.PartidoBeingUsedException;
import com.estrutura.estado.model.PartidoNotFoundExeption;
import com.estrutura.estado.util.DatabaseConnection;

public class PartidoDAO {

    public void cadastrarPartido(Partido partido) throws SQLException, AlreadyCreatedPartidoExeption {
        try (Connection conexao = DatabaseConnection.getConnection()) {
            String sql2 = "SELECT * FROM partidos WHERE sigla = ? OR nome_completo = ?";
            String sql = "INSERT INTO partidos (sigla, nome_completo, orientacao) VALUES (?, ?, ?)";
            try (PreparedStatement stmt2 = conexao.prepareStatement(sql2)) {
                stmt2.setString(1, partido.getSigla());
                stmt2.setString(2, partido.getNome_completo());
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
            String sql = "SELECT * FROM partidos ORDER BY id_partido";
            try (PreparedStatement stmt = conexao.prepareStatement(sql)) {
                ResultSet rs = stmt.executeQuery();
                while (rs.next()) {
                    Partido p = new Partido(rs.getString(2), rs.getString(3), rs.getString(4));
                    p.setId(Integer.valueOf(rs.getInt(1)));
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
                } else {
                    Partido p = new Partido(rs.getString(2), rs.getString(3), rs.getString(4));
                    p.setId(Integer.valueOf(rs.getInt(1)));
                    return p;
                }
            }
        }
    }

    public void excluirPartido(Partido partido) throws SQLException, PartidoBeingUsedException {
        try (Connection conexao = DatabaseConnection.getConnection()) {
            String sql2 = "SELECT COUNT(*) FROM representantes_cg WHERE id_partido = ?";
            String sql = "DELETE FROM partidos WHERE id_partido = ?";
            try (PreparedStatement stmt2 = conexao.prepareStatement(sql2)) {
                stmt2.setInt(1, partido.getId());
                ResultSet rs = stmt2.executeQuery();
                while (rs.next()) {
                    if (Integer.valueOf(rs.getInt(1)) > 0) {
                        throw new PartidoBeingUsedException("Partido está sendo usado por representantes.\nNão foi possível deletar partido.");
                    }
                }
            }
            try (PreparedStatement stmt = conexao.prepareStatement(sql)) {
                stmt.setInt(1, partido.getId());
                stmt.executeUpdate();
            }
        }
    }

    public int numRepresentantes() throws SQLException {
        try (Connection conexao = DatabaseConnection.getConnection()) {
            int numRepresentantes = 0;
            String sql = "SELECT COUNT(*) FROM public.representantes_cg";
            try (PreparedStatement stmt = conexao.prepareStatement(sql)) {
                ResultSet rs = stmt.executeQuery();
                while (rs.next()) {
                    numRepresentantes = rs.getInt(1);
                }
            }
            return numRepresentantes;
        }
    }

    public HashMap<String, Integer> representantesPorOrientacao() throws SQLException {
        HashMap<String, Integer> orientacoesRep = new HashMap<>();
        try (Connection conexao = DatabaseConnection.getConnection()) {
            String sql = "SELECT COUNT(*) FROM public.representantes_cg, public.partidos WHERE representantes_cg.id_partido = partidos.id_partido AND partidos.orientacao = ?";

            try (PreparedStatement stmt = conexao.prepareStatement(sql)) {
                stmt.setString(1, "EXTREMA-ESQUERDA");
                ResultSet rs = stmt.executeQuery();
                while (rs.next()) {
                    orientacoesRep.put("EXTREMA-ESQUERDA", Integer.valueOf(rs.getInt(1)));
                }
            }

            try (PreparedStatement stmt = conexao.prepareStatement(sql)) {
                stmt.setString(1, "ESQUERDA");
                ResultSet rs = stmt.executeQuery();
                while (rs.next()) {
                    orientacoesRep.put("ESQUERDA", Integer.valueOf(rs.getInt(1)));
                }
            }

            try (PreparedStatement stmt = conexao.prepareStatement(sql)) {
                stmt.setString(1, "CENTRO-ESQUERDA");
                ResultSet rs = stmt.executeQuery();
                while (rs.next()) {
                    orientacoesRep.put("CENTRO-ESQUERDA", Integer.valueOf(rs.getInt(1)));
                }
            }

            try (PreparedStatement stmt = conexao.prepareStatement(sql)) {
                stmt.setString(1, "CENTRO");
                ResultSet rs = stmt.executeQuery();
                while (rs.next()) {
                    orientacoesRep.put("CENTRO", Integer.valueOf(rs.getInt(1)));
                }
            }

            try (PreparedStatement stmt = conexao.prepareStatement(sql)) {
                stmt.setString(1, "CENTRO-DIREITA");
                ResultSet rs = stmt.executeQuery();
                while (rs.next()) {
                    orientacoesRep.put("CENTRO-DIREITA", Integer.valueOf(rs.getInt(1)));
                }
            }

            try (PreparedStatement stmt = conexao.prepareStatement(sql)) {
                stmt.setString(1, "DIREITA");
                ResultSet rs = stmt.executeQuery();
                while (rs.next()) {
                    orientacoesRep.put("DIREITA", Integer.valueOf(rs.getInt(1)));
                }
            }

            try (PreparedStatement stmt = conexao.prepareStatement(sql)) {
                stmt.setString(1, "EXTREMA-DIREITA");
                ResultSet rs = stmt.executeQuery();
                while (rs.next()) {
                    orientacoesRep.put("EXTREMA-DIREITA", Integer.valueOf(rs.getInt(1)));
                }
            }
        }
        return orientacoesRep;
    }
}
