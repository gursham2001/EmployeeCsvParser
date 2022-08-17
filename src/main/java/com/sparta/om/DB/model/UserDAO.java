package com.sparta.om.DB.model;

import com.sparta.om.dao.EmployeeDAO;
import com.sparta.om.dto.EmployeeDTO;

import java.sql.*;
import java.util.ArrayList;

import static com.sparta.om.dto.util.Utilities.*;

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
            EmployeeDTO record = validatedEmployees.get(i);
            System.out.println(record.returnSQLReady());
                // change 10 to current count
                try {
                    PreparedStatement preparedStatement = postgresConnection.prepareStatement(SQLQueries.INSERT_INTO_TABLE);
                    preparedStatement.setString(1,record.getEmplID());
                    preparedStatement.setString(2,record.getNamePrefix());
                    preparedStatement.setString(3,record.getFirstName());
                    preparedStatement.setString(4,record.getMiddleInitial());
                    preparedStatement.setString(5,record.getLastName());
                    preparedStatement.setString(6,record.getGender());
                    preparedStatement.setString(7,record.getEmail());
                    preparedStatement.setString(8,DateConverter(record.getDateOfBirth()));
                    preparedStatement.setString(9,DateConverter(record.getDateOfJoining()));
                    preparedStatement.setInt(10,record.getSalary());

                } catch (SQLException e) {
                    e.printStackTrace();
                }

        }
    }
}
