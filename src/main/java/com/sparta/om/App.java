package com.sparta.om;

import com.sparta.om.DB.controller.ConnectionManager;
import com.sparta.om.DB.controller.DBController;
import com.sparta.om.DB.controller.DBLoader;
import com.sparta.om.dao.EmployeeDAO;
import com.sparta.om.dto.EmployeeDTO;

import java.sql.*;
import java.util.ArrayList;

public class App 
{
    public static void main(String[] args) {
        DBLoader.start();

        System.out.println("Corrupted employees ################## \n \n \n" + EmployeeDAO.getDuplicatedEmployees() + "\n FINISHED");
    }
}
