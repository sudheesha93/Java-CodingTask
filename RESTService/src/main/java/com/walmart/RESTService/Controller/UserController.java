package com.walmart.RESTService.Controller;

import javax.validation.Valid;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.walmart.RESTService.entity.Student;
import com.walmart.RESTService.entity.UserDetails;

@RestController
@RequestMapping("/extra")
public class UserController {
	
	@GetMapping("/users/{firstname}")
	public ResponseEntity<UserDetails> getUser(@PathVariable String firstname){
		
		UserDetails user= new UserDetails(firstname, "Reddy",
											"sudheesha@gmail.com", "Reddy1993");
		
		return new ResponseEntity<UserDetails>(user, HttpStatus.OK);
	}
	
	
	@PostMapping(value="/users", produces= {MediaType.APPLICATION_JSON_VALUE,MediaType.APPLICATION_XML_VALUE},
										consumes= {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE})
	public ResponseEntity<UserDetails> createUser(@Valid @RequestBody UserDetails response){
		
		UserDetails user= new UserDetails();
		user.setFirstName(response.getFirstName());
		user.setLastName(response.getLastName());
		user.setEmail(response.getEmail());
		user.setPassword(response.getPassword());
		
		return new ResponseEntity<UserDetails>(user, HttpStatus.CREATED);
		
	}
	
}
