package com.elearning.coursemanagement.dao;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.elearning.coursemanagement.model.Course;
import com.elearning.coursemanagement.model.Student;

public interface StudentDao extends CrudRepository<Student, Integer> {
	
	public List<Student>  findAll();
	public Student findByUsername(String username);
	
	
}
