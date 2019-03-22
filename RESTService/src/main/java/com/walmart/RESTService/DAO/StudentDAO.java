package com.walmart.RESTService.DAO;

import java.util.List;

import com.walmart.RESTService.entity.Student;

public interface StudentDAO {
	
	public List<Student> findAll();
	
	public Student findbyID(int id);
	
	public void saveOrUpdate(Student emp);
	
	public void deleteByID(int id);
	
	public Student addDetails(int id, String name, String email, double gpa);

	public Student printStudent(int id, String name, String email, double gpa);
	

}
