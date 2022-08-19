package com.sparta.om.threads;

import com.sparta.om.DB.controller.ConnectionManager;
import com.sparta.om.DB.controller.DBController;
import com.sparta.om.DB.model.SQLQueries;
import com.sparta.om.dao.EmployeeDAO;
import com.sparta.om.dto.EmployeeDTO;
import com.sparta.om.dto.util.Utilities;
import jdk.jshell.execution.Util;

import javax.sound.midi.Soundbank;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;

import static com.sparta.om.dao.EmployeeDAO.*;

public class Threads implements Runnable{
    private static int count = 0;
    static ArrayList<EmployeeDTO> employeesLarge = EmployeeDAO.PopulateArrayLarge("src/main/resources/EmployeeRecordsLarge.csv");
    static Connection connection = ConnectionManager.connectToDB();
    static DBController controller = new DBController(connection);

    public static ArrayList<Thread> threads = new ArrayList<>();

    public static int startIndex = 0;
    public static int endIndex = 0;

    @Override
    public void run() {
        System.out.println("Printing from a thread");
        updateIndexes(empLargeSplitter(3));
        System.out.println(empLargeSplitter(3));
        System.out.println(startIndex + " " + endIndex);
        insertThread(startIndex, endIndex);
    }

    public static void main(String[] args) {

        controller.dropTable();
        controller.createTable();

        threadGen(3);
        double start = System.nanoTime();
        for (int i = 0; i < 3; i++) {
            threads.get(i).start();
        }
//        while() {
//
//        }
        double end = System.nanoTime();

        double totalTime = (end - start) / 1000000;
        System.out.println(totalTime);
    }

    public static void threadGen(int numOfThreads) {
        for (int i = 0; i < numOfThreads; i++) {
            threads.add(new Thread(new Threads()));
        }
    }

    public static int empLargeSplitter(int amountOfThreads) {
//        if (EmployeeDAO.employeesLarge.size() % amountOfThreads == 0) {
           int employeesSplit = employeesLarge.size() / amountOfThreads;
           return employeesSplit;
//        }
    }

    public synchronized static void updateIndexes(int increment) {
        startIndex = endIndex;
        endIndex = endIndex + increment;
    }

    public void insertThread(int startIndex, int endIndex) {

        for (int i = startIndex; i < endIndex; i++) {
            EmployeeDTO record = employeesLarge.get(i);
            System.out.println(Thread.currentThread().getName() + " " + i);
            try {
                PreparedStatement preparedStatement = connection.prepareStatement(SQLQueries.INSERT_INTO_TABLE);
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
                System.out.println(count++);
                //e.printStackTrace();
            }
        }
    }

}
