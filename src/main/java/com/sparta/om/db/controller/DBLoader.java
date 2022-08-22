package com.sparta.om.db.controller;

import java.sql.Connection;

public class DBLoader {
    public static void start() {
        Connection postgresConnection = ConnectionManager.connectToDB();
        DBController dbController = new DBController(postgresConnection);
        dbController.dropTable();
        dbController.createTable();
        dbController.insertUsersToTable("src/main/resources/EmployeeRecords.csv");
        dbController.getEmployee( 953724);
    }
}
