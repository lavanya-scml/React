package com.tavant.springbackend.controller;

import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.tavant.springbackend.exception.EmployeeNotFound;
import com.tavant.springbackend.exception.NoDataFoundException;
import com.tavant.springbackend.model.Employee;
import com.tavant.springbackend.repository.EmployeeRepository;


@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequestMapping("/api/employee")
public class EmployeeController {
	
	@Autowired
	EmployeeRepository employeeRepository;
	//used to retrieve the details
	@GetMapping 
	public String getEmployee() {
		
		return "this is get";
	}
	//for retrieving details of all employees
	@GetMapping("/all")  
	public List<Employee> getAllEmployees() throws Exception {
		
		List list = this.employeeRepository.findAll();
		return Optional.ofNullable(list.isEmpty()? null :list).orElseThrow(()->
		new NoDataFoundException("No data found"));
		
	}
	
	// retrieving by id
	@GetMapping("/{id}")   
	public ResponseEntity<?> getEmployeeById(@PathVariable("id") Integer id) throws EmployeeNotFound
{
	Optional<Employee> optional=employeeRepository.findById(id);
	if(optional.isPresent())
	{
		return ResponseEntity.ok(optional.get());
	}
	else
	{
		throw new EmployeeNotFound("Resources not found");
	}
	
}

@PostMapping  // to add details into the db
public Employee addEmployee(@RequestBody @Valid Employee employee) throws EmployeeNotFound
{
	if(employee.getEmployeeId()==null)
	{
		throw new EmployeeNotFound("cannot add employee");
		
	}
	
	return employeeRepository.save(employee);
}


@DeleteMapping("/delete/{id}")  // to delete the details from db
public String deleteEmployee(@PathVariable("id") Integer id) throws EmployeeNotFound
{
Optional<Employee> optional=employeeRepository.findById(id);
if(optional.isPresent())
{
	employeeRepository.deleteById(id);
	return "employee deleted successfully";
}
else
{
	throw new EmployeeNotFound("Resources not found");
}

}

@PutMapping("/{Id}") // to update the fields of the employees
public ResponseEntity<Employee> updateAccount(@PathVariable("Id") Integer id,@Valid @RequestBody Employee 
		resourceDetails) throws EmployeeNotFound {
	
	Employee acc = employeeRepository.findById(id).orElseThrow(()->new EmployeeNotFound("record not found"));
	
	
	acc.setFirstName(resourceDetails.getFirstName());
	acc.setLastName(resourceDetails.getLastName());
	acc.setEmail(resourceDetails.getEmail());
	acc.setAddress(resourceDetails.getAddress());
	acc.setGender(resourceDetails.getGender());
	
	final Employee newDetails=employeeRepository.save(acc);
	return ResponseEntity.ok(newDetails);
}


}
