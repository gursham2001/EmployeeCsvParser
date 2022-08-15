package com.sparta.om;

import com.sparta.om.dao.EmployeeDAO;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args )
    {
        EmployeeDAO.PopulateArray("src/main/resources/EmployeeRecords.csv");
    }
}
