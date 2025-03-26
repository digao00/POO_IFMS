
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

public class SteamApp {
	
	private static String user;
	public static void main(String[] args) {
		// Configurações de conexão com o banco de dados
        String url = "jdbc:postgresql://localhost:5432/SteamBanco";
        String usuario = "postgres";
        String senha = "postgres"; //F101 = "postgresql" F103 = "postgres"
		
		 // Objeto Scanner para entrada de dados do usuário
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

				switch (opcao) {
					case 1:
						if (criarConta(conexao, scanner)) {
							System.out.println("Conta criada com sucesso!\n");
							io = 0;
						}
						break;
					case 2:
						System.out.printf("\nNome: ");
						String nomeLogin = scanner.nextLine();
						System.out.printf("\nSenha: ");
						String senhaLogin = scanner.nextLine();
			
						if (login(conexao, nomeLogin, senhaLogin)) {
							io = 0;
						}
						else {
							System.out.println("Nome ou senha errados.\n");
						}
						break;
					default:
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
        } catch (SQLException e) {
            System.out.println("Erro ao conectar ao banco de dados: " + e.getMessage());
        }
	}
	
    private static boolean criarConta(Connection conexao, Scanner scanner) throws SQLException {
		System.out.println("\nDigite um nome de usuário:");
		String nome = scanner.nextLine();
		System.out.println("\nDigite uma senha:");
		String senha = scanner.nextLine();
        String sql = "INSERT INTO jogador (nome, senha) VALUES (?, ?)";
		String sql2 = "SELECT * FROM jogador WHERE nome = '" + nome + "'";
        try (PreparedStatement stmt = conexao.prepareStatement(sql2); ResultSet rs = stmt.executeQuery()) {
			while (rs.next()) {
					System.out.println("Nome de usuário indisponível");
					return false;
				}
			try (PreparedStatement stmt2 = conexao.prepareStatement(sql)) {
					stmt2.setString(1, nome);
        			stmt2.setString(2, senha);
        			stmt2.executeUpdate();
					user = nome;
					return true;
			}
		}
    }

    private static void mostrarLoja(Connection conexao) throws SQLException {
        String sql = "SELECT * FROM conta";
        try (
        	PreparedStatement stmt = conexao.prepareStatement(sql);
            ResultSet rs = stmt.executeQuery()) {
            while (rs.next()) {
                System.out.println("ID: " + rs.getInt("id") + 
                                 ", Nome: " + rs.getString("nome") + 
                                 ", Saldo: " + rs.getDouble("saldo"));
            }
        }
    }
	
	private static void mostrarBiblioteca(Connection conexao) throws SQLException {

	}
    
	private static void comprarJogo(Connection conexao, Scanner scanner) throws SQLException {

	}

	private static boolean login(Connection conexao, String nome, String senha) throws SQLException {
		String sql = "SELECT * FROM jogador WHERE nome = '" + nome + "' AND senha = '" + senha + "'";
		try (PreparedStatement stmt = conexao.prepareStatement(sql); ResultSet rs = stmt.executeQuery()) {
			while (rs.next()) {
				user = rs.getString("nome");
			}
		}
		if (user == null) {
			return false;
		}
		else {
			return true;
		}
	}
}
