package com.example.demo.component;

import org.springframework.stereotype.Component;

@Component
public class GreetingComponent {

	private String aaa = "aaa";

	public String getAaa() {
		return aaa;
	}

	public void setAaa(String aaa) {
		this.aaa = aaa;
	}
	
}
