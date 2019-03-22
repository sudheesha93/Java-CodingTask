package com.walmart.RESTService.Controller;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.walmart.RESTService.DAO.StudentDAO;
import com.walmart.RESTService.entity.Student;

@RestController
@RequestMapping("/api")
public class StudentRestController {
	
	private StudentDAO dao;
	
	@Autowired
	public StudentRestController(StudentDAO dao) {
		
		this.dao = dao;
	}
	
	@GetMapping("/students")
	public List<Student> findAll() {
		return dao.findAll();
	}
	
	@GetMapping("/students/{stuId}")
	public Student findByID(@PathVariable("stuId") int id) {
		
		 Student student= dao.findbyID(id);
		 if(student==null) {
			 throw new RuntimeException("Studnet not found with id: "+ id);
		 }
		 
		 return student;
	}
	
	@RequestMapping(value="/students", method=RequestMethod.POST)
	public Student addStudent(@RequestBody Student student) {
		student.setId(0);
		dao.saveOrUpdate(student);
		return student;
		
	}
	
	@PutMapping("/students")
	public Student updateEmployee(@RequestBody Student student) {
		dao.saveOrUpdate(student);
		return student;
	}
	
	@DeleteMapping("/students/{studentId}")
	public String deleteStudent(@PathVariable("studentId") int id) {
		Student student= dao.findbyID(id);
		
		if(student==null) {
			throw new RuntimeException("Studnet not found: "+ id);
		}
		dao.deleteByID(id);
		
		return "Student deleted: " + id;
		
	}
	
	
	@RequestMapping(value="/students/{name}/{email}/{gpa}", method=RequestMethod.POST,
			produces= {MediaType.APPLICATION_XML_VALUE,MediaType.APPLICATION_JSON_VALUE}) 
			//consumes=MediaType.ALL_VALUE)
	@ResponseBody()
	public Student addStudent(@PathVariable String name,
								@PathVariable String email,
								@PathVariable double gpa, @RequestParam int id) {
		Student student=dao.addDetails(id, name, email, gpa);
		
	
		return student;
		
	}
	
	@GetMapping(value="/students/{name}/{email}/{gpa}")
		//	produces= {MediaType.APPLICATION_XML_VALUE,MediaType.APPLICATION_JSON_VALUE})
	public Student printStudentDetails(@PathVariable String name,
								@PathVariable String email,
								@PathVariable double gpa, @RequestParam int id) {

		 Student student= dao.printStudent(id,name, email,gpa);
		 System.out.println("Student values obtained from the url : ");
		 return student;
}
	
	
	
}