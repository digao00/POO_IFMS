package com.tlp1.steam.controller;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.tlp1.steam.model.Jogador;
import com.tlp1.steam.model.SteamDAO;
import com.tlp1.steam.util.DatabaseConnection;
import com.tlp1.steam.view.SteamView;

public class SteamController {
    private SteamView view;
    private SteamDAO dao;

    public SteamController(SteamView view, SteamDAO dao) {
        this.dao = dao;
        this.view = view;
    }

    public void inicio() throws IOException, InterruptedException {
        try (Connection conexao = DatabaseConnection.getConnection()) {
            boolean io = true;
            while (io) {
                view.menuLogin();
                int op = view.lerInt();
                view.lerString();

                switch (op) {
                    case 1:
                        if (criarConta() == 1) {
                            io = false;
                        }
                        break;
                    case 2:
                        view.limparTela();
                        view.msg("\nNome: ");
                        String nomeLogin = view.lerString();
                        view.msg("\nSenha: ");
                        String senhaLogin = view.lerString();
                        if (login(conexao, nomeLogin, senhaLogin)) {
                            io = false;
                        }
                        break;
                    default:
                        view.pauseComMsg("Digite uma opção válida.");
                        break;
                }
            }

        } catch (SQLException e) {
            System.out.println("Erro ao conectar ao banco de dados: " + e.getMessage());
        }
    }

    public int criarConta() throws SQLException, IOException, InterruptedException {
        view.msg("Usuário deve conter no mínimo 3 carácteres e sem espaço\nSenha deve conter no mínimo 8 caractéres e sem espaços\n");
        view.msg("\nDigite um nome de usuário: ");
        String nome = view.lerString();
        view.msg("\nDigite uma senha: ");
        String senha = view.lerString();

        if (nome.isEmpty() || senha.isEmpty() || nome.contains(" ") || senha.contains(" ") || senha.length() < 8 || nome.length() < 3) {
            view.pauseComMsg("Nome de usuário indisponível.");
            return 0;
        }

        Jogador player = new Jogador(nome, senha);
        return dao.criarConta(player, view);
    }

    private boolean login(Connection conexao, String nome, String senha)
            throws SQLException, IOException, InterruptedException {
        String sql = "SELECT * FROM jogador WHERE nome = ? AND senha = ? ";
        try (PreparedStatement stmt = conexao.prepareStatement(sql)) {
            stmt.setString(1, nome);
            stmt.setString(2, senha);
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                player.setId(rs.getInt("id"));
            }
        }
        if (player.getId() == 0) {
            view.pauseComMsg("Nome ou senha errados.");
            return false;
        } else {
            view.pauseComMsg("Conta Logada.");
            return true;
        }
    }

}
