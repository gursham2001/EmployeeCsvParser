package com.sparta.om.DB.controller;

import com.sparta.om.dao.EmployeeDAO;
import com.sparta.om.dto.EmployeeDTO;

import java.sql.Connection;
import java.util.ArrayList;

public class DBLoader {
    public static void start() {
        Connection postgresConnection = ConnectionManager.connectToDB();
        DBController dbController = new DBController(postgresConnection);

        ArrayList<EmployeeDTO> employees = EmployeeDAO.PopulateArray("src/main/resources/EmployeeRecords.csv");

        dbController.dropTable();
        dbController.createTable();
        dbController.insertUsersToTable("src/main/resources/EmployeeRecords.csv");
        //dbController.printAllUsers();
    }
}
