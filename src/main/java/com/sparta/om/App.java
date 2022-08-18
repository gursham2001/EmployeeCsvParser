package com.sparta.om;

import com.sparta.om.DB.controller.ConnectionManager;
import com.sparta.om.DB.controller.DBController;
import com.sparta.om.DB.controller.DBLoader;
import com.sparta.om.dao.EmployeeDAO;
import com.sparta.om.dto.EmployeeDTO;
import com.sparta.om.scanner.ScannerInput;

import java.sql.*;
import java.util.ArrayList;

public class App 
{
    public static void main(String[] args) {
        //DBLoader.start();
        ScannerInput.ScannerSort();
//        System.out.println("Normal Employees: " + EmployeeDAO.getEmployees().size() + "\n\nCorrupted employees: " + EmployeeDAO.getCorruptedEmployees().size() + " " + EmployeeDAO.getCorruptedEmployees()+
//                "\n\nDuplicated Employees: " +EmployeeDAO.getDuplicatedEmployees().size() + EmployeeDAO.getDuplicatedEmployees().get(50) + "\n FINISHED");
    }
}
