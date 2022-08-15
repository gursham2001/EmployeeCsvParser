package com.sparta.om.dao;

import com.sparta.om.dto.EmployeeDTO;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

//Data Access Object
//CRUD
public class EmployeeDAO {
    private static ArrayList<EmployeeDTO> employees = new ArrayList<>();
    private static BufferedReader bufferedReader;

    public static void PopulateArray(String filename) {
        try {
            var fileReader = new FileReader("src/main/resources/EmployeeRecords.csv");
            var bufferedReader = new BufferedReader(fileReader);
            bufferedReader.readLine();
            for (String line = bufferedReader.readLine(); line != null; line = bufferedReader.readLine()) {
                String[] records = line.split(",");
                EmployeeDTO employeeDTO = new EmployeeDTO(records);
                employees.add(employeeDTO);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static ArrayList<EmployeeDTO> getEmployees() {
        return employees;
    }
}
