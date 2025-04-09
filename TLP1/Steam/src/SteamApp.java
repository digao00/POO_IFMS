
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.InputMismatchException;
import java.util.Scanner;
import java.io.IOException;

public class SteamApp {
	
	private static int userID;
	public static void main(String[] args) throws IOException, InterruptedException {

		// "limparTela();" -> para limpar o terminal

        String url = "jdbc:postgresql://localhost:5432/Steam";
        String usuario = "postgres";
        String senha = "postgres"; //F101 = "postgresql" F103 = "postgres"
        Scanner scanner = new Scanner(System.in);
        
        try (Connection conexao = DriverManager.getConnection(url, usuario, senha)) {
            System.out.println("Conexão com o banco de dados estabelecida!");
			int io = 1;
			while (io == 1) {
				try {
					limparTela();
					System.out.println("\n###### Steam ######");
					System.out.println("1 - Criar conta");
					System.out.println("2 - Login");
					int opcao = scanner.nextInt();
					scanner.nextLine();
					limparTela();
					
					switch (opcao) {
						case 1:
							if (criarConta(conexao, scanner)) {io = 0;}
							break;
						case 2:
							System.out.printf("\nNome: ");
							String nomeLogin = scanner.nextLine();
							System.out.printf("\nSenha: ");
							String senhaLogin = scanner.nextLine();
							if (login(conexao, nomeLogin, senhaLogin, scanner)) {io = 0;}
							break;
						default:
							opçãoInvalida(scanner);
							break;
					}
				}
				catch (InputMismatchException e) {
					scanner.nextLine();
					opçãoInvalida(scanner);
				}
			}

	        while (true) {
				try {
					limparTela();
	            	System.out.println("\nEscolha uma opção:");
	            	System.out.println("1 - Loja");
	            	System.out.println("2 - Comprar jogo");
	            	System.out.println("3 - Sua biblioteca");
	            	System.out.println("4 - Excluir conta");
	            	System.out.println("5 - Sair");
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
										System.out.printf("\nDigite o id do jogo que queira comprar (digite 0 para voltar): ");
										int jogo = scanner.nextInt();
										scanner.nextLine();
										if (jogo == 0) {
											break;
										}
										comprarJogo(conexao, scanner, jogo);
										break;
									}
									catch (InvalidIdExeption e) {
										limparTela();
										System.out.println("Digite um id válido.");
										System.out.println("Pressione Enter para continuar...");
										scanner.nextLine();
									}
									catch (InputMismatchException e) {
										scanner.nextLine();
										limparTela();
										System.out.println("Digite um id válido.");
										System.out.println("Pressione Enter para continuar...");
										scanner.nextLine();
									}
									catch (AlreadyPurchasedGameExeption e) {
										limparTela();
										System.out.println(e.getMessage());
										System.out.println("Pressione Enter para continuar...");
										scanner.nextLine();
									}
								}
								break;
						case 3:
							limparTela();
							mostrarBiblioteca(conexao);
	            	        break;
	            	    case 4:
							if (deletarConta(conexao, scanner)) {
								limparTela();
								System.out.println("Saindo...");
								return;
							}
							limparTela();
	            	        break;
	            	    case 5:
	            	        System.out.println("Saindo...");
	            	        return;
	            	    default:
							opçãoInvalida(scanner);
							break;
	            	}
				}
				catch (InputMismatchException e) {
					scanner.nextLine();
					opçãoInvalida(scanner);
				}
	        }
        } 
		catch (SQLException e) {
            System.out.println("Erro ao conectar ao banco de dados: " + e.getMessage());
        }
	}




	
    private static boolean criarConta(Connection conexao, Scanner scanner) throws SQLException, IOException, InterruptedException {
		System.out.printf("\nDigite um nome de usuário: ");
		String nome = scanner.nextLine().trim();
		System.out.printf("\nDigite uma senha: ");
		String senha = scanner.nextLine().trim();
		if (nome.isEmpty() || senha.isEmpty() || nome.contains(" ") || senha.contains(" ") || senha.length() < 8) {
			limparTela();
			System.out.println("Nome de usuário indisponível");
			System.out.println("Pressione Enter para continuar...");
			scanner.nextLine();
			limparTela();
			return false;
		}
        String sql = "INSERT INTO jogador (nome, senha) VALUES (?, ?)";
		String sql2 = "SELECT * FROM jogador WHERE nome = '" + nome + "'";
        try (PreparedStatement stmt2 = conexao.prepareStatement(sql2); ResultSet rs = stmt2.executeQuery()) {
			while (rs.next()) {
				limparTela();
				System.out.println("Nome de usuário indisponível");
				System.out.println("Pressione Enter para continuar...");
				scanner.nextLine();
				limparTela();
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
				limparTela();
				System.out.println("Conta criada com sucesso!\n");
				System.out.println("Pressione Enter para continuar...");
				scanner.nextLine();
				limparTela();
				return true;
			}
		}
    }




	private static void mostrarLoja(Connection conexao, Scanner scanner) throws SQLException {
        String sql = "SELECT * FROM jogos";
        try (PreparedStatement stmt = conexao.prepareStatement(sql); ResultSet rs = stmt.executeQuery()) {
            while (rs.next()) {
                System.out.printf("%s - %s\n", rs.getString("id"),rs.getString("nome"));
            }
        }
		System.out.println("Pressione Enter para sair...");
		scanner.nextLine();
    }
	



	@SuppressWarnings("resource")
	private static void mostrarBiblioteca(Connection conexao) throws SQLException {
		String sql = "SELECT * FROM public.jogos, public.jogador_jogos WHERE jogador_jogos.id_jogador = " + userID + " AND jogos.id = jogador_jogos.id_jogo";
		try (PreparedStatement stmt = conexao.prepareStatement(sql); ResultSet rs = stmt.executeQuery()) {
			System.out.println("Sua biblioteca: ");
			while (rs.next()) {
				System.out.println(rs.getString("nome"));
			}
			System.out.println("\nPressione Enter para continuar...");
			new Scanner(System.in).nextLine();
		}
	}
    


	private static void comprarJogo(Connection conexao, Scanner scanner, int jogo) throws SQLException, IOException, InterruptedException, InvalidIdExeption, AlreadyPurchasedGameExeption {
		String sql3 = "SELECT * FROM jogador_jogos WHERE id_jogador = "+ userID + " AND id_jogo = "+ jogo;
		String sql2 = "INSERT INTO jogador_jogos (id_jogo, id_jogador) VALUES (?, ?)";
		String sql = "SELECT * FROM jogos WHERE id = " + jogo;
		try (PreparedStatement stmt3 = conexao.prepareStatement(sql3); ResultSet rs2 = stmt3.executeQuery()) {
			if (rs2.next()) {
				throw new AlreadyPurchasedGameExeption("Jogo selecionado ja está disponivel em sua conta");
			}
		}
		try (PreparedStatement stmt = conexao.prepareStatement(sql); ResultSet rs = stmt.executeQuery();) {
			if (!rs.next()) {
				throw new InvalidIdExeption("Erro: ID inválido");
			}
			try (PreparedStatement stmt2 = conexao.prepareStatement(sql2)) {
				limparTela();
				stmt2.setInt(1, jogo);
				stmt2.setInt(2, userID);
				stmt2.executeUpdate();
				System.out.println("Jogo adcionado a sua biblioteca.");
				System.out.println("Pressione Enter para continuar...");
				scanner.nextLine();
			}
		}
	}

	private static boolean deletarConta(Connection conexao, Scanner scanner) throws SQLException, IOException, InterruptedException {
		String sql = "DELETE FROM jogador_jogos WHERE id_jogador = "+ userID + "; DELETE FROM jogador WHERE id = " + userID;
		limparTela();
		while (true) {
			System.out.println("Você tem certeza que quer deletar sua conta? (y/n)");
			String resposta = scanner.nextLine();
			switch (resposta) {
				case "y","Y":
					try (PreparedStatement stmt = conexao.prepareStatement(sql)) {
						stmt.executeUpdate();
						System.out.println("Alma libertada");
					}
					return true;
				case "n","N":
					return false;
				default:
					opçãoInvalida(scanner);
					break;
			}
		}
	}


	private static boolean login(Connection conexao, String nome, String senha, Scanner scanner) throws SQLException, IOException, InterruptedException {
		String sql = "SELECT * FROM jogador WHERE nome = '" + nome + "' AND senha = '" + senha + "'";
		try (PreparedStatement stmt = conexao.prepareStatement(sql); ResultSet rs = stmt.executeQuery()) {
			while (rs.next()) {
				userID = rs.getInt("id");
			}
		}
		if (userID == 0) {
			limparTela();
			System.out.println("Nome ou senha errados.\n");
			System.out.println("Pressione Enter para continuar...");
			scanner.nextLine();
			limparTela();
			return false;
		}
		else {
			limparTela();
			System.out.println("Conta Logada");
			System.out.println("Pressione Enter para continuar...");
			scanner.nextLine();
			limparTela();
			return true;
		}
	}

	private static void opçãoInvalida(Scanner scanner) throws IOException, InterruptedException{
		limparTela();
		System.out.println("Digite uma opção válida.");
		System.out.println("Pressione Enter para continuar...");
		scanner.nextLine();
		limparTela();
	}
	private static void limparTela() throws IOException, InterruptedException {
		new ProcessBuilder("cmd", "/c", "cls").inheritIO().start().waitFor();
	}
}