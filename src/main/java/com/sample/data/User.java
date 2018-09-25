package com.sample.data;

import org.springframework.stereotype.Component;

//@Component
public class User {

	private Long id = 0L;
	private String name = "default name";
	//private String lastName = "default last name";

	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
		
}
