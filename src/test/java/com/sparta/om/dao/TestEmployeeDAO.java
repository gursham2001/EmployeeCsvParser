package com.sparta.om.dao;


import com.sparta.om.dto.EmployeeDTO;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import java.lang.reflect.Array;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

public class TestEmployeeDAO {
    public static EmployeeDTO firstEmployee;

    @BeforeAll
    public static void setUp(){
        firstEmployee = EmployeeDAO.PopulateArray("src/test/resources/EmployeeRecordsTest.csv").get(0);
    }

    @Test
    public void testPopulateArray(){
        Assertions.assertNotNull(EmployeeDAO.PopulateArray("src/test/resources/EmployeeRecordsTest.csv"));
    }

    @Test
    public void testGetEmployees(){

        Assertions.assertEquals(198429,firstEmployee.getEmplID());
        Assertions.assertEquals("Mrs.",firstEmployee.getNamePrefix());
        Assertions.assertEquals("Serafina",firstEmployee.getFirstName());
        Assertions.assertEquals("I",firstEmployee.getMiddleInitial());
        Assertions.assertEquals("Bumgarner",firstEmployee.getLastName());
        Assertions.assertEquals("F",firstEmployee.getGender());
        Assertions.assertEquals("serafina.bumgarner@exxonmobil.com",firstEmployee.getEmail());
        Assertions.assertEquals(LocalDate.parse("9/21/1982", DateTimeFormatter.ofPattern("M/d/uuuu")),firstEmployee.getDateOfBirth());
        Assertions.assertEquals(LocalDate.parse("02/01/2008", DateTimeFormatter.ofPattern("M/d/uuuu")),firstEmployee.getDateOfJoining());
        Assertions.assertEquals(Float.valueOf("69294"),firstEmployee.getSalary());
    }
    @ParameterizedTest
    @ValueSource(ints = {0,1,2,3,4})
    public void testDuplicateEmployees(int index) {
        int validatedEmpID = EmployeeDAO.getEmployees().get(index).getEmplID();
        int duplicatedEmplID = EmployeeDAO.duplicatedEmployees.get(index).getEmplID();
        Assertions.assertEquals(validatedEmpID, duplicatedEmplID);
    }
    @Test
    public void testGetNumberOfValidatesEmployees(){
        int answer = EmployeeDAO.getNumberOfValidatedEmployees();
        int expected = 5;
        Assertions.assertEquals(expected,answer);
    }
    @Test
    public void testGetNumberOfCorruptedEmployees(){
        int answer = EmployeeDAO.getNumberOfCorruptedEmployees();
        int expected = 5;
        Assertions.assertEquals(expected,answer);
    }
    @Test
    public void testGetNumberOfDuplicatedEmployees(){
        int answer = EmployeeDAO.getNumberOfDuplicatedEmployees();
        int expected = 5;
        Assertions.assertEquals(expected,answer);
    }

}
