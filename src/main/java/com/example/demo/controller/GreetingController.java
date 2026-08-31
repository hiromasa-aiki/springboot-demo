package com.example.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.Service.GreetingService;
import com.example.demo.component.GreetingComponent;
import com.example.demo.dto.EmployeeRequest;
import com.example.demo.dto.EmployeeResponse;

@RequestMapping("/Greeting")
@RestController
public class GreetingController {
	
	@Autowired
	private GreetingService greetingService;
	@Autowired
	private GreetingComponent geetingComponent;
	
    @GetMapping("/hello")
    public String hello(@RequestParam(value = "name", defaultValue = "World") String name) {
      return String.format("Hello %s!", name);
    }
	
    @GetMapping("/hello2")
    public String hello2(@RequestParam(value = "name", defaultValue = "World2") String name) {
    	greetingService.sayHello();
    	System.out.println(geetingComponent.getAaa());
    	return String.	format("Hello %s!", name);
    }
    
    @PostMapping("/hellopost")
    public String hellopost(@RequestParam(value = "name", defaultValue = "WorldPost") String name) {
    	return String.	format("Hello %s!", name);
    }
    
    @GetMapping("/employees/1")
    public EmployeeResponse getEmployee() {
    	
    	return new EmployeeResponse(1L, "田中", "tanaka@example.com");
    }
    
    @PostMapping("/employees")
    public EmployeeResponse createEmployee(@RequestBody EmployeeRequest request) {
    	
    	return new EmployeeResponse(1L, request.getName(), request.getEmail());
    }
    
    @Value("${app.message}")
    private String message;
    
    @GetMapping("/config")
    public String config() {
        return message;
    }
    
    
    @GetMapping("/greeting")
    public String greeting(@RequestParam(value = "name", defaultValue = "World") String name) {
        return greetingService.getGreeting(name);
    }
    
    
    

}
