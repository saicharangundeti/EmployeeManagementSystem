package com.EmployeeManagementSystem.model;

public class Employee {
    private String employeeId;
    private String employeeName;
    private String employeePhoneNo;
    private String employeeDesignation;

    public Employee() {
    }


    public Employee(String employeeId, String employeeName, String employeePhoneNo, String employeeDesignation) {
        this.employeeId = employeeId;
        this.employeeName = employeeName;
        this.employeePhoneNo = employeePhoneNo;
        this.employeeDesignation = employeeDesignation;
    }

    public String getEmployeeId() {
        return employeeId;
    }

    public void setEmployeeId(String employeeId) {
        this.employeeId = employeeId;
    }

    public void setEmployeeName(String employeeName) {
        this.employeeName = employeeName;
    }

    public void setEmployeePhoneNo(String employeePhoneNo) {
        this.employeePhoneNo = employeePhoneNo;
    }

    public void setEmployeeDesignation(String employeeDesignation) {
        this.employeeDesignation = employeeDesignation;
    }


    public String getEmployeeName() {
        return employeeName;
    }

    public String getEmployeePhoneNo() {
        return employeePhoneNo;
    }
    public String getEmployeeDesignation() {
        return employeeDesignation;
    }
}
