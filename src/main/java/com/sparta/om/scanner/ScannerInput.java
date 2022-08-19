package com.sparta.om.scanner;

import com.sparta.om.logging.CustomFormatter;
import com.sparta.om.scanner.controller.Controller;

import java.io.IOException;
import java.util.Scanner;
import java.util.logging.FileHandler;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ScannerInput {
    private static final Logger logger = Logger.getLogger("Scanner Input Logger");

    public static void ScannerSort() {
        try {
            FileHandler fileHandler = new FileHandler("src/main/resources/ScannerInputLogger.log", true);
            fileHandler.setFormatter(new CustomFormatter());
            fileHandler.setLevel(Level.ALL);
            logger.setUseParentHandlers(false);
            logger.addHandler(fileHandler);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        Scanner myObj = new Scanner(System.in);
        int choice = 0;

        while (true) {
            logger.log(Level.FINER, "Printing menu for user..");
            System.out.println("\nWelcome to Employee csv Importer");
            System.out.println("What would you like to do ?");
            System.out.println("1 : Create Table");
            System.out.println("2 : Insert Employees into database");
            System.out.println("3 : Select an Employee");
            System.out.println("4 : Delete the Table");
            System.out.println("5 : Exit Application");
            System.out.print("Enter your choice: ");
            choice = myObj.nextInt();


            if(choice == 5){
                logger.log(Level.INFO, "User ended the program");
                break;
            } else {
                Controller.ScannerController(choice);
            }
        }
    }

}
