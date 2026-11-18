package br.com.starlog.config;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/** Ponto unico de configuracao de conexoes SQLite. */
public final class ConexaoFactory {
    private static final String URL_PADRAO = "jdbc:sqlite:starlog.db";

    private ConexaoFactory() { }

    public static Connection obterConexao() throws SQLException {
        // Opcional: -Dstarlog.jdbc.url=jdbc:sqlite:outro-arquivo.db
        String url = System.getProperty("starlog.jdbc.url", URL_PADRAO);
        Connection conn = DriverManager.getConnection(url);
        try (var stmt = conn.createStatement()) {
            stmt.execute("PRAGMA foreign_keys = ON");
        } catch (SQLException e) {
            conn.close();
            throw e;
        }
        return conn;
    }
}
