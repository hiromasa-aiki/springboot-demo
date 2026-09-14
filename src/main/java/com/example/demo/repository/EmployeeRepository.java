package com.example.demo.repository;

import org.springframework.stereotype.Repository;

import com.example.demo.dto.EmployeeResponse;

@Repository
public class EmployeeRepository {
	public EmployeeResponse findById(Long id) {
	    return new EmployeeResponse(1L, "田中", "tanaka@example.com");
	}

}
