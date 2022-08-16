package com.sparta.om.DB.model;

import com.sparta.om.dao.EmployeeDAO;
import com.sparta.om.dto.EmployeeDTO;

import java.sql.*;
import java.util.ArrayList;

public class UserDAO {
    private final Connection postgresConnection;

    private Statement statement;

    public UserDAO(Connection postgresConnection) {
        this.postgresConnection = postgresConnection;
        try {
            statement = postgresConnection.createStatement();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void printAllUsers() {
        try {
            ResultSet resultSet = statement.executeQuery(SQLQueries.SELECT_ALL);
            while(resultSet.next()) {
                System.out.println(resultSet.getInt(1));
                System.out.println(resultSet.getString(2));
                System.out.println(resultSet.getString(3));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void insertUsersToTable(String filename) {
        ArrayList<EmployeeDTO> validatedEmployees = EmployeeDAO.PopulateArray(filename);
        for (int i = 0; i < validatedEmployees.size(); i++){
            EmployeeDTO records = validatedEmployees.get(i);
            System.out.println(records.returnSQLReady());
            for (int j = 1; j < 10; j++) {
                // change 10 to current count
                try {
                    PreparedStatement preparedStatement = postgresConnection.prepareStatement(SQLQueries.INSERT_INTO_TABLE);
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
