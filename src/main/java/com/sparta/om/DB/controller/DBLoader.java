package com.sparta.om.DB.controller;

import com.sparta.om.logging.CustomFormatter;

import java.io.IOException;
import java.sql.Connection;
import java.util.logging.FileHandler;
import java.util.logging.Level;
import java.util.logging.Logger;

public class DBLoader {
    private static final Logger logger = Logger.getLogger("DB Loader Logger");

    public static void start() {
        try {
            FileHandler fileHandler = new FileHandler("src/main/resources/dbLoaderLogger.log", true);
            fileHandler.setFormatter(new CustomFormatter());
            fileHandler.setLevel(Level.ALL);
            logger.setUseParentHandlers(false);
            logger.addHandler(fileHandler);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        logger.log(Level.INFO, "Starting application..");
        Connection postgresConnection = ConnectionManager.connectToDB();
        DBController dbController = new DBController(postgresConnection);

        dbController.dropTable();
        dbController.createTable();
        dbController.insertUsersToTable("src/main/resources/EmployeeRecords.csv");
        //dbController.printAllUsers();
    }
}
