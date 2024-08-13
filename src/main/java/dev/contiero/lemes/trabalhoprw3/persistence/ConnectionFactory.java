package dev.contiero.lemes.trabalhoprw3.persistence;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class ConnectionFactory {
    private static final String DATABASE_URL = "jdbc:h2:file:./data/banco";
    private static final int MAX_RETRIES = 5;

    static {
        try {
            Class.forName("org.sqlite.JDBC");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    public static Connection getConnection() throws SQLException {
        int retries = 0;
        while (true) {
            try {
                return DriverManager.getConnection(DATABASE_URL);
            } catch (SQLException e) {
                if (e.getMessage().contains("database is locked") && retries < MAX_RETRIES) {
                    retries++;
                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException ie) {
                        throw new SQLException("Interrupted while waiting for database lock to release", ie);
                    }
                } else {
                    throw e;
                }
            }
        }
    }

    public static PreparedStatement getPreparedStatement(String sql) throws SQLException {
        return getConnection().prepareStatement(sql);
    }
}


