package com.example.demo.service;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import com.example.demo.Service.GreetingService;
import com.example.demo.dto.EmployeeResponse;
import com.example.demo.repository.EmployeeRepository;

class GreetingServiceTest {
	
    @Test
    void 名前を渡したら名前付きの挨拶を返す1() {
    	
    	EmployeeRepository repository =
    	        Mockito.mock(EmployeeRepository.class);

        GreetingService service = new GreetingService(repository);

        String result = service.getGreeting("Tanaka");

        assertEquals("Hello, Tanaka!", result);
    }
    
    @Test
    void 名前を渡したら名前付きの挨拶を返す2() {
    	
    	EmployeeRepository repository =
    	        Mockito.mock(EmployeeRepository.class);

        GreetingService service = new GreetingService(repository);

        String result = service.getGreeting("");

        assertEquals("Hello, Guest!", result);
    }
    
    @Test
    void 社員IDを渡したら社員情報を返す() {

	    EmployeeRepository repository =
	            Mockito.mock(EmployeeRepository.class);
	
	    EmployeeResponse employee =
	            new EmployeeResponse(1L, "田中", "tanaka@example.com");
	
	    Mockito.when(repository.findById(1L))
	            .thenReturn(employee);
	
	    GreetingService service =
	            new GreetingService(repository);
	
	    EmployeeResponse result =
	            service.getEmployee(1L);
	
	    assertEquals(1L, result.getId());
	    assertEquals("田中", result.getName());
	    assertEquals("tanaka@example.com", result.getEmail());

    }

}
