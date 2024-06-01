package com.EmployeeManagementSystem.Repository;
import com.EmployeeManagementSystem.model.Employee;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.Map;

@Repository
public class EmployeeRepository {
    private Map<Integer, Employee> employeeMap = new HashMap<>();

    public Employee getEmployeeById(int id){
        if(employeeMap.containsKey(id)){
            return employeeMap.get(id);
        }
        return null;
    }
    public Employee postEmployee(Employee employee){
        if(employeeMap.containsKey(employee.getEmployeeId()) || employee.getEmployeeId() < 1){
            return null;
        }
        employeeMap.put(employee.getEmployeeId(),employee);
        return employee;
    }

    public Employee updateEmployee(Employee emp){
        if(!employeeMap.containsKey(emp.getEmployeeId())){
            return null;
        }
        Employee updateEmployee = employeeMap.get(emp.getEmployeeId());
        updateEmployee.setEmployeeName(emp.getEmployeeName());
        updateEmployee.setEmployeeDesignation(emp.getEmployeeDesignation());
        updateEmployee.setEmployeePhoneNo(emp.getEmployeePhoneNo());
        employeeMap.put(emp.getEmployeeId(),updateEmployee);
        return updateEmployee;
    }
    public boolean deleteEmployee(int id){
        if(!employeeMap.containsKey(id)){
            return false;
        }
        employeeMap.remove(id);
        return true;
    }
}
