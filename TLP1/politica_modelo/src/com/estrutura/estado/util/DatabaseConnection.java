package com.estrutura.estado.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseConnection {
	private static final String URL = "jdbc:postgresql://localhost:5432/politica";
    private static final String USUARIO = "postgres";
    private static final String SENHA = "postgres";
    
    public static Connection getConnection() throws SQLException {
		return DriverManager.getConnection(URL, USUARIO, SENHA);
    }
}
