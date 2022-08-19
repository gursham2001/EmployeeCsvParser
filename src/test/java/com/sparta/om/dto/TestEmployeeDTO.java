package com.sparta.om.dto;

import com.sparta.om.dao.EmployeeDAO;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import java.util.ArrayList;

public class TestEmployeeDTO {
    public static EmployeeDTO firstEmployee;
    public static ArrayList<EmployeeDTO> validatedEmployees;
    public static ArrayList<EmployeeDTO> corruptedEmployees;
    public static ArrayList<EmployeeDTO> duplicatedEmployees;


    public static EmployeeDTO firstCorruptEmployee;

    @BeforeAll
    public static void setUp(){
        validatedEmployees = EmployeeDAO.PopulateArray("src/test/resources/EmployeeRecordsTest.csv");
        corruptedEmployees = EmployeeDAO.getCorruptedEmployees();
        duplicatedEmployees = EmployeeDAO.getDuplicatedEmployees();
        firstEmployee = validatedEmployees.get(0);
    }

    @Test
    public void testNamePrefixIsValid(){
        EmployeeDTO corruptedPrefixEmployee = corruptedEmployees.get(4);

        Assertions.assertTrue(firstEmployee.isPrefixValid());
        Assertions.assertFalse(corruptedPrefixEmployee.isPrefixValid());
    }

    @Test
    public void testDateIsValid(){
        EmployeeDTO corruptedDOBEmployee = corruptedEmployees.get(2);
        EmployeeDTO corruptedDOJEmployee = corruptedEmployees.get(1);

        Assertions.assertTrue(firstEmployee.isDateOfBirthValid());
        Assertions.assertTrue(firstEmployee.isDateOfJoiningValid());
        Assertions.assertFalse(corruptedDOBEmployee.isDateOfBirthValid());
        Assertions.assertFalse(corruptedDOJEmployee.isDateOfJoiningValid());
    }

    @Test
    public void testSalaryIsValid(){
        EmployeeDTO corruptedSalaryEmployee = corruptedEmployees.get(3);

        Assertions.assertTrue(firstEmployee.isSalaryValid());
        Assertions.assertFalse(corruptedSalaryEmployee.isSalaryValid());
    }

    @Test
    public void testGenderIsValid(){
        EmployeeDTO corruptGenderEmployee = corruptedEmployees.get(0);

        Assertions.assertTrue(firstEmployee.isGenderValid());
        Assertions.assertFalse(corruptGenderEmployee.isGenderValid());
    }

    @ParameterizedTest
    @ValueSource(ints = {0,1,2,3,4})
    public void testIsRecordValid(int index) {
        Assertions.assertTrue(validatedEmployees.get(index).isRecordValid());
        Assertions.assertFalse(corruptedEmployees.get(index).isRecordValid());
    }






}
