package com.amalitech.db;

import com.amalitech.exceptions.DatabaseUnavailableException;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseService {

    private final String url = "jdbc:mysql://invalid-host:3306/demo";
    private final String user = "root";
    private final String pass = "root";

    public Connection connect() throws SQLException {
        return DriverManager.getConnection(url, user, pass);
    }

    public Connection safeConnect() {
        try {
            return connect();
        } catch (SQLException e) {
            throw new DatabaseUnavailableException("Unable to connect to DB", e);
        }
    }
}
