package com.sparta.om.dao;

import com.sparta.om.dto.EmployeeDTO;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;

//Data Access Object
//CRUD
public class EmployeeDAO {

    private static ArrayList<EmployeeDTO> employees = new ArrayList<>();
    private static ArrayList<EmployeeDTO> corruptedEmployees = new ArrayList<>();
    private static BufferedReader bufferedReader;

    public static ArrayList<EmployeeDTO> PopulateArray(String filename) {
        try {
            var fileReader = new FileReader(filename);
            var bufferedReader = new BufferedReader(fileReader);
            bufferedReader.readLine();
            for (String line = bufferedReader.readLine(); line != null; line = bufferedReader.readLine()) {
                String[] records = line.split(",");
                EmployeeDTO employeeDTO = new EmployeeDTO(records);
                if (employeeDTO.isRecordValid()) {
                    employees.add(employeeDTO);
                } else {
                    corruptedEmployees.add(employeeDTO);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return employees;
    }

    public static ArrayList<EmployeeDTO> getEmployees() {
        return employees;
    }

    public static ArrayList<EmployeeDTO> getCorruptedEmployees() {
        return corruptedEmployees;
    }
}
