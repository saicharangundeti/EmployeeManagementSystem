package com.EmployeeManagementSystem.controller;

import com.EmployeeManagementSystem.model.Employee;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.*;
@RequestMapping("/api/v1")
@RestController
public class EmployeeController {
    Map<String, Employee> map = new HashMap<>();


    @GetMapping("/greet")
    public String greet_message() {
        return "Hello this is my employee api";
    }

    @GetMapping("/employees")
    public ArrayList<Employee> getAllEmployees() {
        ArrayList<Employee> vals = new ArrayList<>(map.values());
        return vals;

    }
    @GetMapping("/employees/by-name")
    public ResponseEntity<Employee> getEmployeeByName(@RequestParam(value = "name") String employeeName) {
        for (Employee emp : map.values()) {
            if (emp.getEmployeeName().equals(employeeName)) {
                return ResponseEntity.ok(emp);
            }
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
    }


    @GetMapping("/employees/{employeeId}")
    public ResponseEntity<Employee> getEmployeeById(@PathVariable String employeeId) {
        if(!map.containsKey(employeeId)){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
        return (ResponseEntity<Employee>) ResponseEntity.ok(map.get(employeeId));
    }

    @PostMapping("/employees")
    public ResponseEntity<Employee> postEmployee(@RequestBody Employee employee) {
        if(employee.getEmployeeId()!= null && !map.containsKey(employee.getEmployeeId())) {
            map.put(employee.getEmployeeId(), employee);
            return ResponseEntity.ok(employee);
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
    }
    @PutMapping("/employees/{employeeId}")
    public ResponseEntity<Employee> updateEmployee(@RequestBody Employee updatedEmployee) {
        Employee update = null;
        if(!map.containsKey(updatedEmployee.getEmployeeId())){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
        update = map.get(updatedEmployee.getEmployeeId());
        update.setEmployeeDesignation(updatedEmployee.getEmployeeDesignation());
        update.setEmployeeId(updatedEmployee.getEmployeeId());
        update.setEmployeeName(updatedEmployee.getEmployeeName());
        update.setEmployeePhoneNo(updatedEmployee.getEmployeePhoneNo());
        map.put(update.getEmployeeId(),update);
        return ResponseEntity.ok(update);

    }
    @DeleteMapping("/employees/by-name")
    public ResponseEntity<Boolean> deleteEmployeeByName(@RequestParam(value = "name") String employeeName){
        for(Employee emp : map.values()){
            if(emp.getEmployeeName().equals(employeeName)){
                map.remove(emp.getEmployeeId(),emp);
                return ResponseEntity.ok(true);
            }
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(false);
    }
    @DeleteMapping("/employees/{employeeId}")
    public ResponseEntity<Boolean> deleteEmployeeById(@PathVariable String employeeId) {
        if(!map.containsKey(employeeId)) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(false);
        }
        map.remove(employeeId);
        return ResponseEntity.ok(true);
    }
}