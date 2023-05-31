package com.match.calculator;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseConfig {
    private final Connection connection;

    public DatabaseConfig() throws ClassNotFoundException, SQLException {
        Class.forName("com.mysql.cj.jdbc.Driver");
        String url = System.getProperty("spring.datasource.url");
        String user = System.getProperty("spring.datasource.username");
        String password = System.getProperty("spring.datasource.password");
        this.connection = DriverManager.getConnection(url, user, password);
    }

    public Connection getConnection() {
        return connection;
    }
}

