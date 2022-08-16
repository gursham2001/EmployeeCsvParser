package com.sparta.om;

import com.sparta.om.DB.controller.ConnectionManager;
import com.sparta.om.DB.model.UserDAO;
import com.sparta.om.dao.EmployeeDAO;

import java.sql.*;

public class App 
{
    public static void main(String[] args) {
        Connection postgresConnection = ConnectionManager.connectToDB();
        UserDAO userDAO = new UserDAO(postgresConnection);

        userDAO.printAllUsers();
    }
}
