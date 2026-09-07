package com.example.demo.dto;

import jakarta.validation.constraints.NotBlank;

public class EmployeeRequest {
	
	@NotBlank(message = "名前は必須です")
	private String name;
	@NotBlank(message = "メールアドレスは必須です")
	private String email;

	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}

}
