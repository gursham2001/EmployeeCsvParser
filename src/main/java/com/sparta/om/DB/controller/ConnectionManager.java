package com.sparta.om.DB.controller;

import com.sparta.om.DB.util.PropertiesLoader;
import com.sparta.om.logging.CustomFormatter;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.logging.FileHandler;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ConnectionManager {
    private static final Logger logger = Logger.getLogger("connectionManager");

    private static Connection postgresConnection;

    public static Connection connectToDB() {
        try {
            FileHandler fileHandler = new FileHandler("src/main/resources/connectionManagerLogger.log", true);
            fileHandler.setFormatter(new CustomFormatter());
            fileHandler.setLevel(Level.ALL);
            logger.setUseParentHandlers(false);
            logger.addHandler(fileHandler);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        String url = PropertiesLoader.getProperty("url");
        String username = PropertiesLoader.getProperty("username");
        String password = PropertiesLoader.getProperty("password");
        logger.log(Level.FINE, "Creating connection with specified variables");
        try {
            postgresConnection = DriverManager.getConnection(url, username, password);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        logger.log(Level.INFO, "Returning connection..");
        return postgresConnection;
    }

    public static void closeConnection() {
        try {
            logger.log(Level.INFO, "Closing connection");
            postgresConnection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
