package com.EmployeeManagementSystem.controller;
import com.EmployeeManagementSystem.Service.EmployeeService;
import com.EmployeeManagementSystem.model.Employee;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
@RequestMapping("/api/v1")
@RestController
public class EmployeeController {
    EmployeeService employeeService;
    EmployeeController(EmployeeService employeeService){
        this.employeeService = employeeService;
    }

    @GetMapping("/employees/{employeeId}")
    public ResponseEntity<Employee> getEmployeeById(@PathVariable int employeeId) {
        Employee emp = employeeService.getEmployee(employeeId);
        if(emp == null){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
        return ResponseEntity.ok(emp);
    }

    @PostMapping("/employees")
    public ResponseEntity<Employee> postEmployee(@RequestBody Employee employee) {
        Employee emp = employeeService.saveEmployee(employee);
        if(emp != null){
            return ResponseEntity.ok(emp);
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);

    }
    @PutMapping("/employees/{employeeId}")
    public ResponseEntity<Employee> updateEmployee(@RequestBody Employee employee) {
        Employee updatedEmployee = employeeService.updateEmployee(employee);
        if(updatedEmployee == null){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
        return ResponseEntity.ok(updatedEmployee);

    }

    @DeleteMapping("/employees/{employeeId}")
    public ResponseEntity<Boolean> deleteEmployeeById(@PathVariable int employeeId) {
        boolean isEmployeePresent = employeeService.deleteEmployee(employeeId);
        if(isEmployeePresent){
            return ResponseEntity.ok(true);
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(false);
    }
}
