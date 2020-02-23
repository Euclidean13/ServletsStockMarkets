package com.app.repository.db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBConnectionManager {

    private Connection connection;

    public DBConnectionManager(String dbURL, String user, String pwd) {
        try {
            this.connection = DriverManager.getConnection(dbURL, user, pwd);
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    public Connection getConnection() {
        return connection;
    }
}
