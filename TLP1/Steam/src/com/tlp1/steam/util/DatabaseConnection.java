package com.tlp1.steam.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseConnection {
    private static final String url = "jdbc:postgresql://localhost:5432/Steam";
    private static final String usuario = "postgres";
    private static final String senha = "postgres"; // F101 = "postgresql" F103 = "postgres"

    public static Connection getConnection() throws SQLException {
        return DriverManager.getConnection(url, usuario, senha);
    }
}
