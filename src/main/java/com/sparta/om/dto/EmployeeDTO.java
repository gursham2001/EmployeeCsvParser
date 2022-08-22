package com.sparta.om.dto;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static java.lang.Integer.parseInt;

public class EmployeeDTO {

    private int emplID;
    private String namePrefix;
    private String firstName;
    private String middleInitial;
    private String lastName;
    private String gender;
    private String email;
    private LocalDate dateOfBirth;
    private LocalDate dateOfJoining;
    private int salary;

    public EmployeeDTO(String[] csvInput) {
        this.emplID = parseInt(csvInput[0]);
        this.namePrefix = csvInput[1];
        this.firstName = csvInput[2];
        this.middleInitial = csvInput[3];
        this.lastName = csvInput[4];
        this.gender = csvInput[5];
        this.email = csvInput[6];
        this.dateOfBirth = LocalDate.parse(csvInput[7], DateTimeFormatter.ofPattern("M/d/uuuu"));
        this.dateOfJoining = LocalDate.parse(csvInput[8], DateTimeFormatter.ofPattern("M/d/uuuu"));
        this.salary = parseInt(csvInput[9]);
    }

    public int getEmplID() {
        return emplID;
    }

    public String getNamePrefix() {
        return namePrefix;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getMiddleInitial() {
        return middleInitial;
    }

    public String getLastName() {
        return lastName;
    }

    public String getGender() {
        return gender;
    }

    public String getEmail() {
        return email;
    }

    public LocalDate getDateOfBirth() {
        return dateOfBirth;
    }

    public LocalDate getDateOfJoining() {
        return dateOfJoining;
    }

    public int getSalary() {
        return salary;
    }

    public boolean isPrefixValid() {
        return getNamePrefix().equals("Mr.")
                || getNamePrefix().equals("Mrs.")
                || getNamePrefix().equals("Hon.")
                || getNamePrefix().equals("Dr.")
                || getNamePrefix().equals("Prof.")
                || getNamePrefix().equals("Drs.")
                || getNamePrefix().equals("Ms.");
    }

    public boolean isDateOfBirthValid() {
        return getDateOfBirth().isBefore(LocalDate.now()) && getDateOfBirth().isBefore(getDateOfJoining()) ;
    }

    public boolean isDateOfJoiningValid() {
        return getDateOfJoining().isBefore(LocalDate.now());
    }

    public boolean isSalaryValid() {
        return getSalary() > 0;
    }

    public boolean isGenderValid() {
        return getGender().equals("M") || getGender().equals("F");
    }

    public boolean isRecordValid(){
        return isGenderValid() && isDateOfBirthValid() && isDateOfJoiningValid() && isPrefixValid() && isSalaryValid();
    }

    @Override
    public String toString() {
        return "EmployeeDTO{" +
                "emplID='" + emplID + '\'' +
                ", namePrefix='" + namePrefix + '\'' +
                ", firstName='" + firstName + '\'' +
                ", middleInitial='" + middleInitial + '\'' +
                ", lastName='" + lastName + '\'' +
                ", gender='" + gender + '\'' +
                ", email='" + email + '\'' +
                ", dateOfBirth=" + dateOfBirth +
                ", dateOfJoining=" + dateOfJoining +
                ", salary=" + salary +
                '}';
    }

    public boolean isDuplicate(EmployeeDTO employeeDTO, ArrayList<Integer> employeeIDs) {
        return employeeIDs.contains(employeeDTO.getEmplID());
    }
}

