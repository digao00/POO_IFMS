package com.tlp1.steam;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.InputMismatchException;
import java.util.Scanner;

//import com.tlp1.steam.controller.SteamController;
import com.tlp1.steam.model.AlreadyPurchasedGameExeption;
import com.tlp1.steam.model.InvalidIdExeption;
//import com.tlp1.steam.model.Jogador;
//import com.tlp1.steam.model.SteamDAO;
//import com.tlp1.steam.view.SteamView;

import java.io.IOException;

public class SteamApp {

	private static int userID;

	public static void main(String[] args) throws IOException, InterruptedException {
		//SteamView view = new SteamView();
		//SteamDAO dao = new SteamDAO();
		//Jogador player = new Jogador();
		//SteamController controller = new SteamController(view, dao, player);

		String url = "jdbc:postgresql://localhost:5432/Steam";
		String usuario = "postgres";
		String senha = "postgresql"; // F101 = "postgresql" F103 = "postgres"
		Scanner scanner = new Scanner(System.in);

		try (Connection conexao = DriverManager.getConnection(url, usuario, senha)) {
			int io = 1;
			while (io == 1) {
				try {
					limparTela();
					System.out.println("\n###### Steam ######\n");
					System.out.println("1 - Criar conta");
					System.out.println("2 - Login");
					System.out.printf("\n-> ");
					int opcao = scanner.nextInt();
					scanner.nextLine();
					limparTela();

					switch (opcao) {
						case 1:
							if (criarConta(conexao, scanner)) {
								io = 0;
							}
							break;
						case 2:
							System.out.printf("\nNome: ");
							String nomeLogin = scanner.nextLine();
							System.out.printf("\nSenha: ");
							String senhaLogin = scanner.nextLine();
							if (login(conexao, nomeLogin, senhaLogin, scanner)) {
								io = 0;
							}
							break;
						default:
							pauseComMsg(scanner, "Digite uma opção válida.");
							break;
					}
				} catch (InputMismatchException e) {
					scanner.nextLine();
					pauseComMsg(scanner, "Digite uma opção válida.");
				}
			}

			while (true) {
				try {
					limparTela();
					System.out.println("\nEscolha uma opção:");
					System.out.println("1 - Mostrar Loja");
					System.out.println("2 - Comprar jogo");
					System.out.println("3 - Sua biblioteca");
					System.out.println("4 - Personalizar");
					System.out.println("0 - Sair");
					System.out.printf("\n-> ");
					int opcao = scanner.nextInt();
					scanner.nextLine();

					switch (opcao) {
						case 1:
							limparTela();
							mostrarLoja(conexao, scanner);
							limparTela();
							break;
						case 2:
							while (true) {
								try {
									limparTela();
									System.out.printf(
											"\nDigite o id do jogo que queira comprar (digite 0 para voltar): ");
									int jogo = scanner.nextInt();
									scanner.nextLine();
									if (jogo == 0) {
										break;
									}
									comprarJogo(conexao, scanner, jogo);
									break;
								} catch (InvalidIdExeption e) {
									pauseComMsg(scanner, "Digite um id válido");
								} catch (InputMismatchException e) {
									scanner.nextLine();
									pauseComMsg(scanner, "Digite um id válido");
								} catch (AlreadyPurchasedGameExeption e) {
									pauseComMsg(scanner, e.getMessage());
								}
							}
							break;
						case 3:
							limparTela();
							mostrarBiblioteca(conexao, scanner);
							break;
						case 4:
							limparTela();
							int confirmation = 0;
							while (confirmation == 0) {
								try {
									System.out.println("1 - Mudar senha");
									System.out.println("2 - Excluir conta");
									System.out.println("0 - Voltar");
									System.out.printf("-> ");
									opcao = scanner.nextInt();
									scanner.nextLine();
									switch (opcao) {
										case 1:
											while (true) {
												if (mudarSenha(conexao, scanner)) {
													confirmation = 1;
													break;
												}
											}
										case 2:
											if (deletarConta(conexao, scanner)) {
												limparTela();
												System.out.println("Saindo...");
												return;
											}
											limparTela();
										case 0:
											limparTela();
											confirmation = 1;
											break;
										default:
											limparTela();
											pauseComMsg(scanner, "Opção inválida");
											break;
									}
								} catch (InputMismatchException e) {
									scanner.nextLine();
									limparTela();
									pauseComMsg(scanner, "Opção inválida");
								}
							}
						case 5:
							break;
						case 0:
							System.out.println("Saindo...");
							return;
						default:
							pauseComMsg(scanner, "Digite uma opção válida.");
							break;
					}
				} catch (InputMismatchException e) {
					scanner.nextLine();
					pauseComMsg(scanner, "Digite uma opção válida.");
				}
			}
		} catch (SQLException e) {
			System.out.println("Erro ao conectar ao banco de dados: " + e.getMessage());
		} finally {
			scanner.close();
		}
	}

	// Métodos

	private static boolean criarConta(Connection conexao, Scanner scanner)
			throws SQLException, IOException, InterruptedException {
		System.out.println(
				"Usuário deve conter no mínimo 3 carácteres e sem espaço\nSenha deve conter no mínimo 8 caractéres e sem espaços");
		System.out.printf("\nDigite um nome de usuário: ");
		String nome = scanner.nextLine().trim();
		System.out.printf("\nDigite uma senha: ");
		String senha = scanner.nextLine().trim();
		if (nome.isEmpty() || senha.isEmpty() || nome.contains(" ") || senha.contains(" ") || senha.length() < 8
				|| nome.length() < 3) {
			pauseComMsg(scanner, "Nome de usuário indisponível.");
			return false;
		}

		String sql = "INSERT INTO jogador (nome, senha) VALUES (?, ?)";
		String sql2 = "SELECT * FROM jogador WHERE nome = ? ";
		try (PreparedStatement stmt2 = conexao.prepareStatement(sql2)) {
			stmt2.setString(1, nome);
			ResultSet rs = stmt2.executeQuery();
			while (rs.next()) {
				pauseComMsg(scanner, "Nome de usuário indisponível.");
				return false;
			}
			try (PreparedStatement stmt = conexao.prepareStatement(sql)) {
				stmt.setString(1, nome);
				stmt.setString(2, senha);
				stmt.executeUpdate();
				try (ResultSet rs2 = stmt2.executeQuery()) {
					while (rs2.next()) {
						userID = rs2.getInt("id");
					}
				}
				pauseComMsg(scanner, "Conta criada com sucesso.");
				return true;
			}
		}
	}

	private static void mostrarLoja(Connection conexao, Scanner scanner) throws SQLException {
		String sql = "SELECT * FROM jogos ORDER BY id ASC";
		try (PreparedStatement stmt = conexao.prepareStatement(sql); ResultSet rs = stmt.executeQuery()) {
			while (rs.next()) {
				System.out.printf("%s - %s\n", rs.getString("id"), rs.getString("nome"));
			}
		}
		System.out.println("Pressione Enter para sair...");
		scanner.nextLine();
	}

	private static void mostrarBiblioteca(Connection conexao, Scanner scanner) throws SQLException {
		String sql = "SELECT * FROM public.jogos, public.jogador_jogos WHERE jogador_jogos.id_jogador = ? AND jogos.id = jogador_jogos.id_jogo";
		try (PreparedStatement stmt = conexao.prepareStatement(sql)) {
			stmt.setInt(1, userID);
			ResultSet rs = stmt.executeQuery();
			System.out.println("Sua biblioteca: ");
			while (rs.next()) {
				System.out.println(rs.getString("nome"));
			}
			System.out.println("\nPressione Enter para continuar...");
			scanner.nextLine();
		}
	}

	private static void comprarJogo(Connection conexao, Scanner scanner, int jogo)
			throws SQLException, IOException, InterruptedException, InvalidIdExeption, AlreadyPurchasedGameExeption {
		String sql3 = "SELECT * FROM jogador_jogos WHERE id_jogador = ? AND id_jogo = ? ";
		String sql2 = "INSERT INTO jogador_jogos (id_jogo, id_jogador) VALUES (?, ?)";
		String sql = "SELECT * FROM jogos WHERE id = ? ";

		try (PreparedStatement stmt3 = conexao.prepareStatement(sql3)) {
			stmt3.setInt(1, userID);
			stmt3.setInt(2, jogo);
			ResultSet rs2 = stmt3.executeQuery();
			if (rs2.next()) {
				throw new AlreadyPurchasedGameExeption("Jogo selecionado ja está disponivel em sua conta");
			}
		}
		try (PreparedStatement stmt = conexao.prepareStatement(sql)) {
			stmt.setInt(1, jogo);
			ResultSet rs = stmt.executeQuery();
			if (!rs.next()) {
				throw new InvalidIdExeption("Erro: ID inválido");
			}
			int confirmation = 0;
			while (confirmation == 0) {
				limparTela();
				System.out.printf("\nTem certeza que deseja comprar %s? (y/n)\n", rs.getString("nome"));
				String resposta = scanner.nextLine();
				switch (resposta) {
					case "y", "Y":
						confirmation = 1;
						break;
					case "n", "N":
						return;
					default:
						pauseComMsg(scanner, "Digite uma opção válida.");
						break;
				}
			}
			try (PreparedStatement stmt2 = conexao.prepareStatement(sql2)) {
				limparTela();
				stmt2.setInt(1, jogo);
				stmt2.setInt(2, userID);
				stmt2.executeUpdate();
				pauseComMsg(scanner, "Jogo adicionado à sua biblioteca.");
			}
		}
	}

	private static boolean deletarConta(Connection conexao, Scanner scanner)
			throws SQLException, IOException, InterruptedException {
		String sql = "DELETE FROM jogador_jogos WHERE id_jogador = ?; DELETE FROM jogador WHERE id = ? ";
		limparTela();
		while (true) {
			System.out.println("Você tem certeza que quer deletar sua conta? (y/n)");
			String resposta = scanner.nextLine();
			switch (resposta) {
				case "y", "Y":
					try (PreparedStatement stmt = conexao.prepareStatement(sql)) {
						stmt.setInt(1, userID);
						stmt.setInt(2, userID);
						stmt.executeUpdate();
						pauseComMsg(scanner, "Alma libertada");
					}
					return true;
				case "n", "N":
					return false;
				default:
					pauseComMsg(scanner, "Digite uma opção válida.");
					break;
			}
		}
	}

	private static boolean login(Connection conexao, String nome, String senha, Scanner scanner)
			throws SQLException, IOException, InterruptedException {
		String sql = "SELECT * FROM jogador WHERE nome = ? AND senha = ? ";
		try (PreparedStatement stmt = conexao.prepareStatement(sql)) {
			stmt.setString(1, nome);
			stmt.setString(2, senha);
			ResultSet rs = stmt.executeQuery();
			while (rs.next()) {
				userID = rs.getInt("id");
			}
		}
		if (userID == 0) {
			pauseComMsg(scanner, "Nome ou senha errados.");
			return false;
		} else {
			pauseComMsg(scanner, "Conta Logada.");
			return true;
		}
	}

	// mudar senha e usuário
	private static boolean mudarSenha(Connection conexao, Scanner scanner)
			throws SQLException, IOException, InterruptedException {
		limparTela();
		String sql2 = "UPDATE senha FROM jogador WHERE id = ?";
		String sql = "SELECT * FROM jogador WHERE id = ?";
		System.out.printf("Digite sua senha: ");
		String senha = scanner.nextLine();
		try (PreparedStatement stmt = conexao.prepareStatement(sql)) {
			stmt.setInt(1, userID);
			ResultSet rs = stmt.executeQuery();
			while (rs.next()) {
				if (senha != rs.getString("senha")) {
					pauseComMsg(scanner, "Senha incorreta.");
					return false;
				} else {
					while (true) {
						limparTela();
						System.out.printf("\nDigite a nova senha: ");
						String senhaNova = scanner.nextLine().trim();
						if (senhaNova.isEmpty() || senhaNova.contains(" ") || senhaNova.length() < 8) {
							pauseComMsg(scanner, "Senha inválida.");
							return false;
						} else {
							try (PreparedStatement stmt2 = conexao.prepareStatement(sql2)) {
								stmt2.setInt(1, userID);
								stmt2.executeUpdate();
								pauseComMsg(scanner, "Senha Atualizada.");
								return true;
							}
						}
					}
				}
			}
		}
		return false;
	}
	// adicionarSaldo()

	private static void pauseComMsg(Scanner scanner, String msg) throws IOException, InterruptedException {
		limparTela();
		System.out.println(msg);
		System.out.println("Pressione Enter para continuar...");
		scanner.nextLine();
		limparTela();
	}

	private static void limparTela() throws IOException, InterruptedException {
		new ProcessBuilder("cmd", "/c", "cls").inheritIO().start().waitFor();
	}
}