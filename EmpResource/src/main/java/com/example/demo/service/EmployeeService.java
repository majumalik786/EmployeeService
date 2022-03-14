package com.example.demo.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import com.example.demo.model.Employee;
import com.example.demo.repository.EmployeeRepository;


@Service
public class EmployeeService {
	
	@Autowired
	private EmployeeRepository repository;
    List<Employee> eList;
    ResponseEntity<String> response;
   

	public ResponseEntity<String> createEmployee(Employee employee) {
		repository.save(employee);
		return new ResponseEntity<>("Successfully saved",HttpStatus.CREATED);
	}
	
	public List<Employee> getAllEmployee(){
		
		eList=new ArrayList<>();
		repository.findAll().forEach(emloyee->eList.add(emloyee));
		
		return eList;
	}
	
	public ResponseEntity<Employee> getEmployeeById(int id) {
		
		Optional<Employee> empId=repository.findById(id);
		if(empId.isPresent()) {
			return new ResponseEntity<>(empId.get(), HttpStatus.OK);
		}else {
			
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
		
	}
	
	
	public ResponseEntity<String> updateEmployee(Employee employee, int id) {
		
		
		Optional<Employee> empId=repository.findById(id);
		if(empId.isPresent()) {
			
			if(employee.getEmp_id()!=id && employee.getEmp_id()!=0) {
				//System.out.println("id miss match");
				return new ResponseEntity<>("Employee Id miss match", HttpStatus.OK);
			}else {
				String original_name=empId.get().getName().intern();
				String payload_name=employee.getName().intern();
				// System.out.println(original_name);
				// System.out.println(payload_name);
				if(original_name!=payload_name) {
					return new ResponseEntity<>("Can not update employee name", HttpStatus.OK);
				}else {
					if(employee.getEmp_id()==0) {
						employee.setEmp_id(id);
					}
					repository.save(employee);
					return new ResponseEntity<>("Employee successfully updated", HttpStatus.OK);
				}
				
			}
			
			
		}else {
			
			return new ResponseEntity<>("Employee Id does not exists", HttpStatus.NOT_FOUND);
			
		}
	
		
	}
	
	
	
	public ResponseEntity<String> deleteEmployee(int id) {
		
		Optional<Employee> empId=repository.findById(id);
		if(empId.isPresent()) {
		repository.deleteById(id);
		return new ResponseEntity<>("Successfully Deleted", HttpStatus.FORBIDDEN);
		}else {
			return new ResponseEntity<>("Employee Id does not exists", HttpStatus.NOT_FOUND);
		}
		
		
	}
	
	public List<Employee> getEmployees(String name){
		
		eList=new ArrayList<>();
		repository.getEmployees(name).forEach(emloyee->eList.add(emloyee));
		
		return eList;
	}
	

}
