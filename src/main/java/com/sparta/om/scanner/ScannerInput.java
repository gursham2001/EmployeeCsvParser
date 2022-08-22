package com.sparta.om.scanner;

import com.sparta.om.scanner.controller.Controller;

import java.util.Scanner;
public class ScannerInput {

    public static void ScannerSort() {
        Scanner myObj = new Scanner(System.in);
        int choice = 0;

        while (true) {
            System.out.println("\n \nWelcome to Employee csv Importer");
            System.out.println("What would you like to do ?");
            System.out.println("1 : Create Table");
            System.out.println("2 : Insert Employees into database");
            System.out.println("3 : Select an Employee");
            System.out.println("4 : Delete the Table");
            System.out.println("5 : Exit Application");
            System.out.print("Enter your choice: ");
            choice = myObj.nextInt();

            if(choice == 5){
                break;
            } else {
                Controller.ScannerController(choice);
            }
        }
    }
}
