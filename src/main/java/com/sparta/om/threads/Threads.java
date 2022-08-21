package com.sparta.om.threads;

import com.sparta.om.db.controller.ConnectionManager;
import com.sparta.om.db.controller.DBController;
import com.sparta.om.db.model.SQLQueries;
import com.sparta.om.dao.EmployeeDAO;
import com.sparta.om.dto.EmployeeDTO;
import com.sparta.om.dto.util.Utilities;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;

public class Threads{
    private static int count = 0;
    static ArrayList<EmployeeDTO> employeesLarge = EmployeeDAO.PopulateArrayLarge("src/main/resources/EmployeeRecordsLarge.csv");
    static Connection connection = ConnectionManager.connectToDB();
    static DBController controller = new DBController(connection);

    public static ArrayList<Thread> threads = new ArrayList<>();

    public static void main(String[] args) {

        Thread thread1 = new Thread(new Runnable() {
            @Override
            public void run() {
                while (!Thread.currentThread().isInterrupted()) {
                    insertThread1();
                }
            }
        });
        Thread thread2 = new Thread(new Runnable() {
            @Override
            public void run() {
                while (!Thread.currentThread().isInterrupted()) {
                    insertThread2();
                }
            }
        });
        Thread thread3 = new Thread(new Runnable() {
            @Override
            public void run() {
                while (!Thread.currentThread().isInterrupted()) {
                    insertThread3();
                }
            }
        });

        controller.dropTable();
        controller.createTable();

        double start = System.nanoTime();
        thread1.start();
        thread2.start();
        thread3.start();
        while (thread1.isAlive() && thread2.isAlive() && thread3.isAlive()) {

        }
        double end = System.nanoTime();
        double totalTime = (end - start) / 1000000000;
        System.out.println("Total time taken for large array: " + totalTime + " seconds");

    }
/*
    public static void threadGen(int numOfThreads) {
        for (int i = 0; i < numOfThreads; i++) {
            threads.add(new Thread(new Threads()));
        }
    }

*/

    public static void insertThread1() {
            try {
                PreparedStatement preparedStatement = connection.prepareStatement(String.valueOf(SQLQueries.INSERT_INTO_TABLE));
                for (int i = 0; i < 21833; i++) {
                        EmployeeDTO record = employeesLarge.get(i);

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
                        preparedStatement.addBatch();
                    }
                preparedStatement.executeBatch();
                Thread.currentThread().interrupt();
            } catch (SQLException e) {
                System.out.println(count++);
            }
        }

    public static void insertThread2() {
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(String.valueOf(SQLQueries.INSERT_INTO_TABLE));
            for (int i = 21833; i < 43666; i++) {
                EmployeeDTO record = employeesLarge.get(i);

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
                preparedStatement.addBatch();
            }
            preparedStatement.executeBatch();
            Thread.currentThread().interrupt();
        } catch (SQLException e) {
            System.out.println(count++);
        }
    }
    public static void insertThread3() {
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(String.valueOf(SQLQueries.INSERT_INTO_TABLE));
            for (int i = 43666; i < employeesLarge.size(); i++) {
                EmployeeDTO record = employeesLarge.get(i);

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
                preparedStatement.addBatch();
            }
            preparedStatement.executeBatch();
            Thread.currentThread().interrupt();
        } catch (SQLException e) {
            System.out.println(count++);
        }
    }
}
