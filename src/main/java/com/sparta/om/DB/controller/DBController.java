package com.sparta.om.DB.controller;

import com.sparta.om.DB.model.SQLQueries;
import com.sparta.om.dao.EmployeeDAO;
import com.sparta.om.dto.EmployeeDTO;
import com.sparta.om.dto.util.Utilities;
import com.sparta.om.logging.CustomFormatter;

import java.io.IOException;
import java.sql.*;
import java.util.ArrayList;
import java.util.logging.FileHandler;
import java.util.logging.Level;
import java.util.logging.Logger;

import static com.sparta.om.dao.EmployeeDAO.corruptedEmployees;
import static com.sparta.om.dao.EmployeeDAO.duplicatedEmployees;

public class DBController {
    private static final Logger logger = Logger.getLogger("DB Controller Logger");

    private final Connection postgresConnection;
    private Statement statement;

    public DBController(Connection postgresConnection) {
        try {
            FileHandler fileHandler = new FileHandler("src/main/resources/DBControllerLogger.log", true);
            fileHandler.setFormatter(new CustomFormatter());
            fileHandler.setLevel(Level.ALL);
            logger.setUseParentHandlers(false);
            logger.addHandler(fileHandler);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        logger.log(Level.INFO, "Starting Connection...");
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
            while (resultSet.next()) {
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
        logger.log(Level.FINE, "Printing general info about the CSV");
        System.out.println("Valid employees count: " + validatedEmployees.size());
        System.out.println("Corrupted employees count: " + corruptedEmployees.size());
        System.out.println("Duplicated employees count: " + duplicatedEmployees.size());

        for (int i = 0; i < validatedEmployees.size(); i++) {
            EmployeeDTO record = validatedEmployees.get(i);
            try {
                PreparedStatement preparedStatement = postgresConnection.prepareStatement(SQLQueries.INSERT_INTO_TABLE);
                preparedStatement.setInt(1, record.getEmplID());
                preparedStatement.setString(2, record.getNamePrefix());
                preparedStatement.setString(3, record.getFirstName());
                preparedStatement.setString(4, record.getMiddleInitial());
                preparedStatement.setString(5, record.getLastName());
                preparedStatement.setString(6, record.getGender());
                preparedStatement.setString(7, record.getEmail());
                preparedStatement.setDate(8, Utilities.DateConverter(record.getDateOfBirth()));
                preparedStatement.setDate(9, Utilities.DateConverter(record.getDateOfJoining()));
                preparedStatement.setInt(10, record.getSalary());
                preparedStatement.execute();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        logger.log(Level.INFO, "Finished inserting into table");
    }

    public void dropTable() {
        try {
            logger.log(Level.FINE, "Dropping table...");
            PreparedStatement preparedStatement = postgresConnection.prepareStatement(SQLQueries.DROP_TABLE);
            preparedStatement.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void createTable() {
        try {
            logger.log(Level.FINE, "Creating table...");
            PreparedStatement preparedStatement = postgresConnection.prepareStatement(SQLQueries.CREATE_TABLE);
            preparedStatement.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    public void getEmployee(int id){
        try {
            PreparedStatement preparedStatement = postgresConnection.prepareStatement(SQLQueries.SELECT_INDIVIDUAL);
            preparedStatement.setInt(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();
            logger.log(Level.FINE, "Printing some info about the queried employee");
            while (resultSet.next()) {
                System.out.print("ID: " + resultSet.getInt( 1)+ " \n");
                System.out.print("Name: " + resultSet.getString(2) + " ");
                System.out.print(resultSet.getString(3)+ " ");
                System.out.print(resultSet.getString(4)+ " ");
                System.out.print(resultSet.getString(5)+ " \n" );
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public boolean doesTableExist(){
        logger.log(Level.INFO, "Checking to see if table exists");
        PreparedStatement prepareStatement = null;
        boolean result = false;
        try {
            prepareStatement = postgresConnection.prepareStatement(SQLQueries.CHECK_TABLE);

            ResultSet resultSet = prepareStatement.executeQuery();
            while(resultSet.next()) {
               result = resultSet.getBoolean(1);
            }
           return result;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }
}
