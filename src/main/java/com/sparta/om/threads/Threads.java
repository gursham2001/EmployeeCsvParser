package com.sparta.om.threads;

import com.sparta.om.DB.controller.ConnectionManager;
import com.sparta.om.DB.controller.DBController;
import com.sparta.om.DB.model.SQLQueries;
import com.sparta.om.dao.EmployeeDAO;
import com.sparta.om.dto.EmployeeDTO;
import com.sparta.om.dto.util.Utilities;
import jdk.jshell.execution.Util;

import javax.print.DocFlavor;
import javax.sound.midi.Soundbank;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import static com.sparta.om.dao.EmployeeDAO.*;

public class Threads implements Runnable{
    private static int count = 0;
    static ArrayList<EmployeeDTO> employeesLarge = EmployeeDAO.PopulateArrayLarge("src/main/resources/EmployeeRecordsLarge.csv");
    static Connection connection = ConnectionManager.connectToDB();
    static DBController controller = new DBController(connection);

    public static ArrayList<Thread> threads = new ArrayList<>();

    public static int currentIndex = 0;

    @Override
    public void run() {
        double start = System.nanoTime();

        while(!Thread.currentThread().isInterrupted()) {
            insertThread();
        }
        double end = System.nanoTime();
        double totalTime = (end - start) / 1000000;
        System.out.println(totalTime);

    }

    public static void main(String[] args) {

        controller.dropTable();
        controller.createTable();

        threadGen(1000);
        double start = System.nanoTime();
        for (int i = 0; i < 1000; i++) {
            threads.get(i).start();
        }



    }

    public static void threadGen(int numOfThreads) {
        for (int i = 0; i < numOfThreads; i++) {
            threads.add(new Thread(new Threads()));
        }
    }

    public void insertThread() {
        if(currentIndex >= employeesLarge.size()){
            Thread.currentThread().interrupt();

        }else {

            System.out.println(Thread.currentThread().getName() + " " + currentIndex);
            try {
                for (int i = 0; i <= 4; i++) {
                    SQLQueries.INSERT_MULTIPLE.append(", (?, ?, ?, ?, ?, ?, ?, ?, ?, ?)");
                }

                PreparedStatement preparedStatement = connection.prepareStatement(String.valueOf(SQLQueries.INSERT_MULTIPLE));
                for (int i = 0; i <= 4; i++) {
                    EmployeeDTO record = employeesLarge.get(currentIndex);
                    currentIndex++;

                    preparedStatement.setInt((10 * i) + 1, record.getEmplID());
                    preparedStatement.setString((10 * i) + 2, record.getNamePrefix());
                    preparedStatement.setString((10 * i) + 3, record.getFirstName());
                    preparedStatement.setString((10 * i) + 4, record.getMiddleInitial());
                    preparedStatement.setString((10 * i) + 5, record.getLastName());
                    preparedStatement.setString((10 * i) + 6, record.getGender());
                    preparedStatement.setString((10 * i) + 7, record.getEmail());
                    preparedStatement.setDate((10 * i) + 8, Utilities.DateConverter(record.getDateOfBirth()));
                    preparedStatement.setDate((10 * i) + 9, Utilities.DateConverter(record.getDateOfJoining()));
                    preparedStatement.setInt((10 * i) + 10, record.getSalary());
                }
                preparedStatement.execute();

            } catch (SQLException e) {
                System.out.println(count++);
                //e.printStackTrace();
            }
        }
    }
}
