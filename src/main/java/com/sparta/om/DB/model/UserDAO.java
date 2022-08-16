package com.sparta.om.DB.model;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

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
}
