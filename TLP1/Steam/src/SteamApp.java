
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
        String url = "jdbc:postgresql://localhost:5432/Steam";
        String usuario = "postgres";
        String senha = "postgresql";
		
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
						criarConta(conexao, scanner);
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
							System.out.println("Nome ou senha errados.");
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
	            scanner.nextLine(); // Consumir a nova linha
	
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
	
    private static void comprarJogo(Connection conexao, Scanner scanner) throws SQLException {
        System.out.println("Digite o nome do titular da conta:");
        String nome = scanner.nextLine();

        String sql = "INSERT INTO conta (nome, saldo) VALUES (?, ?)";
        try (PreparedStatement stmt = conexao.prepareStatement(sql)) {
            stmt.setString(1, nome);
            stmt.setDouble(2, 0.0); // Saldo inicial zero
            stmt.executeUpdate();
            System.out.println("Conta criada com sucesso!");
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
    
	private static void criarConta(Connection conexao, Scanner scanner) throws SQLException {

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
