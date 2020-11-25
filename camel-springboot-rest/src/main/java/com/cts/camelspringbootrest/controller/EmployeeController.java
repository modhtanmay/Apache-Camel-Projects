package com.cts.camelspringbootrest.controller;

import java.util.List;

import org.apache.camel.ProducerTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.cts.camelspringbootrest.model.Employee;

@RestController
public class EmployeeController {

	@Autowired
	ProducerTemplate producerTemplate;
	
	@GetMapping("/employees")
	public List<Employee> getEmployees() {
		List<Employee> employees = producerTemplate.requestBody("direct:select",null,List.class);
		return employees;
	}
	
	@GetMapping("/employees/{id}")
	public List<Employee> getEmployeeById(@RequestBody Employee employee){
		List<Employee> employees = producerTemplate.requestBody("direct:selectById",employee,List.class);
		return employees;
	}
	
	@PostMapping("/employees")
	public String insertEmployee(@RequestBody Employee employee) {
		producerTemplate.requestBody("direct:insert", employee, List.class);
		return "Employee Added";
	}
}
