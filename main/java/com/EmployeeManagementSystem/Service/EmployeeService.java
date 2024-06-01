package com.EmployeeManagementSystem.Service;

import com.EmployeeManagementSystem.Repository.EmployeeRepository;
import com.EmployeeManagementSystem.model.Employee;
import org.springframework.stereotype.Service;

@Service
public class EmployeeService {
    private EmployeeRepository employeeRepository;
    EmployeeService(EmployeeRepository employeeRepository){
        this.employeeRepository = employeeRepository;
    }

    public Employee getEmployee(int employeeId){
        Employee emp = employeeRepository.getEmployeeById(employeeId);
        if(emp != null){
            return emp;
        }
        return null;
    }

    public Employee saveEmployee(Employee employee){
        Employee emp = employeeRepository.postEmployee(employee);
        if(emp != null) return  emp;
        return  null;
    }
    public Employee updateEmployee(Employee employee){
        Employee emp = employeeRepository.updateEmployee(employee);
        if(emp != null) return emp;
        return null;
    }
    public boolean deleteEmployee(int id){
        boolean isExist = employeeRepository.deleteEmployee(id);
        if(isExist) return true;
        return  false;
    }

}
