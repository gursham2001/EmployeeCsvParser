package com.sparta.om.db.controller;

import java.sql.Connection;

public class DBLoader {
    public static void start() {
        Connection postgresConnection = ConnectionManager.connectToDB();
        DBController dbController = new DBController(postgresConnection);

        System.out.println("hi");
        //ArrayList<EmployeeDTO> employees = EmployeeDAO.PopulateArray("src/main/resources/EmployeeRecordsTest.csv");

        dbController.dropTable();
        dbController.createTable();
        dbController.insertUsersToTable("src/main/resources/EmployeeRecords.csv");
        //dbController.printAllUsers();
        dbController.getEmployee( 953724);
    }
}
