package com.sparta.om.dao;

import com.sparta.om.dto.EmployeeDTO;
import com.sparta.om.logging.CustomFormatter;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.logging.FileHandler;
import java.util.logging.Level;

//Data Access Object
//CRUD
public class EmployeeDAO {
    private static final java.util.logging.Logger logger = java.util.logging.Logger.getLogger("EmployeeDaoLogger");
    public static ArrayList<EmployeeDTO> employeesLarge = new ArrayList<>();
    private static ArrayList<EmployeeDTO> employees = new ArrayList<>();
    private static ArrayList<Integer> employeeIDs = new ArrayList<>();
    public static ArrayList<EmployeeDTO> corruptedEmployees = new ArrayList<>();
    public static ArrayList<EmployeeDTO> duplicatedEmployees = new ArrayList<>();
    private static BufferedReader bufferedReader;

    public static ArrayList<EmployeeDTO> PopulateArray(String filename) {
        try {
            try {
                FileHandler fileHandler = new FileHandler("src/main/resources/employeeDaoLogger.log", true);
                fileHandler.setFormatter(new CustomFormatter());
                fileHandler.setLevel(Level.ALL);
                logger.setUseParentHandlers(false);
                logger.addHandler(fileHandler);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }

            var fileReader = new FileReader(filename);
            var bufferedReader = new BufferedReader(fileReader);
            bufferedReader.readLine();
            for (String line = bufferedReader.readLine(); line != null; line = bufferedReader.readLine()) {
                String[] records = line.split(",");
                EmployeeDTO employeeDTO = new EmployeeDTO(records);
                if (employeeDTO.isRecordValid()) {
                    if (employeeDTO.isDuplicate(employeeDTO, employeeIDs)) {
                        duplicatedEmployees.add(employeeDTO);
                    } else {
                        employees.add(employeeDTO);
                        employeeIDs.add(employeeDTO.getEmplID());
                    }
                } else {
                    corruptedEmployees.add(employeeDTO);
                }
            }
            logger.log(Level.INFO, "Finished populating array");
        } catch (IOException e) {
            e.printStackTrace();
        }
        return employees;
    }

    public static ArrayList<EmployeeDTO> PopulateArrayLarge(String filename) {
        try {
            var fileReader = new FileReader(filename);
            var bufferedReader = new BufferedReader(fileReader);
            bufferedReader.readLine();
            for (String line = bufferedReader.readLine(); line != null; line = bufferedReader.readLine()) {
                String[] records = line.split(",");
                EmployeeDTO employeeDTO = new EmployeeDTO(records);
                employeesLarge.add(employeeDTO);
            }
            logger.log(Level.FINE, "Finished populating large array");
        } catch (IOException e) {
            e.printStackTrace();
        }
        return employeesLarge;
    }

    public static ArrayList<EmployeeDTO> getEmployeesLarge() {
        return employeesLarge;
    }

    public static ArrayList<EmployeeDTO> getEmployees() {
        return employees;
    }

    public static ArrayList<EmployeeDTO> getCorruptedEmployees() {
        return corruptedEmployees;
    }

    public static ArrayList<EmployeeDTO> getDuplicatedEmployees() {
        return duplicatedEmployees;
    }
}
