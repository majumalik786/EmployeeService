package com.example.demo.resource;

import java.util.List;

import javax.websocket.server.PathParam;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.example.demo.model.Employee;
import com.example.demo.service.EmployeeService;

@RestController
@RequestMapping("/employee")
public class EmployeeResource {

	@Autowired
	private EmployeeService service;

	@PostMapping
	public ResponseEntity<String> createEmployee(@RequestBody Employee employee) {

		return service.createEmployee(employee);
	}

	@GetMapping
	public List<Employee> getAllEmployee() {

		return service.getAllEmployee();
	}
	
	@GetMapping(value = "/{id}")
	public ResponseEntity<Employee> getEmployeeById(@PathVariable ("id") int id){
		
		return service.getEmployeeById(id);
	}
	
	@PutMapping(value = "/{id}")
	public ResponseEntity<String> updateEmployee(@RequestBody Employee employee,@PathVariable ("id") int id) {
		
		return service.updateEmployee(employee, id);
	}
	
	@DeleteMapping(value = "/{id}")
	public ResponseEntity<String> deleteEmployee(@PathVariable ("id") int id) {
		
		return service.deleteEmployee(id);
	}
	
	@GetMapping("/search")
	public List<Employee> getEmployees(@PathParam ("name") String name) {

		return service.getEmployees(name);
	}

}
