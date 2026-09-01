package com.example.demo.Service;

import org.springframework.stereotype.Service;

@Service
public class GreetingService {
	
    public void sayHello() {
        System.out.println("Hello World!");
    }
	
    public String getGreeting(String name) {
    	
    		if (name.equals("") || name == null) {
    			name = "Guest";
    		}
    	
        return "Hello, " + name + "!" ;
    }
    
    
    
    
}
