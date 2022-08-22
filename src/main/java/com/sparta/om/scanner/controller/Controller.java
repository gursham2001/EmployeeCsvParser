package com.sparta.om.scanner.controller;

import com.sparta.om.db.controller.ConnectionManager;
import com.sparta.om.db.controller.DBController;

import java.sql.Connection;
import java.util.Scanner;

public class Controller {
    static Connection postgresConnection = ConnectionManager.connectToDB();
    static DBController dbController = new DBController(postgresConnection);

    public static void ScannerController(int i){

        String filename = "src/main/resources/EmployeeRecords.csv";

        switch(i) {
            case 1:
                dbController.dropTable();
                dbController.createTable();
                System.out.println("Created the Table...");
                break;
            case 2:
                if(dbController.doesTableExist()) {
                    dbController.insertUsersToTable(filename);
                    System.out.println("Inserted Employees ...");
                }else{
                    System.out.println("Table does not exist");
                }
                break;
            case 3:
                dbController.getEmployee(SelectedEmployee());
                break;
            case 4:
                dbController.dropTable();
                break;
            default:
                System.out.println("you did not select a valid option");
                System.exit(1);
        }
    }

    public static int SelectedEmployee(){
        Scanner myObj = new Scanner(System.in);
        System.out.println("Please enter the employee ID :");

        return myObj.nextInt();
    }
}
