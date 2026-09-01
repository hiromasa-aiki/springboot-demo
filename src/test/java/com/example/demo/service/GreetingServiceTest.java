package com.example.demo.service;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import com.example.demo.Service.GreetingService;

class GreetingServiceTest {
	
    @Test
    void 名前を渡したら名前付きの挨拶を返す1() {

        GreetingService service = new GreetingService();

        String result = service.getGreeting("Tanaka");

        assertEquals("Hello, Tanaka!", result);
    }
    
    @Test
    void 名前を渡したら名前付きの挨拶を返す2() {

        GreetingService service = new GreetingService();

        String result = service.getGreeting("");

        assertEquals("Hello, Guest!", result);
    }

}
