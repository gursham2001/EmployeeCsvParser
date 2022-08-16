package com.sparta.om.DB.controller;

import com.sparta.om.DB.util.PropertiesLoader;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionManager {
    private static Connection postGresConnection;

    public static Connection connectToDB() {
        String url = PropertiesLoader.getProperty("url");
        String username = PropertiesLoader.getProperty("userName");
        String password = PropertiesLoader.getProperty("passWord");

        try {
            postGresConnection = DriverManager.getConnection(url, username, password);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return postGresConnection;
    }

    public static void closeConnection() {
        try {
            postGresConnection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
