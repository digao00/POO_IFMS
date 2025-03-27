
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;
import java.io.IOException;

public class SteamApp {
	
	private static String userID;
	public static void main(String[] args) throws IOException, InterruptedException {
		// Configurações de conexão com o banco de dados
        String url = "jdbc:postgresql://localhost:5432/Steam";
        String usuario = "postgres";
        String senha = "postgres"; //F101 = "postgresql" F103 = "postgres"
        Scanner scanner = new Scanner(System.in);
        
        try (Connection conexao = DriverManager.getConnection(url, usuario, senha)) {
            System.out.println("Conexão com o banco de dados estabelecida!");
			int io = 1;
			while (io == 1) {
				System.out.println("\n###### Steam ######");
				System.out.println("1 - Criar conta");
				System.out.println("2 - Login");
				int opcao = scanner.nextInt();
				scanner.nextLine();
				new ProcessBuilder("cmd", "/c", "cls").inheritIO().start().waitFor();

				switch (opcao) {
					case 1:
						if (criarConta(conexao, scanner)) {io = 0;}
						break;
					case 2:
						System.out.printf("\nNome: ");
						String nomeLogin = scanner.nextLine();
						System.out.printf("\nSenha: ");
						String senhaLogin = scanner.nextLine();
						if (login(conexao, nomeLogin, senhaLogin)) {io = 0;}
						break;
					default:
						new ProcessBuilder("cmd", "/c", "cls").inheritIO().start().waitFor();
						break;
				}
			}

	        while (true) {
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
	                	mostrarLoja(conexao);
						new ProcessBuilder("cmd", "/c", "cls").inheritIO().start().waitFor();
	                    break;
					case 2:
						comprarJogo(conexao, scanner);
	                    break;
					case 3:
						mostrarBiblioteca(conexao);
	                    break;
	                case 4:
	                	System.out.println("Alma libertada");
	                    break;
	                case 5:
	                    System.out.println("Saindo...");
	                    return;
	                default:
	                    System.out.println("Opção inválida!");
	            }
	        }
        } 
		catch (SQLException e) {
            System.out.println("Erro ao conectar ao banco de dados: " + e.getMessage());
        }
	}




	
    private static boolean criarConta(Connection conexao, Scanner scanner) throws SQLException, IOException, InterruptedException {
		System.out.println("\nDigite um nome de usuário:");
		String nome = scanner.nextLine();
		System.out.println("\nDigite uma senha:");
		String senha = scanner.nextLine();
        String sql = "INSERT INTO jogador (nome, senha) VALUES (?, ?)";
		String sql2 = "SELECT * FROM jogador WHERE nome = '" + nome + "'";
        try (PreparedStatement stmt2 = conexao.prepareStatement(sql2); ResultSet rs = stmt2.executeQuery()) {
			while (rs.next()) {
				new ProcessBuilder("cmd", "/c", "cls").inheritIO().start().waitFor();
				System.out.println("Nome de usuário indisponível");
				System.out.println("Pressione Enter para continuar...");
				scanner.nextLine();
				new ProcessBuilder("cmd", "/c", "cls").inheritIO().start().waitFor();
				return false;
			}
			try (PreparedStatement stmt = conexao.prepareStatement(sql)) {
				stmt.setString(1, nome);
        		stmt.setString(2, senha);
        		stmt.executeUpdate();	 
				try (ResultSet rs2 = stmt2.executeQuery()) {
					while (rs2.next()) {
						userID = rs2.getString("id");
					}
				}
				new ProcessBuilder("cmd", "/c", "cls").inheritIO().start().waitFor();
				System.out.println("Conta criada com sucesso!\n");
				System.out.println("Pressione Enter para continuar...");
				scanner.nextLine();
				new ProcessBuilder("cmd", "/c", "cls").inheritIO().start().waitFor();
				return true;
			}
		}
    }

    private static void mostrarLoja(Connection conexao) throws SQLException {
        String sql = "SELECT * FROM jogos";
        try (PreparedStatement stmt = conexao.prepareStatement(sql); ResultSet rs = stmt.executeQuery()) {
            while (rs.next()) {
				int i = 1;
                System.out.println(i + " - " + rs.getString("nome"));
				i++;
            }
        }
		System.out.println("Pressione Enter para sair...");
		@SuppressWarnings("resource")
		Scanner pause = new Scanner(System.in);
		pause.nextLine();
    }
	
	private static void mostrarBiblioteca(Connection conexao) throws SQLException {

	}
    
	private static void comprarJogo(Connection conexao, Scanner scanner) throws SQLException {

	}

	private static boolean login(Connection conexao, String nome, String senha) throws SQLException, IOException, InterruptedException {
		@SuppressWarnings("resource")
		Scanner scanner = new Scanner(System.in);
		String sql = "SELECT * FROM jogador WHERE nome = '" + nome + "' AND senha = '" + senha + "'";
		try (PreparedStatement stmt = conexao.prepareStatement(sql); ResultSet rs = stmt.executeQuery()) {
			while (rs.next()) {
				userID = rs.getString("id");
			}
		}
		if (userID == null) {
			new ProcessBuilder("cmd", "/c", "cls").inheritIO().start().waitFor();
			System.out.println("Nome ou senha errados.\n");
			System.out.println("Pressione Enter para continuar...");
			scanner.nextLine();
			new ProcessBuilder("cmd", "/c", "cls").inheritIO().start().waitFor();
			return false;
		}
		else {
			new ProcessBuilder("cmd", "/c", "cls").inheritIO().start().waitFor();
			System.out.println("Conta Logada");
			System.out.println("Pressione Enter para continuar...");
			scanner.nextLine();
			new ProcessBuilder("cmd", "/c", "cls").inheritIO().start().waitFor();
			return true;
		}
	}
}
