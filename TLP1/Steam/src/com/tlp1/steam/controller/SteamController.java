package com.tlp1.steam.controller;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

import com.tlp1.steam.view.SteamView;

public class SteamController {
    private SteamView view;

    public SteamController(SteamView view) {
        this.view = view;
    }
    
    public void inicio() throws IOException, InterruptedException {
        boolean io = true;
        while (io) {   
            view.menuLogin();
            int op = view.lerInt();
            
            switch (op) {
                case 1:
                    if (criarConta(conexao)) {
                        io = 0;
                    }
                    break;
                case 2:
                    view.msg("\nNome: ");
                    String nomeLogin = view.lerString()
                    viwe.msg("\nSenha: ");
                    String senhaLogin = view.lerString()
                    if (login(conexao, nomeLogin, senhaLogin, scanner)) {
                        io = 0;
                    }
                    break;
                default:
                    pauseComMsg(scanner, "Digite uma opção válida.");
                    break;
            }
        }
    }

    public int criarConta(Connection conexao) throws SQLException, IOException, InterruptedException {
		view.msg("Usuário deve conter no mínimo 3 carácteres e sem espaço\nSenha deve conter no mínimo 8 caractéres e sem espaços\n");
		view.msg("\nDigite um nome de usuário: ");
		String nome = view.lerString();
		view.msg("\nDigite uma senha: ");
		String senha = view.lerString();
		if (nome.isEmpty() || senha.isEmpty() || nome.contains(" ") || senha.contains(" ") || senha.length() < 8 || nome.length() < 3) {
			view.pauseComMsg("Nome de usuário indisponível.");
			return 0;
		}
		
		String sql = "INSERT INTO jogador (nome, senha) VALUES (?, ?)";
		String sql2 = "SELECT * FROM jogador WHERE nome = ? ";
		try (PreparedStatement stmt2 = conexao.prepareStatement(sql2)) {
			stmt2.setString(1, nome);
			ResultSet rs = stmt2.executeQuery();
			while (rs.next()) {
				view.pauseComMsg("Nome de usuário indisponível.");
				return 0;
			}
			try (PreparedStatement stmt = conexao.prepareStatement(sql)) {
				stmt.setString(1, nome);
				stmt.setString(2, senha);
				stmt.executeUpdate();
				try (ResultSet rs2 = stmt2.executeQuery()) {
					while (rs2.next()) {
						int id = rs2.getInt("id");
                        view.pauseComMsg("Conta criada com sucesso.");
                        return id;
					}
				}
                return 0;
			}
		}
	}

}
