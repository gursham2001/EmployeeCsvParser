package com.sparta.om.dto;

//Data Transfer Object - DTO
// Emp ID, name prefix, First Name, Middle initial, Last Name, Gender, E mail, Date of Birth, Date of Joining, Salary
//Read as String - Convert getters

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class EmployeeDTO {

    private String emplID;
    private String namePrefix;
    private String firstName;
    private String middleInitial;
    private String lastName;
    private String gender;
    private String email;
    private LocalDate dateOfBirth;
    private LocalDate dateOfJoining;
    private Float salary;

    public EmployeeDTO(String[] csvInput) {
        this.emplID = csvInput[0];
        this.namePrefix = csvInput[1];
        this.firstName = csvInput[2];
        this.middleInitial = csvInput[3];
        this.lastName = csvInput[4];
        this.gender = csvInput[5];
        this.email = csvInput[6];
        this.dateOfBirth = LocalDate.parse(csvInput[7], DateTimeFormatter.ofPattern("M/d/uuuu"));
        this.dateOfJoining = LocalDate.parse(csvInput[8], DateTimeFormatter.ofPattern("M/d/uuuu"));
        this.salary = Float.valueOf(csvInput[9]);
    }

    public String getEmplID() {
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

    public Float getSalary() {
        return salary;
    }

    public boolean isPrefixValid() {

        return false;
    }

    public boolean isDateOfBirthValid() {
        return false;
    }

    public boolean isDateOfJoiningValid() {
        return false;
    }

    public boolean isSalaryValid() {
        return false;
    }

    public boolean isGenderValid() {
        return false;
    }
    public boolean isRecordValid(){

        return isGenderValid() && isDateOfBirthValid() && isDateOfJoiningValid() && isPrefixValid() && isPrefixValid();
    }
}

