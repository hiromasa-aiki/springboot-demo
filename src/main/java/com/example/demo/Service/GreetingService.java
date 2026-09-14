package com.example.demo.Service;

import org.springframework.stereotype.Service;

import com.example.demo.dto.EmployeeResponse;
import com.example.demo.repository.EmployeeRepository;

@Service
public class GreetingService {
	
	private EmployeeRepository employeeRepository;
	
	public GreetingService(EmployeeRepository employeeRepository) {
	    this.employeeRepository = employeeRepository;
	}
	
    public void sayHello() {
        System.out.println("Hello World!");
    }
	
    public String getGreeting(String name) {
    	
    		if (name.equals("") || name == null) {
    			name = "Guest";
    		}
    	
        return "Hello, " + name + "!" ;
    }
    
    public EmployeeResponse getEmployee(Long id) {
    	return employeeRepository.findById(id);
    }
    
    
    
    
}
