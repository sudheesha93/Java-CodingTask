package com.walmart.RESTService.Controller;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.skyscreamer.jsonassert.JSONAssert;
import org.skyscreamer.jsonassert.JSONCompare;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import com.walmart.RESTService.DAO.StudentDAO;
import com.walmart.RESTService.entity.Student;

@RunWith(SpringRunner.class)
@WebMvcTest(value = StudentRestController.class, secure = false)
public class StudentRestControllerTest {

	@Autowired
	private MockMvc mockMvc;

	@MockBean
	private StudentDAO dao;
	
	
	// Test case for find all students
	@Test
	public void findAllTest() throws Exception{
		
		List<Student> list= new ArrayList<>();
		list.add(new Student(2,"sandeep","sandeep@gmail.com",3.5));
		list.add(new Student(1,"sudheesha","sudheesha@gmail.com",3.6));
		
		Mockito.when(dao.findAll()).thenReturn(list);
		
		RequestBuilder requestBuilder = MockMvcRequestBuilders.get("/api/students")
				.accept(MediaType.APPLICATION_JSON);
		MvcResult result = mockMvc.perform(requestBuilder).andReturn();
		System.out.println(result.getResponse());
		
		String expected="[{id:2,name:sandeep,email:sandeep@gmail.com,gpa:3.5},"
				+ "			{id:1,name:sudheesha,email:sudheesha@gmail.com,gpa:3.6}]";
		
		JSONAssert.assertEquals(expected, result.getResponse().getContentAsString(), false);
		
	}
	
	
	// Test case for find student with a specific ID
	@Test
	public void findByIdTest() throws Exception {
		
		Student s=new Student(2, "sandeep", "sandeep@gmail.com", 3.5);

		Mockito.when(dao.findbyID(Mockito.anyInt())).thenReturn(s);

		RequestBuilder requestBuilder = MockMvcRequestBuilders.get("/api/students/2")
				.accept(MediaType.APPLICATION_JSON);
		MvcResult result = mockMvc.perform(requestBuilder).andReturn();
		System.out.println(result.getResponse());
		String expected="{id:2,name:sandeep,email:sandeep@gmail.com,gpa:3.5}";
		JSONAssert.assertEquals(expected, result.getResponse().getContentAsString(), false);

	}
	
	// Test case for accessing details from the URL and adding them to database
	@Test
	public void addDetailsTest() throws Exception{
		
		Student s=new Student(11,"sanjay","sanjay@gmail.com",3);
		Mockito.when(dao.addDetails(Mockito.anyInt(), Mockito.anyString(),
										Mockito.anyString(), Mockito.anyDouble())).thenReturn(s);
				
		RequestBuilder requestBuilder = MockMvcRequestBuilders.post("/api/students/sanjay/sanjay@gmail.com/3?id=11")
				.accept(MediaType.APPLICATION_JSON);
				
		MvcResult result = mockMvc.perform(requestBuilder).andReturn();
		HttpServletResponse response=result.getResponse();
		
		assertEquals(HttpStatus.OK.value(), response.getStatus());
		
	}
	
	// Test case for accessing details from the URL and populating a student object
	@Test
	public void printStudentDetailsTest() throws Exception{
		
		Student s=new Student(10, "sree", "sree@gmail.com", 3.5);
		Mockito.when(dao.printStudent(Mockito.anyInt(), Mockito.anyString(),
										Mockito.anyString(), Mockito.anyDouble())).thenReturn(s);
		RequestBuilder requestBuilder = MockMvcRequestBuilders.get("/api/students/sree/sree@gmail.com/3.5?id=10")
				.accept(MediaType.APPLICATION_JSON);
		MvcResult result = mockMvc.perform(requestBuilder).andReturn();
		System.out.println(result.getResponse());
		String expected="{id:10,name:sree,email:sree@gmail.com,gpa:3.5}";
		JSONAssert.assertEquals(expected, result.getResponse().getContentAsString(), false);
	}
	
	
}

