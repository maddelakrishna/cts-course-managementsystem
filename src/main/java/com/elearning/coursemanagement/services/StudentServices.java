package com.elearning.coursemanagement.services;

import java.util.List;

import com.elearning.coursemanagement.model.Course;
import com.elearning.coursemanagement.model.Student;
import com.elearning.coursemanagement.model.StudentLogin;

public interface StudentServices {
	
	public int createStudent(Student student);
	public boolean login(StudentLogin studentlogin);
	public boolean updateStudent(Student student);
	public Student getStudent(String username);
	public Course getCourse(int id);
	public List<Course> getAllCourses(); 
	

}
