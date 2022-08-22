package com.sparta.om.db.controller;

import com.sparta.om.db.util.PropertiesLoader;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionManager {
    private static Connection postgresConnection;

    public static Connection connectToDB() {
        String url = PropertiesLoader.getProperty("url");
        String username = PropertiesLoader.getProperty("username");
        String password = PropertiesLoader.getProperty("password");

        try {
            postgresConnection = DriverManager.getConnection(url, username, password);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return postgresConnection;
    }

    public static void closeConnection() {
        try {
            postgresConnection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
