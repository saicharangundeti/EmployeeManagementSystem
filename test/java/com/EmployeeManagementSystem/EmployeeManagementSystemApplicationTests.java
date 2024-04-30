package com.EmployeeManagementSystem;

import com.EmployeeManagementSystem.controller.EmployeeController;
import com.EmployeeManagementSystem.model.Employee;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class EmployeeManagementSystemApplicationTests {


	EmployeeController employeeController = new EmployeeController();
	ResponseEntity<Employee> emp = employeeController.postEmployee(new Employee("I1","sai","28282","Manager"));

	@Test
	public void testGetEmployeeById_ReturnsOkResponseCode() {
		ResponseEntity<Employee> response = employeeController.getEmployeeById("I1");
		assertEquals(HttpStatus.OK, response.getStatusCode());

	}

	@Test void testGetEmployeeById_ReturnsNotFoundResponseCode(){
		ResponseEntity<Employee> response= employeeController.getEmployeeById("I2");
		assertEquals(HttpStatus.NOT_FOUND,response.getStatusCode());
	}

	@Test void testGetEmployeeById_ReturnsCorrectEmployee(){
		ResponseEntity<Employee> response = employeeController.getEmployeeById("I1");
		assertNotNull(response.getBody(),"Employee Should not be null");
		assertEquals("sai",response.getBody().getEmployeeName());
	}

	@Test
	public void testGetAllEmployees_ReturnsOkResponseCode(){
		ResponseEntity<ArrayList<Employee>> response = employeeController.getAllEmployees(null);
			assertEquals(HttpStatus.OK,response.getStatusCode());
	}
	// non existing name
	@Test
	public void testGetAllEmployees_ReturnsNotFoundResponseCode(){
		ResponseEntity<ArrayList<Employee>> response = employeeController.getAllEmployees("charan");
		assertEquals(HttpStatus.NOT_FOUND,response.getStatusCode());
	}
	@Test
	public void testGetAllEmployees_ReturnsValidEmployeesSize(){
		ResponseEntity<Employee> emp1 = employeeController.postEmployee(new Employee("I2","sai","28282","Manager"));
		ResponseEntity<Employee> emp2 = employeeController.postEmployee(new Employee("I3","charan","28282","Manager"));
		ResponseEntity<ArrayList<Employee>> response = employeeController.getAllEmployees("charan");
		assertEquals(2,response.getBody().size());
	}
	@Test
	public void testGetAllEmployees_ReturnsInvalidEmployeesSize(){
		ResponseEntity<Employee> emp1 = employeeController.postEmployee(new Employee("I2","sai","28282","Manager"));
		ResponseEntity<Employee> emp2 = employeeController.postEmployee(new Employee("I3","charan","28282","Manager"));
		ResponseEntity<ArrayList<Employee>> response = employeeController.getAllEmployees("charan");
		assertNotEquals(2,response.getBody().size());
	}
	@Test
	public  void testAddEmployee_ReturnsCorrectEmployee(){
		ResponseEntity<Employee> newEmp = employeeController.postEmployee(new Employee("I2","charan","7847","CEO"));
		assertNotNull(newEmp.getBody().getEmployeeId(),"Employee Should not be null");
		assertEquals("charan",newEmp.getBody().getEmployeeName());
		assertEquals("I2",newEmp.getBody().getEmployeeId());
		assertEquals("7847",newEmp.getBody().getEmployeePhoneNo());
		assertEquals("CEO",newEmp.getBody().getEmployeeDesignation());

	}

	@Test
	public void testUpdateEmployee_ReturnsOkResponseCode(){
		ResponseEntity<Employee> newEmp = employeeController.postEmployee(new Employee("I2","charan","7847","CEO"));
		assertEquals(HttpStatus.OK,newEmp.getStatusCode());
	}


	@Test
	public void testUpdateEmployee_ReturnsNotFoundResponseCode(){
		ResponseEntity<Employee> newEmp = employeeController.postEmployee(new Employee(null,"charan","7847","CEO"));
		assertEquals(HttpStatus.NOT_FOUND,newEmp.getStatusCode());
	}
	@Test
	public void testUpdateEmployee_ReturnsOkStatusCode(){
		ResponseEntity<Employee> response = employeeController.updateEmployee(new Employee("I1","charan","57577","eco"));
		assertEquals(HttpStatus.OK,response.getStatusCode());

	}

	@Test
	public void testUpdateEmployee_ReturnsValidEmlpoyee(){
		ResponseEntity<Employee> response = employeeController.updateEmployee(new Employee("I1","charan","57577","eco"));
		assertEquals("charan",response.getBody().getEmployeeName());

	}

	@Test
	public  void testDeleteEmployeeByID_ReturnsOkStatusCode() {
		ResponseEntity<Boolean> result = employeeController.deleteEmployeeById("I1");
		assertEquals(HttpStatus.OK,result.getStatusCode());
	}


	@Test
	public  void testDeleteEmployeeById_ReturnsNotFoundStatusCode() {
		ResponseEntity<Boolean> result = employeeController.deleteEmployeeById("I2");
		assertEquals(HttpStatus.NOT_FOUND,result.getStatusCode());
	}


	@Test
	public void testDeleteEmployeeById_ReturnsValidId(){
		ResponseEntity<Boolean> response = employeeController.deleteEmployeeById("I1");
		assertEquals(true,response.getBody());

	}


	@Test
	public void testDeleteEmployeeById_ReturnsInValidId(){
		ResponseEntity<Boolean> response = employeeController.deleteEmployeeById(null);
		assertEquals(false,response.getBody());
	}


	@Test
	public void testDeleteEmployeeByName_ReturnsOkStatus(){
		ResponseEntity<Boolean> response = employeeController.deleteEmployeeByName("sai");
		assertEquals(response.getBody(),true);
	}

	@Test
	public void testDeleteEmployeeByName_ReturnsNotFoundStatus(){
		ResponseEntity<Boolean> response = employeeController.deleteEmployeeByName("charan");
		assertEquals(response.getBody(),false);
	}

}
