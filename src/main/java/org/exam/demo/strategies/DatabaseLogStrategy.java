package org.exam.demo.strategies;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class DatabaseLogStrategy implements LogStrategy {
    private final String jdbcUrl;
    private final String username;
    private final String password;
    private final String tableName;

    public DatabaseLogStrategy(String jdbcUrl, String username, String password, String tableName) {
        this.jdbcUrl = jdbcUrl;
        this.username = username;
        this.password = password;
        this.tableName = tableName;
    }

    @Override
    public void writeLog(String message) {
        String sql = "INSERT INTO " + tableName + " (message, timestamp) VALUES (?, CURRENT_TIMESTAMP)";

        try (Connection connection = DriverManager.getConnection(jdbcUrl, username, password);
             PreparedStatement preparedStatement = connection.prepareStatement(sql)) {

            preparedStatement.setString(1, message);
            preparedStatement.executeUpdate();
            System.out.println("Log saved to table " + tableName + ": " + message);

        } catch (SQLException e) {
            System.err.println("Error saving log to database: " + e.getMessage());
        }
    }
}
