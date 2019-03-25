package com.walmart.RESTService.entity;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

public class UserDetails {
	
	@NotNull(message="first name cannot be null")
	private String firstName;
	
	@NotNull(message="last name cannot be null")
	private String lastName;
	
	@NotNull(message="email cannot be null")
	@Email
	private String email;
	
	@NotNull(message="password cannot be null")
	@Size(min=6, max=12)
	private String password;

	public UserDetails() {
	
	}

	public UserDetails(String firstName, String lastName, String email, String password) {
		this.firstName = firstName;
		this.lastName = lastName;
		this.email = email;
		this.password = password;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}
	
	

	
}
