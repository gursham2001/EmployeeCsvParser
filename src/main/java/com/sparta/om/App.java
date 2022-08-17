package com.sparta.om;

import com.sparta.om.DB.controller.ConnectionManager;
import com.sparta.om.DB.controller.DBController;
import com.sparta.om.dao.EmployeeDAO;
import com.sparta.om.dto.EmployeeDTO;

import java.sql.*;
import java.util.ArrayList;

public class App 
{
    public static void main(String[] args) {
        Connection postgresConnection = ConnectionManager.connectToDB();
        DBController userDAO = new DBController(postgresConnection);
        ArrayList<EmployeeDTO> employees = EmployeeDAO.PopulateArray("src/main/resources/EmployeeRecords.csv");

        // userDAO.dropTable();
        userDAO.createTable();
        userDAO.insertUsersToTable("src/main/resources/EmployeeRecords.csv");
    }
}
