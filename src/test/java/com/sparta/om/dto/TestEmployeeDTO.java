package com.sparta.om.dto;

import com.sparta.om.dao.EmployeeDAO;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

public class TestEmployeeDTO {
    public static EmployeeDTO firstEmployee;
    public static EmployeeDTO corruptEmployee;

    @BeforeAll
    public static void setUp(){
        EmployeeDAO.PopulateArray("src/test/resources/TestExample.csv");

        firstEmployee = EmployeeDAO.getEmployees().get(0);

        corruptEmployee = EmployeeDAO.getCorruptedEmployees().get(0);
    }

    @Test
    public void testNamePrefixIsValid(){
        Assertions.assertTrue(firstEmployee.isPrefixValid());

        Assertions.assertFalse(corruptEmployee.isPrefixValid());
    }

    @Test
    public void testDateIsValid(){
        Assertions.assertTrue(firstEmployee.isDateOfBirthValid());
        Assertions.assertTrue(firstEmployee.isDateOfJoiningValid());

        Assertions.assertFalse(corruptEmployee.isDateOfBirthValid());
        Assertions.assertFalse(corruptEmployee.isDateOfJoiningValid());
    }

    @Test
    public void testSalaryIsValid(){
        Assertions.assertTrue(firstEmployee.isSalaryValid());

        Assertions.assertFalse(corruptEmployee.isSalaryValid());
    }

    @Test
    public void testGenderIsValid(){
        Assertions.assertTrue(firstEmployee.isGenderValid());

        Assertions.assertFalse(corruptEmployee.isGenderValid());
    }

    @Test
    public void testIsRecordValid() {
        Assertions.assertTrue(firstEmployee.isRecordValid());

        Assertions.assertFalse(corruptEmployee.isRecordValid());
    }

}
