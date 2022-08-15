package com.sparta.om.dao;


import com.sparta.om.dto.EmployeeDTO;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.lang.reflect.Array;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

public class TestEmployeeDAO {
    public static EmployeeDTO firstEmployee;

    @BeforeAll
    public static void setUp(){
        firstEmployee = EmployeeDAO.PopulateArray("src/test/resources/TestExample.csv").get(0);
    }

    @Test
    public void testPopulateArray(){
        Assertions.assertNotNull(EmployeeDAO.PopulateArray("src/test/resources/TestExample.csv"));
    }

    @Test
    public void testGetEmployees(){

        Assertions.assertEquals("198429",firstEmployee.getEmplID());
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

    @Test
    public void testNamePrefixIsValid(){
        Assertions.assertTrue(firstEmployee.isPrefixValid());
    }

    @Test
    public void testDateIsValid(){
        Assertions.assertTrue(firstEmployee.isDateOfBirthValid());
        Assertions.assertTrue(firstEmployee.isDateOfJoiningValid());
    }

    @Test
    public void testSalaryIsValid(){
        Assertions.assertTrue(firstEmployee.isSalaryValid());
    }

    @Test
    public void testGenderIsValid(){
        Assertions.assertTrue(firstEmployee.isGenderValid());
    }




}
