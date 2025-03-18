package bancoTLP1;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

public class Conta {
	public static void main(String[] args) {
		// Configurações de conexão com o banco de dados
        String url = "jdbc:postgresql://localhost:5432/BancoApp";
        String usuario = "postgres";
        String senha = "postgres";
		
		 // Objeto Scanner para entrada de dados do usuário
        Scanner scanner = new Scanner(System.in);
        
        try (Connection conexao = DriverManager.getConnection(url, usuario, senha)) {
            System.out.println("Conexão com o banco de dados estabelecida!");
        
	        // Menu de opções
	        while (true) {
	            System.out.println("\nEscolha uma opção:");
	            System.out.println("1 - Criar conta");
	            System.out.println("2 - Listar contas");
	            System.out.println("3 - Depositar");
	            System.out.println("4 - Sacar");
	            System.out.println("5 - Excluir conta");
	            System.out.println("6 - Sair");
	            int opcao = scanner.nextInt();
	            scanner.nextLine(); // Consumir a nova linha
	
	            switch (opcao) {
	                case 1:
	                	criarConta(conexao, scanner);
	                    break;
	                case 2:
	                	 listarContas(conexao);
	                    break;
	                case 3:
	                	System.out.println("Depósito realizado");
	                    break;
	                case 4:
	                	System.out.println("Saque realizado");
	                    break;
	                case 5:
	                	System.out.println("Conta excluída");
	                    break;
	                case 6:
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
	
	// Método para criar uma nova conta
    private static void criarConta(Connection conexao, Scanner scanner) throws SQLException {
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

    
    // Método para listar todas as contas
    private static void listarContas(Connection conexao) throws SQLException {
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
    
}
