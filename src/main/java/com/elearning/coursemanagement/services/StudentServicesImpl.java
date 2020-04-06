package com.elearning.coursemanagement.services;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import com.elearning.coursemanagement.dao.CourseDao;
import com.elearning.coursemanagement.dao.StudentDao;
import com.elearning.coursemanagement.model.Course;
import com.elearning.coursemanagement.model.Student;
import com.elearning.coursemanagement.model.StudentLogin;
@Service
@Component
public class StudentServicesImpl  implements StudentServices{
      @Autowired
	  private StudentDao studentDao;
      @Autowired
      private CourseDao courseDao;
	
	
	@Override
	public int createStudent(Student student) {
          Student s = studentDao.findByUsername(student.getUsername());
          if(s==null)
          {
        	 Student s1= studentDao.save(student);
        	 if(s1!=null)
        	 {
        		 return 1;
        	 }
        	 else
        	 {
        		 return 2;
        	 }
          }
// 0 aleady exists, 1 stored successfully , 2 some went wrong
		     
		return 0;
	}

	@Override
	public boolean login(StudentLogin studentlogin) {

            Student s = studentDao.findByUsername(studentlogin.getUsername());
            
            if(s!=null)
            {
            	return s.getPassword().equals(studentlogin.getPassword());
            }
		
		return false;
	}

	@Override
	public boolean updateStudent(Student student) {
 // studentDao.sa
Student s =studentDao.save(student);
if(s!=null)
{
	return true;
}
		return false;
	}

	@Override
	public List<Course> getAllCourses() {
		
		
		
		return  courseDao.findAll();
	}

	@Override
	public Course getCourse(int id) {
		
		return courseDao.findById(id).get();
	}

	@Override
	public Student getStudent(String username) {

              
		return studentDao.findByUsername(username);
	}

}
