package com.walmart.RESTService.DAO;

import java.util.List;

import javax.persistence.EntityManager;
import javax.transaction.Transactional;
import org.hibernate.Session;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.walmart.RESTService.entity.Student;

@Repository
public class StudentDAOImplementation implements StudentDAO {

	private EntityManager entityManager;

	@Autowired
	public StudentDAOImplementation(EntityManager entityManager) {
		this.entityManager = entityManager;
	}

	@Override
	@Transactional
	public List<Student> findAll() {

		Session curreSession = entityManager.unwrap(Session.class);
		Query<Student> theQuery = curreSession.createQuery("from Student", Student.class);
		List<Student> students = theQuery.getResultList();
		return students;
	}

	@Override
	@Transactional
	public Student findbyID(int id) {
		Session curreSession = entityManager.unwrap(Session.class);
		Student student = curreSession.get(Student.class, id);
		return student;
	}

	@Override
	@Transactional
	public void saveOrUpdate(Student student) {
		Session curreSession = entityManager.unwrap(Session.class);
		curreSession.saveOrUpdate(student);

	}

	@Override
	@Transactional
	public void deleteByID(int id) {
		Session curreSession = entityManager.unwrap(Session.class);
		Query<Student> theQuery = curreSession.createQuery("delete from Student where id=:empid");
		theQuery.setParameter("empid", id);
		theQuery.executeUpdate();

	}

	@Override
	@Transactional
	public Student addDetails(int id, String name, String email, double gpa) {

		Session curreSession = entityManager.unwrap(Session.class);

		Student student = new Student();

		student.setId(id);
		student.setEmail(email);
		student.setGpa(gpa);
		student.setName(name);

		curreSession.save(student);
		// curreSession.getTransaction().commit();

		return student;
	}

	@Override
	@Transactional
	public Student printStudent(int id, String name, String email, double gpa) {

		Session curreSession = entityManager.unwrap(Session.class);

		Student student = new Student();
		student.setId(id);
		student.setEmail(email);
		student.setGpa(gpa);
		student.setName(name);
		
		

		student.setId(id);
		student.setEmail(email);
		student.setGpa(gpa);
		student.setName(name);

		return student;
	}

}
