package com.sparta.om;

import com.sparta.om.DB.controller.ConnectionManager;
import com.sparta.om.DB.model.UserDAO;
import com.sparta.om.dao.EmployeeDAO;
import com.sparta.om.dto.EmployeeDTO;

import java.sql.*;
import java.util.ArrayList;
import java.util.Arrays;

public class App 
{
    public static void main(String[] args) {
        Connection postgresConnection = ConnectionManager.connectToDB();
        UserDAO userDAO = new UserDAO(postgresConnection);
        ArrayList<EmployeeDTO> employees = EmployeeDAO.PopulateArray("src/main/resources/EmployeeRecords.csv");
        System.out.println(employees.get(0).toString());

        userDAO.insertUsersToTable("src/main/resources/EmployeeRecords.csv");

    }
}
